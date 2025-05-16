package com.love.service.impl;

import com.love.mapper.SearchMapper;
import com.love.pojo.Video;
import com.love.pojo.user.User;
import com.love.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;

    @Override
    public HashMap<String, Object> search(String keyword, Integer pageNum, Integer pageSize) {
        List<Video> videos = searchMapper.searchVideos(keyword);
        List<Video> animes = searchMapper.searchAnimes(keyword);
        List<User> users = searchMapper.searchUser(keyword);
        
        // 合并视频和番剧结果
        List<Video> combinedResults = new ArrayList<>();
        combinedResults.addAll(videos);
        combinedResults.addAll(animes);
        
        // 按创建时间排序（降序）
        combinedResults = combinedResults.stream()
                .sorted(Comparator.comparing(Video::getCreateTime).reversed())
                .collect(Collectors.toList());

        HashMap<String, Object> result = new HashMap<>();
        result.put("video", combinedResults);
        result.put("users", users);
        return result;
    }
}
