package kopo.poly.service.impl;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.repository.NoticeJoinRepository;
import kopo.poly.repository.entity.NoticeJoinEntity;
import kopo.poly.service.INoticeJoinService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticejoinService implements INoticeJoinService {

    private final NoticeJoinRepository noticeJoinRepository; // @JoinColumn 적용된 공지사항

    @Override
    public List<NoticeDTO> getNoticeListUsingJoinColumn() {
        log.info(this.getClass().getName() + ".getNoticeListUsingJoinColumn Start!");

        // 공지사항 전체 리스트 조회하기
        List<NoticeJoinEntity> rList = noticeJoinRepository.findAllByOrderByNoticeSeqDesc();

        List<NoticeDTO> list = new LinkedList<>(); // 조회 결과를 List<NoticeDTO> 변환하기 위해 사용

        rList.forEach(rEntity -> {  // 람다식 사용

            // Entity 결과를 DTO 저장하기위해 결과 변수를 담기
            long noticeSeq = rEntity.getNoticeSeq(); // 공지사항 순번 PK
            String noticeYn = CmmUtil.nvl(rEntity.getNoticeYn());   // 공지글 여부
            String title = CmmUtil.nvl(rEntity.getTitle()); //  공지사항 제목
            long readCnt = rEntity.getReadCnt();    // 공지사항 조회수
            String userName = CmmUtil.nvl(rEntity.getUserInfoEntity().getUsername());   // 공지사항 작성자(JoinColumn) 활용
            String regDt = CmmUtil.nvl(rEntity.getRegDt()); // 공지사항 작성일시

            // 로그 출력
            log.info("noticeSeq: " + noticeSeq);
            log.info("noticeYn: " + noticeYn);
            log.info("title: " + title);
            log.info("readCnt: " + readCnt);
            log.info("userName: " + userName);
            log.info("regDt: " + regDt);
            log.info("-----------------------------------");

            // Entity 결과를 DTO 저장하기
            NoticeDTO rDTO = NoticeDTO.builder()
                    .noticeSeq(noticeSeq).noticeYn(noticeYn)
                    .title(title).readCnt(readCnt).userName(userName).regDt(regDt).build();

            list.add(rDTO);

        });

        log.info(this.getClass().getName() + ".getNoticeListUsingJoinColumn End!");

        return list;
    }
}
