package com.example.uostime_springboot.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sub_dept")
	private String subDept;

	@Column(name = "subject_div")
	private String subjectDiv;

	@Column(name = "subject_div2")
	private String subjectDiv2;

	@Column(name = "subject_no")
	private String subjectNo;

	@Column(name = "class_div")
	private String classDiv;

	@Column(name = "subject_nm")
	private String subjectNm;

	private Integer shyr; // grade

	private Integer credit;

	@Column(name = "prof_nm")
	private String profNm;

	@Column(name = "day_night_nm")
	private String dayNightNm;

	@Column(name = "class_type")
	private String classType;

	@Column(name = "class_nm")
	private String classNm;

	@Column(name = "tlsn_count")
	private Integer tlsnCount; // 수강 인원

	@Column(name = "tlsn_limit_count")
	private Integer tlsnLimitCount; // 수강 정원

	@Column(name = "etc_permit_yn")
	private String etcPermitYn; // 타과 허용

	@Column(name = "sec_permit_yn")
	private String secPermitYn; // 복수전공 허용

	private String year;

	private String term;

	private Boolean used;

	public void changeUsedFlag(Boolean b){
		this.used = b;
	}

	public void updateInfo(Lecture l){
		this.tlsnCount = l.getTlsnCount();
		this.tlsnLimitCount = l.getTlsnLimitCount();
		this.etcPermitYn = l.getEtcPermitYn();
		this.secPermitYn = l.getSecPermitYn();
		this.subjectDiv2 = l.getSubjectDiv2();
		this.subjectNm = l.getSubjectNm();
	}
}
