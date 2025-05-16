package com.love.mapper;

import com.love.pojo.Video;
import com.love.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    // 搜索视频
    List<Video> searchVideos(String keyword);
    
    // 搜索番剧
    List<Video> searchAnimes(String keyword);

    // 搜索用户
    List<User> searchUser(String keyword);
}
