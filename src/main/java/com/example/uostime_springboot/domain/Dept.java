package com.example.uostime_springboot.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ptr_ord")
	private String ptrOrd;

	@Column(name = "dept")
	private String subDept;	// subDept

	@Column(name = "dept_code_nm")
	private String deptCodeNm;

	@Column(name = "dept_nm")
	private String deptNm;

	@Column(name = "up_nm")
	private String upNm;

	@Column(name = "up_dept")
	private String dept;	// dept

	@Column(name = "dept_div")
	private String deptDiv;

	@Column(name = "colg")
	private String colg;	// deptDiv

	@Column(name = "colg_nm")
	private String colgNm;

	private Boolean used;

	public void changeUsedFlag(Boolean b){
		this.used = b;
	}

	public void updateInfo(Dept d){
		this.ptrOrd = d.getPtrOrd();
		this.deptCodeNm = d.getDeptCodeNm();
		this.deptNm = d.getDeptNm();
		this.upNm = d.getUpNm();
		this.deptDiv = d.getDeptDiv();
		this.colgNm = d.getColgNm();
	}
}
