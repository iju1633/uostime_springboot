package com.example.uostime_springboot.controller;

import com.example.uostime_springboot.dto.LectureDTO;
import com.example.uostime_springboot.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api")
public class LectureController {
	private final LectureService lectureService;

	@GetMapping("")
	public List<LectureDTO> findAll(@RequestBody LectureDTO lectureDTO){
		return lectureService.findAllLecture(lectureDTO);
	}

	@GetMapping("/subject_no")
	public List<LectureDTO> findBySubjectNo(@RequestBody LectureDTO lectureDTO){
		return lectureService.findBySubjectNo(lectureDTO);
	}

	@GetMapping("/subject_nm")
	public List<LectureDTO> findBySubjectNm(@RequestBody LectureDTO lectureDTO){
		return lectureService.findBySubjectNm(lectureDTO);
	}

	@GetMapping("/sub_dept")
	public List<LectureDTO> findBySubjectDept(@RequestBody LectureDTO lectureDTO){
		return lectureService.findByDept(lectureDTO);
	}

	@GetMapping("/subject_div")
	public List<LectureDTO> findBySubjectDiv(@RequestBody LectureDTO lectureDTO){
		return lectureService.findBySubjectDiv(lectureDTO);
	}

	@GetMapping("/prof_nm")
	public List<LectureDTO> findByProfNm(@RequestBody LectureDTO lectureDTO){
		return lectureService.findByProfNm(lectureDTO);
	}

	@GetMapping("/credit")
	public List<LectureDTO> findByCredit(@RequestBody LectureDTO lectureDTO){
		return lectureService.findByCredit(lectureDTO);
	}

	@GetMapping("/grade")
	public List<LectureDTO> findByGrade(@RequestBody LectureDTO lectureDTO){
		return lectureService.findByGrade(lectureDTO);
	}
}