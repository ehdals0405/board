package com.solo.board.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoticeResponseDto {

    private Long noticeId;
    private String title;
    private String content;
    private String status;
}
