package com.love.controller.user;

import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import com.love.service.VideoRecordService;
import com.love.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 播放记录
 */
@Slf4j
@RestController
@RequestMapping("/api/video/record")
public class VideoRecordController {

    @Autowired
    private VideoRecordService videoRecordService;

    @Autowired
    private HttpServletRequest request;



    /**
     * 添加到播放列表
     */
    @PostMapping
    public Result<String> add(@RequestBody VideoRecord videoRecord) {
        log.info("接收到播放记录请求：videoId={}, categoryId={}", videoRecord.getVideoId(), videoRecord.getCategoryId());
        
        String authHeader = request.getHeader("Authorization");
        Map<String, Object> userMap = JwtUtil.parseToken(authHeader);
        String username = (String) userMap.get("username");
        
        log.info("用户 {} 添加播放记录 videoId={}, categoryId={}", username, videoRecord.getVideoId(), videoRecord.getCategoryId());
        videoRecordService.add(username, videoRecord.getVideoId(), videoRecord.getCategoryId());
        return Result.success();
    }

    /**
     * 获取播放列表
     */
    @GetMapping
    public Result<List<Video>> getVideoList() {
        String authHeader = request.getHeader("Authorization");
        Map<String, Object> userMap = JwtUtil.parseToken(authHeader);
        return Result.success(videoRecordService.findByUserIdAndVideoId((String) userMap.get("username")));
    }
}
