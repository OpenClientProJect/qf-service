package com.love.mapper;

import com.love.pojo.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    /**
     * 发布公告
     */
    @Insert("insert into announcement(title,text,image_url,video_url,create_time)" +
            "values(#{title},#{text},#{imageUrl},#{videoUrl},NOW())")
    void publish(Announcement announcement);

    /**
     * 获取公告
     */
    @Select("select * from announcement order by create_time desc")
    List<Announcement> getAnnouncement();

    /**
     * 删除公告
     */
    @Delete("delete from announcement where announcement_id=#{announcementId}")
    void deleteAnnouncement(Integer announcementId);

    /**
     * 編輯公告
     */
    void updateAnnouncement(Announcement announcement);
}
