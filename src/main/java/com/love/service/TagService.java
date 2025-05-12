package com.love.service;

import com.love.pojo.Tag;

import java.util.List;

public interface TagService {

    void publish(Tag tag);

    void deleteTag(Integer tagId);

    void updateTag(Tag tag);

    List<Tag> getFirstTag();

    List<Tag> getLastTag();
}
