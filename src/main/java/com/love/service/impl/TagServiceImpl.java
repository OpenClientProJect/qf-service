package com.love.service.impl;

import com.love.mapper.TagMapper;
import com.love.pojo.Tag;
import com.love.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
  @Autowired
  public TagMapper tagMapper;
    @Override
    public void publish(Tag tag){
        tagMapper.publish(tag);
    }

    /**
     * 获取公告
     */
    @Override
    public List<Tag> getFirstTag() {
        return tagMapper.getFirstTag();
    }
    @Override
    public List<Tag> getLastTag() {
        return tagMapper.getLastTag();
    }

    /**
     * 删除公告
     */
    @Override
    public void deleteTag(Integer id) {
        tagMapper.deleteTag(id);
    }

    /**
     * 修改公告
     */
    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateTag(tag);
    }
}
