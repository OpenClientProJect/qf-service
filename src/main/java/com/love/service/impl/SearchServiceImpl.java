package com.love.service.impl;

import com.love.mapper.SearchMapper;
import com.love.pojo.Video;
import com.love.pojo.user.User;
import com.love.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;


    @Override
    public HashMap<String, Object> search(String keyword, Integer pageNum, Integer pageSize) {
        List<Video> video = searchMapper.search(keyword);
        List<User> users = searchMapper.searchUser(keyword);

        HashMap<String, Object> result = new HashMap<>();

        result.put("video",video);
        result.put("users",users);
        return result;
    }
}
