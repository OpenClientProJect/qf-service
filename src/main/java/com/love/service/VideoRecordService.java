package com.love.service;

import com.love.pojo.Video;

import java.util.List;

public interface VideoRecordService {

    /**
     * 添加播放记录
     */
    void add(String username, Integer videoId,Integer categoryId);

    List<Video> findByUserIdAndVideoId(String username);
}
