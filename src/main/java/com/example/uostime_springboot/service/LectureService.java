package com.example.uostime_springboot.service;

import com.example.uostime_springboot.domain.Lecture;
import com.example.uostime_springboot.dto.LectureDTO;
import com.example.uostime_springboot.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
	private LectureRepository lectureRepository;

	@Autowired
	public LectureService(LectureRepository lectureRepository){
		this.lectureRepository = lectureRepository;
	}

	public List<LectureDTO> findAllLecture(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();

		return lectureRepository.findByYearAndTerm(year, term).stream()
				.map(l -> new LectureDTO((Lecture) l))  // Lecture -> LectureDTO
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findBySubjectNo(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String subject_no = lectureDTO.getSubject_no();

		return lectureRepository.findByYearAndTermAndSubjectNo(year, term, subject_no).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findBySubjectNm(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String subject_nm = lectureDTO.getSubject_nm();

		return lectureRepository.findByYearAndTermAndSubjectNm(year, term, subject_nm).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findByDept(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String dept = lectureDTO.getSub_dept();

		return lectureRepository.findByYearAndTermAndSubDept(year, term, dept).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findBySubjectDiv(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String subject_div = lectureDTO.getSubject_div();

		return lectureRepository.findByYearAndTermAndSubjectDiv(year, term, subject_div).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findByDeptAndSubjectDiv(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String dept = lectureDTO.getSub_dept();
		String subject_div = lectureDTO.getSubject_div();

		return lectureRepository.findByYearAndTermAndSubDeptAndSubjectDiv(year, term, dept, subject_div).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findByProfNm(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		String prof_nm = lectureDTO.getProf_nm();

		return lectureRepository.findByYearAndTermAndProfNm(year, term, prof_nm).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findByCredit(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		Integer credit = lectureDTO.getCredit();

		return lectureRepository.findByYearAndTermAndCredit(year, term, credit).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}

	public List<LectureDTO> findByGrade(LectureDTO lectureDTO){
		String year = lectureDTO.getYear();
		String term = lectureDTO.getTerm();
		Integer grade = lectureDTO.getShyr();

		return lectureRepository.findByYearAndTermAndShyr(year, term, grade).stream()
				.map(l -> new LectureDTO((Lecture) l))
				.collect(Collectors.toList());
	}
}