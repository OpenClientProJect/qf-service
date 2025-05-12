package com.love.controller.admin;

import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.service.admin.AdminVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/video")
public class AdminVideoController {

    @Autowired
    private AdminVideoService adminVideoService;

    /**
     * 获取草稿视频列表
     */
    @GetMapping("/getVideoList")
    public Result<List<Video>> getVideoList() {

        List<Video> draftVideoList = adminVideoService.getDraftVideoList();
        return Result.success(draftVideoList);
    }

    /**
     * 审核视频
     */
    @PostMapping("/auditVideo")
    public Result<?> AuditVideo(Integer id,Boolean review) {
        adminVideoService.AuditVideo(id,review);
        return Result.success();
    }
}
