package com.love.service;

import com.love.pojo.Video;

import java.util.List;

public interface VideoService {
    // 查询所有视频
    List<Video> list(Integer subCategoryId);
    // 根据id查询视频
    Video findById(Integer id);
    //用户收藏列表
    List<Video> getCollectList(Long userId);
}
