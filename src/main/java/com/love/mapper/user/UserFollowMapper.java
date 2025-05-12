package com.love.mapper.user;

import com.love.pojo.user.UserFollowData;
import com.love.pojo.user.UserFollow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserFollowMapper {
    // 关注
    @Insert("insert into user_follow(user_id, follow_user_id,create_time) values(#{userId}, #{id},NOW())")
    void userFollow(Long userId, Long id);
    // 取消关注
    @Delete("delete from user_follow where user_id = #{userId} and follow_user_id = #{id}")
    void userUnFollow(Long userId, Long id);

    // 查询关注信息
    @Select("select * from user_follow where user_id = #{userId} and follow_user_id = #{id}")
    List<UserFollow> list(Long userId, Long id);
    // 查询关注列表
    @Select("select follow_user_id,create_time from user_follow where follow_user_id = #{id}")
    List<UserFollow> followList(Long id);
    // 查询关注
    @Select("select count(id) from user where id = #{id}")
    int findByUsername(Long id);
    List<UserFollowData> getUserFollow(Long id);
    // 查询关注数
    @Select("select count(id) from user_follow where user_id = #{id}")
    int followCount(Long id);
    // 查询粉丝数
    @Select("select count(id) from user_follow where follow_user_id = #{id}")
    int fansCount(Long id);
    // 查询用户视频数
    @Select("select count(id) from video where user_id = #{id}")
    int videoCount(Long id);
}
