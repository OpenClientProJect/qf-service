package com.love.service.admin;

import com.love.pojo.HomeImage;

import java.util.List;
import java.util.Map;

public interface AdminHomeImageService {

    void add(HomeImage homeImage);

    List<HomeImage> getHomeImageList();

    Map<String,Object> getBackground();

    void update(HomeImage homeImage);

    void delete(Integer homeImgId);
    //更新背景图
    void updateBackground(Integer id,String img);
    //添加背景图
    void addBackground(String img);
}
