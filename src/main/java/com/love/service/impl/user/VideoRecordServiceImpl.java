package com.love.service.impl.user;

import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import com.love.service.VideoRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class VideoRecordServiceImpl implements VideoRecordService {

    @Autowired
    private VideoRecordMapper videoRecordMapper;

    @Override
    public void add(String username, Integer videoId, Integer categoryId) {
        log.info("添加/更新播放记录 - 用户: {}, 视频ID: {}, 分类ID: {}", username, videoId, categoryId);
        LocalDateTime createTime = LocalDateTime.now();
        
        // 检查是否存在记录
        VideoRecord existingRecord = videoRecordMapper.findByUserIdAndVideoId(username, videoId);
        
        if (existingRecord != null) {
            log.info("更新已存在的播放记录 - 用户: {}, 视频ID: {}, 旧时间: {}, 新时间: {}", 
                    username, videoId, existingRecord.getCreateTime(), createTime);
            videoRecordMapper.updateTime(username, videoId, createTime);
        } else {
            log.info("添加新的播放记录 - 用户: {}, 视频ID: {}, 时间: {}", username, videoId, createTime);
            videoRecordMapper.add(username, videoId, categoryId, createTime);
        }
        
        // 验证记录是否成功更新
        VideoRecord updatedRecord = videoRecordMapper.findByUserIdAndVideoId(username, videoId);
        if (updatedRecord != null) {
            log.info("播放记录已保存 - 视频ID: {}, 时间: {}", videoId, updatedRecord.getCreateTime());
        } else {
            log.error("播放记录保存失败 - 无法找到更新后的记录");
        }
    }

    /**
     * 获取播放记录
     */
    @Override
    public List<Video> findByUserIdAndVideoId(String username) {
        log.info("获取用户播放记录列表: {}", username);
        List<Video> records = videoRecordMapper.selectVideoRecord(username).stream()
                .filter(video -> video.getStatus() == 2)
                .toList();
        log.info("用户 {} 播放记录数量: {}", username, records.size());
        return records;
    }
}
