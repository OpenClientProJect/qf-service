package com.love.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeImage {
    private Integer homeImgId;
    private String image;
    private LocalDateTime createTime;
    private String title;
    private String video;
    private String description;
}
