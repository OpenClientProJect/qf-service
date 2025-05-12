package com.love.mapper.admin;

import com.love.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagementUserMapper {

    @Select("select * from user")
    List<User> selectUserList();
}
