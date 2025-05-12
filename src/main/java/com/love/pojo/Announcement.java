package com.love.pojo;


import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
public class Announcement {
    private Integer announcementId;
    private String title;
    private String text;
    private String imageUrl;// 封面图片地址
    private String videoUrl;// 视频地址
    private LocalDateTime createTime;
}
