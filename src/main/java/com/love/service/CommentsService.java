package com.love.service;

import com.love.pojo.Comments;

import java.util.List;

public interface CommentsService {
    // 发布评论
    String publish(Integer id, String content, Long parentId);
    // 根据视频id查询评论
    List<Comments> findCommentsByVideoId(Integer id);
}
