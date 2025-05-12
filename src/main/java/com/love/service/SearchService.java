package com.love.service;

import com.love.pojo.PageBean;
import com.love.pojo.Video;

public interface SearchService {
    // 搜索视频
    PageBean<Video> search(String keyword, Integer pageNum, Integer pageSize);
}
