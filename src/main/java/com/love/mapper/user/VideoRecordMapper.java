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

    @Insert("insert into video_record(username,video_id,category_id,create_time)" +
            "values (#{username},#{videoId},#{categoryId},#{createTime})")
    void add(String username, Integer videoId, Integer categoryId, LocalDateTime createTime);

    @Select("select * from video_record where username=#{username} and video_id=#{videoId}")
    VideoRecord findByUserIdAndVideoId(String username, Integer videoId);

    @Update("update video_record set create_time=#{createTime} where username=#{username} and video_id=#{videoId}")
    void updateTime(String username, Integer videoId, LocalDateTime createTime);

    /**
     * 查询用户的视频播放记录，按时间倒序排列
     */
    @Select("select v.id, v.title, v.video_url, v.status, v.cover, v.create_time, " +
            "v.category_id, vr.create_time as view_time " +
            "from video_record vr " +
            "join video v on vr.video_id = v.id " +
            "where vr.username=#{username} " +
            "order by vr.create_time desc")
    List<Video> selectVideoRecord(String username);

    /**
     * 获取用户播放记录列表
     * 注意：此查询会按照观看时间降序排序（最新观看的排在前面）
     */
    @Select("select id, username, video_id, category_id, create_time " + 
            "from video_record " +
            "where username=#{username} " +
            "order by create_time desc")
    List<VideoRecord> userRecordList(String username);
    
    /**
     * 计算用户的播放记录数量
     */
    @Select("select count(*) from video_record where username=#{username}")
    int countUserRecords(String username);


    /**
     * 获取所有有观看记录的用户
     */
    @Select("SELECT DISTINCT username FROM video_record")
    List<String> getAllUsers();

    /**
     * 获取视频的观看次数
     */
    @Select("SELECT COUNT(*) FROM video_record WHERE video_id = #{videoId}")
    int getWatchCount(Integer videoId);
}
