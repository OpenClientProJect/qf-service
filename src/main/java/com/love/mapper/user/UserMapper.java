package com.love.mapper.user;

import com.love.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
    // 根据用户名或邮箱查询用户信息
    @Select("select * from user where email=#{email} or username=#{username}")
    User findByUserNameOrEmail(User u);
    // 注册
    @Insert("insert into user(username,password,nickname,email,create_time,update_time)" +
            "values (#{username},#{password},#{nickname},#{email},now(),now())")
    void add(User user);
    // 编辑用户信息
    void update(User user);
    // 更新头像
    @Update("update user set user_pic=#{avatarUrl},update_time=NOW() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);
    //修改密码
    @Update("update user set password=#{password},update_time=now() where id=#{id}")
    void updatePassword(String password, Integer id);

    // 根据邮箱查询用户
    @Select("select * from user where email=#{email}")
    User findByUSerEmail(String email);
    // 根据邮箱修改密码
    @Update("update user set password=#{password},update_time=now() where email=#{email}")
    void updatePasswordWhereEmail(String password, String email);
    //查询用户列表
    @Select("select nickname,user_pic from user where id=#{id}")
    List<User> findByUserId(Integer id);
    @Select("select nickname,user_pic from user where username=#{username}")
    List<User> findByUserChat(String username);
    @Update("update user set id=#{uId} where id=#{id}")
    void updateUserId(Integer id, Long uId);
    @Select("select * from user where id=#{userId}")
    User findById(Long userId);
    @Select("select id,username,nickname,sex,email,user_pic,introduction from user where username=#{username}")
    User getUserInfo(String username);


    @Select("select username,nickname,sex,email,user_pic,introduction from user")
    List<User> getAllUsers(User user);

    @Update("update user set status=1 where id=#{userId}")
    void updateUserStatus1(Long userId);

    @Update("update user set status=0 where id= #{userId}")
    void updateUserStatus0(Long userId);

    @Update("update user set role='admin' where id= #{userId}")
    void updateAdmin(Long userId);
}
