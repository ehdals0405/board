package com.solo.board.notice.service;

import com.solo.board.exception.BusinessException;
import com.solo.board.exception.ExceptionCode;
import com.solo.board.notice.entity.Notice;
import com.solo.board.notice.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.solo.board.notice.entity.Notice.Status.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice updateNotice(Notice notice) {

        Notice findNotice = findVerifyNotice(notice.getNoticeId());
        findNotice.setTitle(notice.getTitle());
        findNotice.setContent(notice.getContent());

        return findNotice;
    }
    @Transactional(readOnly = true)
    public Notice getNotice(long noticeId) {
        return findVerifyNotice(noticeId);
    }
    @Transactional(readOnly = true)
    public Page<Notice> getNotices(int page, int size){

        return noticeRepository.findAllByStatus(POSTED,PageRequest.of(page-1,size,Sort.by("noticeId")));

    }
    public void deleteNotice(long noticeId){
        Notice findNotice = findVerifyNotice(noticeId);
        findNotice.setStatus(DELETED);
    }
    public void deleteAllNotice(){
        List<Notice> noticeList = noticeRepository.findAll();
        noticeList.stream()
                .forEach(notice -> notice.setStatus(DELETED));
    }

    private Notice findVerifyNotice(long noticeId) {

        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        Notice findNotice = optionalNotice.orElseThrow(() -> new BusinessException(ExceptionCode.NOTICE_NOT_FOUND));

        if(findNotice.getStatus().equals("DELETED")) throw new BusinessException(ExceptionCode.NOTICE_DELETED);

        return findNotice;
    }
}
