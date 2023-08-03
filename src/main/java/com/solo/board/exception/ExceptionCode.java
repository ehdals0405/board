package com.solo.board.exception;

import lombok.Getter;

public enum ExceptionCode {
    NOTICE_NOT_FOUND("게시글을 찾을 수 없습니다."),
    NOTICE_DELETED("삭제된 게시글입니다.");

    @Getter
    private String message;

    ExceptionCode(String message) {
        this.message = message;
    }
}
