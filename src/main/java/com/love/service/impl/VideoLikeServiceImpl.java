package com.love.service.impl;

import com.love.mapper.VideoLikesMapper;
import com.love.pojo.Video;
import com.love.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoLikeServiceImpl implements VideoLikeService {

    @Autowired
    private VideoLikesMapper videoLikesMapper;

    @Override
    public List<Video> getVideoLikeList(Long id) {
        return videoLikesMapper.findVideoLikeList(id);
    }
}
