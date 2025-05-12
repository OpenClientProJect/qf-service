package com.love.controller.user;

import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import com.love.service.VideoRecordService;
import com.love.utils.JwtUtil;
import com.love.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 播放记录
 */
@RestController
@RequestMapping("/api/video/record")
public class VideoRecordController {

    @Autowired
    private VideoRecordService videoRecordService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 添加播放记录
     */
    @PostMapping
    public Result<String> add(@RequestBody VideoRecord videoRecord) {
        Map<String, Object> userInfo = JwtUtil.parseTokenWithValidation(request.getHeader("authorization"));
        String username = (String) userInfo.get("username");
        videoRecordService.add(username, videoRecord.getVideoId());
        return Result.success();
    }

    /**
     * 获取播放记录
     */
    @GetMapping
    public Result<List<Video>> findByUserIdAndVideoId(String username) {
        return Result.success(videoRecordService.findByUserIdAndVideoId(username));
    }
}
