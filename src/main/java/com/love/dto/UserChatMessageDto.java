package com.love.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatMessageDto {
    private Integer id;
    private String nickname;
    private String username;
    private String userPic;
    private String content;//消息内容
    private LocalDateTime sendTime;//发送时间
}
