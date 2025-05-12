package com.love.controller.admin;

import com.love.pojo.Result;
import com.love.pojo.user.User;
import com.love.service.User.UserService;
import com.love.service.admin.ManagementUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/managementUser")
public class ManagementUserController {

    @Autowired
    private ManagementUserService managementUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/userList")
    public Result<List<User>> getUserList() {
        List<User> userList = managementUserService.getUserList();
        return Result.success(userList);
    }

    /**
     * 更新用户状态
     */
    @PutMapping("/{userId}")
    public Result<String> updateUserStatus(@PathVariable Long userId) {
        Boolean updateUserStatus = managementUserService.updateUserStatus(userId);
        if (updateUserStatus) {
            stringRedisTemplate.delete(userService.findByUserId(userId).getUsername()+"token");
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    @PutMapping("/role/{userId}")
    public Result<String> updateAdmin(@PathVariable Long userId){
        managementUserService.updateAdmin(userId);
        return Result.success("更新成功");
    }
}
