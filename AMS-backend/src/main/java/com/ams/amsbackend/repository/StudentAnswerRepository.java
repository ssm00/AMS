package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.StudentAnswerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity,Long> {

    /**
     * studentAnswerEntity에서 studentEntity 의 name 항상 접근
     * fetch join 사용
     * N + 1문제 쿼리 6번 -> 1번
     * fetch join시 oneToMany시 뻥튀기 주의
     * 여기서는 ManyToOne 이라 괜찮음
     * @Query("select sae from StudentAnswerEntity sae join fetch sae.studentEntity se where sae.examNumber = :examNumber order by sae.studentScore Desc join "), pagable 받기
     * 메소드 이름 줄이기 가능... 나중에 고민ㄱ
     */
    @EntityGraph(attributePaths = {"studentEntity"})
    List<StudentAnswerEntity> findTop5ByExamNumberOrderByStudentScoreDesc(Integer examNumber);

}
