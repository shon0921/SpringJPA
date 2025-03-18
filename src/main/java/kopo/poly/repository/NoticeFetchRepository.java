package kopo.poly.repository;

import kopo.poly.repository.entity.NoticeFetchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeFetchRepository extends JpaRepository<NoticeFetchEntity, Long> {

    /*
        공지사항 리스트
     */
    @Query("SELECT A FROM NoticeFetchEntity A JOIN FETCH A.userInfo ORDER BY A.noticeYn desc , A.noticeSeq DESC")
    List<NoticeFetchEntity> getListFetchJoin();

}
