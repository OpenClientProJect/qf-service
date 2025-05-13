package com.love.controller;

import com.love.pojo.PageBean;
import com.love.pojo.Result;
import com.love.pojo.Video;
import com.love.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    // 搜索视频
    @GetMapping
    public Result<Map<String,Object>> search(String keyword,
                                          Integer pageNum,//当前页
                                          Integer pageSize//每页条数
    ) {
        Map<String,Object> pb = searchService.search(keyword,pageNum,pageSize);
        return Result.success(pb);
    }
}
