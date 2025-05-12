package com.love.mapper;

import com.love.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    /**
     * 关注列表
     */
    List<User> FollowList(Long userId);

    /**
     * 粉色列表
     */
    List<User> fansList(Long userId);
}
