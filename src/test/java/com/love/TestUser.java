package com.love;

import com.love.pojo.Video;
import com.love.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestUser {

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private VideoService videoService;

    //获取使用收藏列表
    @Test
    public void getCollectList(){
        List<Video> collectList = videoService.getCollectList(2450284791L);
        System.out.println(collectList);
    }
}
