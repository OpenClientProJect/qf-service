package com.love.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private Integer activityId;
    private String text;
    private String title;
    private String image;
    private LocalDateTime createTime;
}
