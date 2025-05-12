package com.love.mapper;

import com.love.pojo.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    // 根据标题和简介搜索
    List<Video> search(String keyword);
}
