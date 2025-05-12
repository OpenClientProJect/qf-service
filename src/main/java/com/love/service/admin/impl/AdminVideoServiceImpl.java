package com.love.service.admin.impl;

import com.love.mapper.admin.AdminVideoMapper;
import com.love.pojo.Video;
import com.love.service.admin.AdminVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminVideoServiceImpl implements AdminVideoService {

    @Autowired
    private AdminVideoMapper adminVideoMapper;


    @Override
    public List<Video> getDraftVideoList() {
        return adminVideoMapper.getDraftVideoList();
    }

    /**
     * 视频审核
     */
    @Override
    public void AuditVideo(Integer id, Boolean review) {
        if (review) {
            adminVideoMapper.updateVideoState(id, 2);
        } else {
            adminVideoMapper.updateVideoState(id, 3);
        }
    }
}
