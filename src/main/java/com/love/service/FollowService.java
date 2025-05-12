package com.love.service;

import com.love.pojo.user.User;

import java.util.List;

public interface FollowService {

    /**
     * 关注列表
     */
    List<User> FollowList(Long userId);

    /**
     * 粉色列表
     */
    List<User> fansList(Long userId);
}
