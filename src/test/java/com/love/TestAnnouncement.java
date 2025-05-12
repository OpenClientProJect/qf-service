package com.love;

import com.love.pojo.Announcement;
import com.love.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestAnnouncement {

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private AnnouncementService announcementService;
    /**
     * 获取公告
     */
    @Test
    public void getAnnouncementList() {
        List<Announcement> announcement = announcementService.getAnnouncement();
        System.out.println(announcement);
    }
}
