package com.love;

import com.love.dto.UserChatMessageDto;
import com.love.service.User.UserMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestMassage {

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Autowired
    private UserMessageService userMessageService;


    @Test
    public void getMassage() {
        String username = "245678780817";
          for (UserChatMessageDto userChatMessageDto : userMessageService.getAllChatMessage(username)) {
              System.out.println(userChatMessageDto);
        }
    }
}
