package com.love.service.admin;

import com.love.pojo.user.User;

import java.util.List;

public interface ManagementUserService {
    List<User> getUserList();

    Boolean updateUserStatus(Long userId);

    void updateAdmin(Long userId);
}
