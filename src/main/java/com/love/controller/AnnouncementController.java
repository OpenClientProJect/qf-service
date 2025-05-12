package com.love.controller;

import com.love.pojo.Announcement;
import com.love.pojo.Result;
import com.love.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 发布公告
     */
    @PostMapping
    public Result<?> publish(@RequestBody Announcement announcement) {
        announcementService.publish(announcement);
        return Result.success();
    }

    /**
     * 获取公告列表
     */
    @GetMapping
    public Result<List<Announcement>> getAnnouncementList() {
        List<Announcement> announcements = announcementService.getAnnouncement();
        return Result.success(announcements);
    }

    /**
     * 删除公告
     */
    @DeleteMapping
    public Result<?> deleteAnnouncement(@RequestParam Integer announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return Result.success();
    }

    /**
     * 修改公告
     */
    @PutMapping
    public Result<?> updateAnnouncement(@RequestBody Announcement announcement) {
        announcementService.updateAnnouncement(announcement);
        return Result.success();
    }
}
