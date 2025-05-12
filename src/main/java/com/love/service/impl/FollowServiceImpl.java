package com.love.service.impl;

import com.love.mapper.FollowMapper;
import com.love.pojo.user.User;
import com.love.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<User> FollowList(Long userId) {
        List<User> users = followMapper.FollowList(userId);
        for (User user : users) {
            user.setRole(null);
        }
        return users;
    }

    @Override
    public List<User> fansList(Long userId) {

        List<User> users = followMapper.fansList(userId);
        for (User user : users){
            user.setRole(null);
        }
        return users;
    }


}
