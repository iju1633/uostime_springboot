package com.example.uostime_springboot.dto;

import com.example.uostime_springboot.domain.Dept;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeptDTO {
	private Long	id;
	private String	ptr_ord;
	private String	dept; // sub dept
	private String	dept_code_nm;
	private String	dept_nm;
	private String	up_nm;
	private String	up_dept; // dept
	private String	dept_div;
	private String	colg; // dept_div
	private String	colg_nm;
	private Boolean used;

	// entity -> dto (when construct)
	// search from DB
	public DeptDTO(Dept entity){
		this.id = entity.getId();
		this.ptr_ord = entity.getPtrOrd();
		this.dept = entity.getDept();
		this.dept_code_nm = entity.getDeptCodeNm();
		this.dept_nm = entity.getDeptNm();
		this.up_nm = entity.getUpNm();
		this.up_dept = entity.getDept();
		this.dept_div = entity.getDeptDiv();
		this.colg = entity.getColg();
		this.colg_nm = entity.getColgNm();
		this.used = entity.getUsed();
	}

	// dto -> entity
	// save DB
	public Dept toEntity(){
		return Dept.builder()
				.ptrOrd(ptr_ord)
				.subDept(dept)
				.deptCodeNm(dept_code_nm)
				.deptNm(dept_nm)
				.upNm(up_nm)
				.dept(up_dept)
				.deptDiv(dept_div)
				.colg(colg)
				.colgNm(colg_nm)
				.used(used)
				.build();
	}
}
