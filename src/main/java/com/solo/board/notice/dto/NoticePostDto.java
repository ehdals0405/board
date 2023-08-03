package com.solo.board.notice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class NoticePostDto {
    @NotBlank(message = "제목을 입력해야합니다.")
    private String title;
    @NotBlank(message = "내용을 입력해야합니다.")
    private String content;
}
