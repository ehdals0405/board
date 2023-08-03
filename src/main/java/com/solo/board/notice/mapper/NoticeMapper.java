package com.solo.board.notice.mapper;

import com.solo.board.notice.dto.NoticePatchDto;
import com.solo.board.notice.dto.NoticePostDto;
import com.solo.board.notice.dto.NoticeResponseDto;
import com.solo.board.notice.entity.Notice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticeMapper {

    Notice postDtoToEntity(NoticePostDto postDto);

    Notice patchDtoToEntity(NoticePatchDto patchDto);

    NoticeResponseDto entityToResponse(Notice notice);

    List<NoticeResponseDto> entityListToResponseList(List<Notice> list);

}
