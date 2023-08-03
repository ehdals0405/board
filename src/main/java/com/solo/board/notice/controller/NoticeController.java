package com.solo.board.notice.controller;

import com.solo.board.notice.dto.NoticePatchDto;
import com.solo.board.notice.dto.NoticePostDto;
import com.solo.board.notice.dto.NoticeResponseDto;
import com.solo.board.notice.entity.Notice;
import com.solo.board.notice.mapper.NoticeMapper;
import com.solo.board.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/notice")
@Validated
public class NoticeController {
    private final NoticeMapper noticeMapper;
    private final NoticeService noticeService;

    public NoticeController(NoticeMapper noticeMapper, NoticeService noticeService) {
        this.noticeMapper = noticeMapper;
        this.noticeService = noticeService;
    }

    @PostMapping
    public ResponseEntity postNotice(@RequestBody NoticePostDto post) {
        Notice notice = noticeMapper.postDtoToEntity(post);
        noticeService.createNotice(notice);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/{notice-id}")
    public ResponseEntity patchNotice(@PathVariable("notice-id") @Positive long noticeId,
                                      @RequestBody NoticePatchDto patchDto) {
        Notice notice = noticeMapper.patchDtoToEntity(patchDto);
        Notice updatedNotice = noticeService.updateNotice(notice);
        NoticeResponseDto response = noticeMapper.entityToResponse(updatedNotice);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{notice-id}")
    public ResponseEntity getNotice(@PathVariable("notice-id") @Positive long noticeId) {
        Notice getNotice = noticeService.getNotice(noticeId);
        NoticeResponseDto response = noticeMapper.entityToResponse(getNotice);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllNotice(@RequestParam @Positive int page,
                                       @RequestParam @Positive int size) {
        Page<Notice> noticePage = noticeService.getNotices(page, size);

        List<Notice> noticeList = noticePage.getContent();
        List<NoticeResponseDto> responses = noticeMapper.entityListToResponseList(noticeList);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{notice-id}")
    public ResponseEntity deleteNotice(@PathVariable("notice-id") @Positive long noticeId) {
        noticeService.deleteNotice(noticeId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteAllNotice() {
        noticeService.deleteAllNotice();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
