package com.love.controller.admin;

import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.service.admin.AdminVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/video")
public class AdminVideoController {

    @Autowired
    private AdminVideoService adminVideoService;

    /**
     * 获取审核频列表
     */
    @GetMapping("/getVideoList")
    public Result<List<Video>> getVideoList() {

        List<Video> draftVideoList = adminVideoService.getDraftVideoList();
        return Result.success(draftVideoList);
    }

    /**
     * 审核视频
     */
    @PutMapping("/auditVideo")
    public Result<?> AuditVideo(Integer id,Boolean review ) {
        adminVideoService.AuditVideo(id,review);

        return Result.success();
    }
}
