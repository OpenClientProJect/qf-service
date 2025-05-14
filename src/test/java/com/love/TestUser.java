package com.love;

import com.love.anno.Log;
import com.love.mapper.user.VideoRecordMapper;
import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import com.love.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestUser {

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoRecordMapper videoRecordMapper;

    //获取使用收藏列表
    @Test
    public void getCollectList(){
        List<Video> collectList = videoService.getCollectList(2450284791L);
        System.out.println(collectList);
    }

    @Test
    public void getVideoList(){
        String username = "24895020387";
        List<VideoRecord> videoRecords = videoRecordMapper.userRecordList(username);
        log.info("视频播放时间排序:{}",videoRecords);
    }
}
