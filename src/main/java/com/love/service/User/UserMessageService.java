package com.love.service.User;

import com.love.dto.CommentDto;
import com.love.dto.UserChatMessageDto;
import com.love.dto.VideoDto;

import java.util.List;

public interface UserMessageService {

    /**
     * 用户消息服务
     */
    List<VideoDto> getUserMassage(Long userId);

    /**
     * 私信消息
     */
    List<UserChatMessageDto> getAllChatMessage(String username);

    /**
     * 评论消息
     */
    List<CommentDto> getAllCommentsMessage(Long userId);
}
