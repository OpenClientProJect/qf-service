package com.love.mapper.user;

import com.love.pojo.Video;
import com.love.pojo.VideoRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface VideoRecordMapper {


    @Insert("insert into video_record(username,video_id,create_time)" +
            "values (#{username},#{videoId},#{createTime})")
    void add(String username, Integer videoId, LocalDateTime createTime);

    @Select("select * from video_record where username=#{username} and video_id=#{videoId}")
    VideoRecord findByUserIdAndVideoId(String username, Integer videoId);

    @Update("update video_record set create_time=#{createTime} where username=#{username} and video_id=#{videoId}")
    void updateTime(String username, Integer videoId, LocalDateTime createTime);

    @Select("select v.id,v.title, v.video_url,v.status,v.cover,v.status,v.create_time " +
            " from video_record vr " +
            "join video v on vr.video_id = v.id " +
            "where username=#{username}")
    List<Video> selectVideoRecord(String username);
}
