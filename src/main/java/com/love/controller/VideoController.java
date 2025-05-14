package com.love.controller;

import com.love.pojo.Barrage;
import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.service.BarrageService;
import com.love.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    BarrageService barrageService;
    @Autowired
    VideoService videoService;
    // 查询所有视频
    @GetMapping
    public Result<List<Video>> list(@RequestParam(required = false) Integer subCategoryId) {
        List<Video> list = videoService.list(subCategoryId);
        return Result.success(list);
    }
    //获取视频详细信息
    @GetMapping("/videoInfo")
    public Result<Video> videoInfo(Integer id){
        log.info("获取视频详情，ID: {}", id);
        Video video = videoService.findById(id);
        return Result.success(video);
    }
    //获取弹幕
    @GetMapping("/barrage")
    public Result<List<Barrage>> getBarrage(@RequestParam Integer videoId) {
        return Result.success(barrageService.getBarrage(videoId));
    }
    //获取视频收藏列表
    @GetMapping("/collectList")
    public Result<List<Video>> collectList(Long userId) {
        List<Video> collectList = videoService.getCollectList(userId);
        return Result.success(collectList);
    }
}
