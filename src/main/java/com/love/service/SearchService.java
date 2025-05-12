package com.love.service;

import com.love.pojo.PageBean;
import com.love.pojo.Video;

import java.util.HashMap;

public interface SearchService {
    // 搜索视频
    HashMap<String,Object> search(String keyword, Integer pageNum, Integer pageSize);
}
