package com.love.controller;

import com.love.pojo.Result;
import com.love.pojo.user.User;
import com.love.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {


    @Autowired
    private FollowService followService;
    // 关注列表
    @GetMapping()
    public Result<List<User>> subscribeList(Long userId){

        List<User> userFollows = followService.FollowList(userId);
        return Result.success(userFollows);
    }

    //粉色列表
    @GetMapping("/fans")
    public Result<List<User>> fansList(Long userId){

        List<User> userFans = followService.fansList(userId);
        return Result.success(userFans);
    }
}
