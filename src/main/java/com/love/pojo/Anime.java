package com.love.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anime {
    private Long animeId;
    private String title;//标题
    private String description;//描述
    private String coverImage;//封面图片
    private LocalDate releaseDate;//首播日期
    private String status;//番剧状态
    private Integer recommend;//推荐
    private Long userId;
    private String nickname;
    private String userPic;
    private LocalDateTime updatedAt;//更新时间
}
