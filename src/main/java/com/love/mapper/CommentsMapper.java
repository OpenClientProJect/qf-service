package com.love.mapper;

import com.love.dto.CommentDto;
import com.love.pojo.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {
    //发表评论
    @Insert("insert into comments(video_id,user_id,content,create_time,update_time,parent_id,status,like_count) " +
            "values(#{videoId},#{userId},#{content},NOW(),NOW(),#{parentId},#{status},#{likeCount})")
    void publish(Comments comments);
    //根据视频id查询评论
    List<Comments> findCommentsByVideoId(Integer id);

    //获取用户评论消息
    List<CommentDto> getAllCommentsMessage(Long userId);
}
