package com.love.controller.user;


import com.love.dto.CommentDto;
import com.love.dto.UserChatMessageDto;
import com.love.dto.VideoDto;
import com.love.pojo.Result;
import com.love.service.User.UserMessageService;
import com.love.utils.ThreadLocalUtil;
import com.love.vo.UserMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/message")
public class UserMessageController {

    @Autowired
    private UserMessageService userMessageService;

    @GetMapping
        public Result<List<UserMessageVo>> getUserMassage(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Long userId = (Long) map.get("id");
        List<VideoDto> userMassage = userMessageService.getUserMassage(userId);
        String username = (String) map.get("username");
        List<UserChatMessageDto> allChatMessage = userMessageService.getAllChatMessage(username);
        List<CommentDto> allCommentsMessage = userMessageService.getAllCommentsMessage(userId);

        List<UserMessageVo> userMessageVo = Collections.singletonList(new UserMessageVo(userMassage, allChatMessage,allCommentsMessage));
        return Result.success(userMessageVo);
    }
}
