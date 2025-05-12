package com.love.service.impl.user;

import com.love.dto.CommentDto;
import com.love.dto.UserChatMessageDto;
import com.love.dto.VideoDto;
import com.love.mapper.CommentsMapper;
import com.love.mapper.user.UserChatMessageMapper;
import com.love.mapper.user.UserVideoMapper;
import com.love.service.User.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserVideoMapper userVideoMapper;

    @Autowired
    private UserChatMessageMapper userChatMessageMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<VideoDto> getUserMassage(Long userId) {
        return userVideoMapper.listByUserId(userId);
    }

    @Override
    public List<UserChatMessageDto> getAllChatMessage(String username) {
        return userChatMessageMapper.getAllChatMessage(username);
    }

    @Override
    public List<CommentDto> getAllCommentsMessage(Long userId) {
        return commentsMapper.getAllCommentsMessage(userId);
    }
}
