package com.love.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private Integer videoId;
    private String title;//视频标题
    private String videoCover;//视频封面
    private Long userId;
    private String userPic;
    private String nickname;
    private String content;//评论内容
    private LocalDateTime createTime;//评论时间
}
