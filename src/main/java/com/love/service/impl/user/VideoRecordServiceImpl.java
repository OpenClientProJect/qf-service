package com.love.service.impl.user;

import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Video;
import com.love.service.VideoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoRecordServiceImpl implements VideoRecordService {

    @Autowired
    private VideoRecordMapper videoRecordMapper;

    @Override
    public void add(String username, Integer videoId) {
        LocalDateTime createTime = LocalDateTime.now();
        if (videoRecordMapper.findByUserIdAndVideoId(username, videoId) != null) {
            videoRecordMapper.updateTime(username, videoId, createTime);
        } else {
            videoRecordMapper.add(username, videoId, createTime);
        }
    }

    /**
     * 获取播放记录
     */
    @Override
    public List<Video> findByUserIdAndVideoId(String username) {
        return videoRecordMapper.selectVideoRecord(username).stream()
                .filter(video -> video.getStatus() == 2)
                .toList();
    }
}
