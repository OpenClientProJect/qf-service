package com.love.controller;

import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/video/like")
public class VideoLikeController {

    @Autowired
    private VideoLikeService videoLikeService;

    /**
     * 获取视频点赞列表
     */
    @GetMapping
    public Result<List<Video>> list(Long id) {
        List<Video> videoLikeList = videoLikeService.getVideoLikeList(id);
        return Result.success(videoLikeList);
    }
}
