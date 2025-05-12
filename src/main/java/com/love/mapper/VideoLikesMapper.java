package com.love.mapper;

import com.love.pojo.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoLikesMapper {

    /**
     * 用户点赞列表
     */
    List<Video> findVideoLikeList(Long id);
}
