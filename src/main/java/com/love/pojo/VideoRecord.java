package com.love.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoRecord {
    private Integer id;
    private String username;
    private Integer videoId;
    private LocalDateTime  createTime;
}
