package com.love.vo;

import com.love.dto.CommentDto;
import com.love.dto.UserChatMessageDto;
import com.love.dto.VideoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageVo {
    private List<VideoDto> videoLike;//点赞信息
    private List<UserChatMessageDto> userChatMessageDto;
    private List<CommentDto> commentsMessage;

    public UserMessageVo(List<VideoDto> userMassage) {
        this.videoLike = userMassage;
    }
}
