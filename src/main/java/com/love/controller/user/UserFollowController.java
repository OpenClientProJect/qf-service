package com.love.controller.user;

import com.love.anno.Log;
import com.love.pojo.Result;
import com.love.pojo.user.UserFollowData;
import com.love.pojo.user.UserFollow;
import com.love.service.User.UserFollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/follow")
public class UserFollowController {

    @Resource
    UserFollowService userFollowService;

    // 关注或取消关注
    @Log
    @PutMapping("/{id}")
    public Result<String> follow(@PathVariable("id") Long id, Boolean isFollow) {
        return userFollowService.follow(id,isFollow);
    }

    // 获取关注用户信息
    @GetMapping("/{id}")
    public Result<List<UserFollow>> list(@PathVariable("id") Long id) {
        List<UserFollow> list = userFollowService.list(id);
        return Result.success(list);
    }
    //获取关注列表
    @GetMapping("/list")
    public Result<List<UserFollowData>> followList() {
        // TODO 接口未完善
        List<UserFollowData> list = userFollowService.followList();
        return Result.success(list);
    }
}
