package com.love.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowDto {
    private Long id;
    private Long followUserId;
    private LocalDateTime createTime;
}

