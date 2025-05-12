package com.love.mapper.admin;

import com.love.pojo.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminVideoMapper {

    /**
     * 获取草稿视频List
     */
    List<Video> getDraftVideoList();

    /**
     * 拷贝视频到视频表
     */
    void copyVideo(Integer id);

    /**
     * 删除草稿视频
     */
    @Delete("delete from video_draft where id=#{id}")
    void deleteVideo(Integer id);

    @Select("select id, username, nickname, user_pic from user")
    Video getUserInfoById(Long userId);

    @Update("update video set status=#{state} where id=#{id}")
    void updateVideoState(Integer id, int state);
}
