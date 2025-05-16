package com.love.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.love.mapper.VideoMapper;
import com.love.mapper.anime.AnimeMapper;
import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Anime;
import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import com.love.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private VideoRecordMapper videoRecordMapper;
    @Autowired
    private AnimeMapper animeMapper;

    // 视频标签缓存
    private final Map<Integer, Set<String>> videoTagsCache = new HashMap<>();
    // 用户偏好模型缓存
    private final Map<String, RealVector> userPreferenceModels = new HashMap<>();
    // 视频特征向量缓存
    private final Map<Integer, RealVector> videoFeatureVectors = new HashMap<>();

    @Override
    public List<Video> list(Integer subCategoryId) {
        // 获取基础视频列表
        List<Video> combinedList = getBaseVideoList(subCategoryId);

        // 检查用户是否已登录
        if (request.getHeader("Authorization") != null) {
            String username = extractUsername(request.getHeader("Authorization"));

            if (!username.isEmpty()) {
                // 获取个性化推荐列表
                return getPersonalizedRecommendations(username, combinedList);
            }
        }

        // 未登录用户返回热门视频
        return getPopularVideos(combinedList);
    }

    /**
     * 获取基础视频列表（原始视频+动漫）
     */
    private List<Video> getBaseVideoList(Integer subCategoryId) {
        // 获取原始视频列表
        List<Video> originalList = videoMapper.list(subCategoryId).stream()
                .filter(video -> video.getStatus() == 2)
                .peek(video -> video.setIsAnime(false))
                .collect(Collectors.toList());

        // 获取动漫列表并转换
        List<Video> animeVideos = animeMapper.list().stream()
                .map(this::convertAnimeToVideo)
                .collect(Collectors.toList());

        // 合并列表
        List<Video> combinedList = new ArrayList<>();
        combinedList.addAll(originalList);
        combinedList.addAll(animeVideos);

        // 初始化视频特征
        initializeVideoFeatures(combinedList);

        return combinedList;
    }

    /**
     * 获取个性化推荐
     */
    private List<Video> getPersonalizedRecommendations(String username, List<Video> videos) {
        // 1. 基于内容的推荐
        List<Video> contentBased = contentBasedRecommendation(username, videos);

        // 2. 协同过滤推荐
        List<Video> collaborativeFiltering = collaborativeFilteringRecommendation(username, videos);

        // 3. 用户历史行为推荐
        List<Video> historical = historicalBehaviorRecommendation(username, videos);

        // 混合推荐结果
        return blendRecommendations(contentBased, collaborativeFiltering, historical, videos);
    }

    /**
     * 基于内容的推荐
     */
    private List<Video> contentBasedRecommendation(String username, List<Video> videos) {
        // 获取或创建用户偏好模型
        RealVector userModel = getUserPreferenceModel(username);

        return videos.stream()
                .sorted((v1, v2) -> {
                    double score1 = cosineSimilarity(userModel, videoFeatureVectors.get(v1.getId()));
                    double score2 = cosineSimilarity(userModel, videoFeatureVectors.get(v2.getId()));
                    return Double.compare(score2, score1); // 降序
                })
                .limit(20)
                .collect(Collectors.toList());
    }

    /**
     * 协同过滤推荐
     */
    private List<Video> collaborativeFilteringRecommendation(String username, List<Video> videos) {
        // 获取相似用户
        List<String> similarUsers = findSimilarUsers(username);

        // 获取相似用户喜欢的视频
        Set<Integer> recommendedVideoIds = new HashSet<>();
        for (String similarUser : similarUsers) {
            List<VideoRecord> records = videoRecordMapper.userRecordList(similarUser);
            records.stream()
                    .map(VideoRecord::getVideoId)
                    .forEach(recommendedVideoIds::add);
        }

        // 排除用户已经看过的视频
        List<VideoRecord> userRecords = videoRecordMapper.userRecordList(username);
        Set<Integer> userWatchedIds = userRecords.stream()
                .map(VideoRecord::getVideoId)
                .collect(Collectors.toSet());

        recommendedVideoIds.removeAll(userWatchedIds);

        // 返回推荐视频
        return videos.stream()
                .filter(v -> recommendedVideoIds.contains(v.getId()))
                .sorted(Comparator.comparingDouble(v -> -getVideoPopularityScore(v.getId()))) // 按热度降序
                .limit(20)
                .collect(Collectors.toList());
    }

    /**
     * 基于历史行为的推荐
     */
    private List<Video> historicalBehaviorRecommendation(String username, List<Video> videos) {
        // 获取用户最近观看记录
        List<VideoRecord> records = videoRecordMapper.userRecordList(username);

        if (records.isEmpty()) {
            return Collections.emptyList();
        }

        // 获取最近观看的10个视频
        Set<Integer> recentWatchedIds = records.stream()
                .sorted(Comparator.comparing(VideoRecord::getCreateTime).reversed())
                .limit(10)
                .map(VideoRecord::getVideoId)
                .collect(Collectors.toSet());

        // 找到与这些视频相似的内容
        return videos.stream()
                .filter(v -> !recentWatchedIds.contains(v.getId())) // 排除已经看过的
                .sorted((v1, v2) -> {
                    double score1 = maxSimilarityToWatched(v1.getId(), recentWatchedIds);
                    double score2 = maxSimilarityToWatched(v2.getId(), recentWatchedIds);
                    return Double.compare(score2, score1); // 降序
                })
                .limit(20)
                .collect(Collectors.toList());
    }

    /**
     * 混合推荐结果
     */
    private List<Video> blendRecommendations(List<Video> contentBased,
                                             List<Video> collaborativeFiltering,
                                             List<Video> historical,
                                             List<Video> allVideos) {
        // 给每种推荐结果赋予权重
        Map<Integer, Double> videoScores = new HashMap<>();

        // 基于内容的推荐权重
        for (int i = 0; i < contentBased.size(); i++) {
            Video v = contentBased.get(i);
            double score = 0.5 * (contentBased.size() - i) / contentBased.size();
            videoScores.merge(v.getId(), score, Double::sum);
        }

        // 协同过滤推荐权重
        for (int i = 0; i < collaborativeFiltering.size(); i++) {
            Video v = collaborativeFiltering.get(i);
            double score = 0.3 * (collaborativeFiltering.size() - i) / collaborativeFiltering.size();
            videoScores.merge(v.getId(), score, Double::sum);
        }

        // 历史行为推荐权重
        for (int i = 0; i < historical.size(); i++) {
            Video v = historical.get(i);
            double score = 0.2 * (historical.size() - i) / historical.size();
            videoScores.merge(v.getId(), score, Double::sum);
        }

        // 确保所有视频都有分数（对于未被推荐但需要展示的视频）
        allVideos.forEach(v -> videoScores.putIfAbsent(v.getId(), getVideoPopularityScore(v.getId()) * 0.1));

        // 按分数排序并返回
        return allVideos.stream()
                .sorted(Comparator.comparingDouble(v -> -videoScores.get(v.getId())))
                .collect(Collectors.toList());
    }

    /**
     * 获取热门视频
     */
    private List<Video> getPopularVideos(List<Video> videos) {
        return videos.stream()
                .sorted(Comparator.comparingDouble(v -> -getVideoPopularityScore(v.getId())))
                .collect(Collectors.toList());
    }

    /**
     * 初始化视频特征
     */
    private void initializeVideoFeatures(List<Video> videos) {
        // 这里应该是从数据库或其它服务获取视频标签
        // 示例中我们模拟一些标签
        String[] allTags = {"action", "comedy", "romance", "sci-fi", "drama", "adventure", "fantasy", "horror"};

        Random random = new Random();
        for (Video video : videos) {
            // 随机生成一些标签（实际应用中应该从数据库获取）
            Set<String> tags = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                tags.add(allTags[random.nextInt(allTags.length)]);
            }
            videoTagsCache.put(video.getId(), tags);

            // 创建特征向量
            RealVector vector = new ArrayRealVector(allTags.length);
            int i = 0;
            for (String tag : allTags) {
                vector.setEntry(i++, tags.contains(tag) ? 1.0 : 0.0);
            }
            videoFeatureVectors.put(video.getId(), vector);
        }
    }

    /**
     * 获取用户偏好模型
     */
    private RealVector getUserPreferenceModel(String username) {
        // 如果已有缓存，直接返回
        if (userPreferenceModels.containsKey(username)) {
            return userPreferenceModels.get(username);
        }

        // 获取用户观看记录
        List<VideoRecord> records = videoRecordMapper.userRecordList(username);

        // 创建初始向量（大小与标签数量相同）
        RealVector userModel = new ArrayRealVector(videoFeatureVectors.values().iterator().next().getDimension());

        if (!records.isEmpty()) {
            // 基于用户观看历史计算偏好
            for (VideoRecord record : records) {
                RealVector videoVector = videoFeatureVectors.get(record.getVideoId());
                if (videoVector != null) {
                    userModel = userModel.add(videoVector);
                }
            }

            // 归一化
            userModel = userModel.mapDivide(userModel.getNorm());
        } else {
            // 新用户使用热门标签
            for (int i = 0; i < userModel.getDimension(); i++) {
                userModel.setEntry(i, Math.random()); // 随机初始值
            }
        }

        // 缓存用户模型
        userPreferenceModels.put(username, userModel);

        return userModel;
    }

    /**
     * 查找相似用户
     */
    private List<String> findSimilarUsers(String username) {
        // 实际应用中应该从用户服务获取
        // 这里简化为随机返回一些用户
        List<String> allUsers = videoRecordMapper.getAllUsers();
        allUsers.remove(username); // 排除自己

        Collections.shuffle(allUsers);
        return allUsers.stream().limit(5).collect(Collectors.toList());
    }

    /**
     * 计算余弦相似度
     */
    private double cosineSimilarity(RealVector v1, RealVector v2) {
        return v1.dotProduct(v2) / (v1.getNorm() * v2.getNorm());
    }

    /**
     * 计算视频与已观看视频的最大相似度
     */
    private double maxSimilarityToWatched(Integer videoId, Set<Integer> watchedIds) {
        RealVector targetVector = videoFeatureVectors.get(videoId);
        if (targetVector == null) return 0;

        return watchedIds.stream()
                .mapToDouble(watchedId -> {
                    RealVector watchedVector = videoFeatureVectors.get(watchedId);
                    return watchedVector != null ? cosineSimilarity(targetVector, watchedVector) : 0;
                })
                .max()
                .orElse(0);
    }

    /**
     * 获取视频热度分数
     */
    private double getVideoPopularityScore(Integer videoId) {
        // 实际应用中应该考虑观看次数、点赞数、收藏数等
        // 这里简化为随机值
        return Math.random();
    }

    // 保留原有的convertAnimeToVideo和extractUsername方法
    private Video convertAnimeToVideo(Anime anime) {
        Video video = new Video();
        if (anime.getAnimeId() != null) {
            video.setId(anime.getAnimeId().intValue());
        } else {
            video.setId((int)(System.currentTimeMillis() % Integer.MAX_VALUE));
            log.warn("Anime with title '{}' has null animeId, using generated ID: {}", anime.getTitle(), video.getId());
        }
        video.setTitle(anime.getTitle());
        video.setCover(anime.getCoverImage());
        video.setContent(anime.getDescription());
        video.setStatus(2);
        video.setIsAnime(true);
        video.setNickname(anime.getNickname());
        video.setUserPic(anime.getUserPic());
        
        return video;
    }

    private String extractUsername(String authHeader) {
        // 实现保持不变
        if (authHeader == null || authHeader.isEmpty()) {
            return "";
        }
        return "24895020387"; // 示例简化
    }

    // 保留原有的findById和getCollectList方法
    @Override
    public Video findById(Integer id) {
        return videoMapper.findById(id);
    }

    @Override
    public List<Video> getCollectList(Long userId) {
        return videoMapper.findVideoFavorite(userId);
    }
}