package com.example.uostime_springboot.repository;

import com.example.uostime_springboot.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.used = true")
	List<Lecture> findByYearAndTerm(String year, String term);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.subjectNo = :subject_no and l.used = true")
	List<Lecture> findByYearAndTermAndSubjectNo(String year, String term, String subject_no);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.subjectNm = :subject_nm and l.used = true")
	List<Lecture> findByYearAndTermAndSubjectNm(String year, String term, String subject_nm);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.subjectDiv = :subject_div and l.used = true")
	List<Lecture> findByYearAndTermAndSubjectDiv(String year, String term, String subject_div);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.subDept = :sub_dept and l.used = true")
	List<Lecture> findByYearAndTermAndSubDept(String year, String term, String sub_dept);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.subDept = :sub_dept and l.subjectDiv = :subject_div and l.used = true")
	List<Lecture> findByYearAndTermAndSubDeptAndSubjectDiv(String year, String term, String sub_dept, String subject_div);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.profNm = :prof_nm and l.used = true")
	List<Lecture> findByYearAndTermAndProfNm(String year, String term, String prof_nm);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.credit = :credit and l.used = true")
	List<Lecture> findByYearAndTermAndCredit(String year, String term, Integer credit);

	@Query("select l from Lecture l where l.year = :year and l.term = :term and l.shyr = :shyr and l.used = true")
	List<Lecture> findByYearAndTermAndShyr(String year, String term, Integer shyr);
}

