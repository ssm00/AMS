package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.Subject;
import org.springframework.data.jpa.repository.EntityGraph;

import com.ams.amsbackend.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity,Long> {


    /**
     * 시험 상위5명 정보에는 studentEntity 의 name 항상 접근
     * fetch join 사용
     * 쿼리 6번 -> 1번
     * fetch join시 oneToMany시 뻥튀기 주의
     * 여기서는 ManyToOne 이라 괜찮음
     * @Query("select sae from StudentAnswerEntity sae join fetch sae.studentEntity se where sae.examNumber = :examNumber order by sae.studentScore Desc join "), pagable 받기
     * 메소드 이름 줄이기 가능... 나중에 고민ㄱ
     */
    @EntityGraph(attributePaths = {"studentEntity"})
    List<StudentAnswerEntity> findTop5ByExamNumberAndExamSubjectOrderByStudentScoreDesc(Integer examNumber, Subject examSubject);


    //Long타입 확인
    StudentAnswerEntity findByStudentEntityAndExamNumberAndExamSubject(StudentEntity studentEntity, int examNumber, Subject examSubject);

    Long countByExamNumberAndExamSubject(Integer examNumber, String examSubject);

    /**
     * 오답률계산을 위한 엔티티 불러오기
     * studentEntity의 정보  사용안하기 때문에 EntityGraph 적용 안함
     * 추후 studentEntity정보 접근시 fetch join 추가 고려
     */
    List<StudentAnswerEntity> findAllByExamNumberAndExamSubject(Integer examNumber, Subject examSubject);

    Long countStudentAnswerEntitiesByExamNumberAndExamSubjectAndStudentEntityIn(Integer examNumber, Subject examSubject, List<StudentEntity> studentEntities);
    StudentAnswerEntity findByStudentEntityAndExamNumberAndExamSubject(StudentEntity studentEntity, Integer examNumber, Subject examSubject);
    List<StudentAnswerEntity> findAllByExamNumberAndExamSubjectAndStudentEntityIn(Integer examNumber, Subject examSubject, List<StudentEntity> studentEntities);
    List<StudentAnswerEntity> findStudentAnswerEntitiesByStudentEntity(StudentEntity studentEntity);
    List<StudentAnswerEntity> findStudentAnswerEntitiesByStudentEntityInAndExamSubject(List<StudentEntity> studentEntities, Subject subject);

}
