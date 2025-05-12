package com.love.service.impl;

import com.love.mapper.CommentsMapper;
import com.love.pojo.Comments;
import com.love.service.CommentsService;
import com.love.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsMapper commentsMapper;
    @Override
    public String publish(Integer id, String comments, Long parentId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        if (map == null){
            return "请先登录后在发布评论";
        }
        Comments comment = new Comments();
        comment.setVideoId(id);
        comment.setUserId((Long) map.get("id"));
        comment.setContent(comments);
        comment.setParentId(parentId);
        commentsMapper.publish(comment);
        return null;
    }

    // 查询评论
    @Override
    public List<Comments> findCommentsByVideoId(Integer id) {
        return commentsMapper.findCommentsByVideoId(id);
    }
}
