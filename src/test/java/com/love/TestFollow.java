package com.love;

import com.love.pojo.user.User;
import com.love.service.FollowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestFollow {


    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private FollowService followService;

    @Test
    public void FollowList(){

        Long UserId = 2450284791L;

        List<User> users = followService.FollowList(UserId);

        System.out.println(users);

    }

    /**
     * 粉色列表
     */
    @Test
    public void fansList(){

        Long UserId = 2450284791L;
        List<User> users = followService.fansList(UserId);
        System.out.println(users);
    }
}
