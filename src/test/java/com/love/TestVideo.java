package com.love;

import com.love.mapper.VideoMapper;
import com.love.pojo.Video;
import com.love.service.admin.AdminVideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestVideo {

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private AdminVideoService adminVideoService;

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void test(){
//        adminVideoService.AuditVideo(93, );
    }

    @Test
    public void test2(){
        List<Video> list = videoMapper.list(1);
        System.out.println(list);

    }

}
