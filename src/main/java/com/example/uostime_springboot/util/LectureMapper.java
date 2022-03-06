package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Lecture;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"lecture"})
public class LectureMapper {
	private String subDept;
	private String subjectDiv;
	private String subjectNo;
	private String classDiv;
	private Integer shyr;
	private Integer credit;
	private String profNm;
	private String dayNightNm;
	private String classType;
	private String classNm;
//	private String etcPermitYn;
//	private String secPermitYn;
	private String year;
	private String term;
	private Lecture lecture;

	public LectureMapper(Lecture lecture){
		this.subDept = lecture.getSubDept();
		this.subjectDiv = lecture.getSubjectDiv();
		this.subjectNo = lecture.getSubjectNo();
		this.classDiv = lecture.getClassDiv();
		this.shyr = lecture.getShyr();
		this.credit = lecture.getCredit();
		this.profNm = lecture.getProfNm();
		this.dayNightNm = lecture.getDayNightNm();
		this.classType = lecture.getClassType();
		this.classNm = lecture.getClassNm();
//		this.etcPermitYn = lecture.getEtcPermitYn();
//		this.secPermitYn = lecture.getSecPermitYn();
		this.year = lecture.getYear();
		this.term = lecture.getTerm();
		this.lecture = lecture;
	}
}
