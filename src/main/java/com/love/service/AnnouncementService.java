package com.love.service;

import com.love.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {

    /**
     * 发布公告
     */
    void publish(Announcement announcement);

    /**
     * 获取公告
     */
    List<Announcement> getAnnouncement();

    /**
     * 删除公告
     */
    void deleteAnnouncement(Integer announcementId);

    /**
     * 編輯公告
     */
    void updateAnnouncement(Announcement announcement);
}
