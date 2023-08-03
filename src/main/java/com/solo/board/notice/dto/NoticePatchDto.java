package com.solo.board.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class NoticePatchDto {
        @Positive(message = "1 이상의 게시글 번호를 입력해주세요")
        private long noticeId;
        @NotBlank(message = "수정할 제목 이름을 입력해주세요")
        private String title;
        @NotBlank(message = "수정할 내용을 입력해주세요")
        private String content;

}
