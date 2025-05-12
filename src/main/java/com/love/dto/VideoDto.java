package com.love.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private Long id;
    private Integer videoId;
    private Long userId;
    private String nickname;
    private String userPic;
    private String title;
    private String cover;
    private LocalDateTime likedAt;//点赞时间
}
