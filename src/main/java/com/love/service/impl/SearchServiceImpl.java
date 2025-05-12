package com.love.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.love.mapper.SearchMapper;
import com.love.pojo.PageBean;
import com.love.pojo.Video;
import com.love.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;


    @Override
    public PageBean<Video> search(String keyword, Integer pageNum, Integer pageSize) {
        // 封装分页数据
        PageBean<Video> pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Video> as = searchMapper.search(keyword);
        //Page提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据
        Page<Video> p = (Page<Video>) as;
        //把数据填充到PageBean中
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return pb;
    }
}
