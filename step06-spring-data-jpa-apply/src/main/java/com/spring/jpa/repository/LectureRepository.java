package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>{

	@Query("select l from Lecture l join fetch l.students")
	List<Lecture> findAllWithFetchJoin();

	@Query("select l from Lecture l left join fetch l.students")
	List<Lecture> findAllWithOuterFetchJoin();

	@EntityGraph(attributePaths = {"students"}) //엔터티의 필드값을 적어주면 됨. // @EntityGraph를 통해 students 필드에 대한 내용을 join을 해서 한번에 가져옴.
	@Query("select l from Lecture l ")
	List<Lecture> findAllWithEntityGraph();
	
}
