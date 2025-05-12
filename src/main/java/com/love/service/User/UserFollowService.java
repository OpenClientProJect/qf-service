package com.love.service.User;

import com.love.pojo.Result;
import com.love.pojo.user.UserFollowData;
import com.love.pojo.user.UserFollow;

import java.util.List;

public interface UserFollowService {
    Result<String> follow(Long id, Boolean isFollow);
    List<UserFollow> list(Long id);
    // 关注列表
    List<UserFollowData> followList();
}
