package com.love.mapper.admin;

import com.love.pojo.HomeImage;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminHomeImageMapper {

    // 添加轮播图
    @Insert("insert into home_image(image,create_time,title,video,description)" +
            " values(#{image},NOW(),#{title},#{video},#{description})")
    void add(HomeImage homeImage);

    @Select("select home_img_id,image,create_time,title,video,description from home_image")
    List<HomeImage> getHomeImageList();

    @Select("select id,img from background")
    Map<String, Object> getBackground();

    //更新轮播图
    @Update("update home_image set image=#{image},title=#{title},video=#{video},description=#{description}" +
            " where home_img_id=#{homeImgId}")
    void update(HomeImage homeImage);

    //删除轮播图
    @Delete("delete from home_image where home_img_id=#{homeImgId}")
    void delete(Integer homeImgId);

    @Update("update background set img=#{img} where id = #{id}")
    void updateBackground(Integer id,String img);

    //添加背景图
    @Insert("insert into background(img) values(#{img})")
    void addBackground(String img);
}
