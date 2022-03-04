package com.example.uostime_springboot.dto;

import com.example.uostime_springboot.domain.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LectureDTO {
	private Long id;
	private String sub_dept;
	private String subject_div;
	private String subject_div2;
	private String subject_no;
	private String class_div;
	private String subject_nm;
	private Integer shyr; // grade
	private Integer credit;
	private String prof_nm;
	private String day_night_nm;
	private String class_type;
	private String class_nm;
	private Integer tlsn_count; // 수강 인원
	private Integer tlsn_limit_count; // 수강 정원
	private String etc_permit_yn; // 타과 허용
	private String sec_permit_yn; // 복수전공 허용
	private String year;
	private String term;
	private Boolean used;

	public LectureDTO(Lecture entity){
		this.id = entity.getId();
		this.sub_dept = entity.getSubDept();
		this.subject_div = entity.getSubjectDiv();
		this.subject_div2 = entity.getSubjectDiv2();
		this.subject_no = entity.getSubjectNo();
		this.class_div = entity.getClassDiv();
		this.subject_nm = entity.getSubjectNm();
		this.shyr = entity.getShyr();
		this.credit = entity.getCredit();
		this.prof_nm = entity.getProfNm();
		this.day_night_nm = entity.getDayNightNm();
		this.class_type = entity.getClassType();
		this.class_nm = entity.getClassNm();
		this.tlsn_count = entity.getTlsnCount();
		this.tlsn_limit_count = entity.getTlsnLimitCount();
		this.etc_permit_yn = entity.getEtcPermitYn();
		this.sec_permit_yn = entity.getSecPermitYn();
		this.year = entity.getYear();
		this.term = entity.getTerm();
		this.used = entity.getUsed();
	}

	public Lecture toEntity(){
		return Lecture.builder()
				.id(id)
				.subDept(sub_dept)
				.subjectDiv(subject_div)
				.subjectDiv2(subject_div2)
				.subjectNo(subject_no)
				.classDiv(class_div)
				.subjectNm(subject_nm)
				.shyr(shyr)
				.credit(credit)
				.profNm(prof_nm)
				.dayNightNm(day_night_nm)
				.classType(class_type)
				.classNm(class_nm)
				.tlsnCount(tlsn_count)
				.tlsnLimitCount(tlsn_limit_count)
				.etcPermitYn(etc_permit_yn)
				.secPermitYn(sec_permit_yn)
				.year(year)
				.term(term)
				.used(used)
				.build();
	}
}