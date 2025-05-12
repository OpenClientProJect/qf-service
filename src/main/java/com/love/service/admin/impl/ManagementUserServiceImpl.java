package com.love.service.admin.impl;

import com.love.mapper.admin.ManagementUserMapper;
import com.love.mapper.user.UserMapper;
import com.love.pojo.user.User;
import com.love.service.admin.ManagementUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ManagementUserServiceImpl implements ManagementUserService {

    @Autowired
    private ManagementUserMapper managementUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        //过滤管理员账号
        return managementUserMapper.selectUserList();/*.stream()
                .filter(user -> !"admin".equals(user.getRole()))
                .toList();*/
    }

    @Override
    public Boolean updateUserStatus(Long userId) {
        User userInfo = userMapper.findById(userId);
        if (userInfo == null) {
            throw new RuntimeException("用户不存在");
        }
        Integer status = userInfo.getStatus();
        if (status == null) {
            throw new RuntimeException("用户状态为空");
        }
        if (Objects.equals(userInfo.getRole(), "admin")){
            throw new RuntimeException("管理员账号不能修改状态");
        }
        switch (status) {
            case 0 -> {
                userMapper.updateUserStatus1(userId);
                return true;
            }
            case 1 -> {
                userMapper.updateUserStatus0(userId);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateAdmin(Long userId) {
        userMapper.updateAdmin(userId);
    }
}
