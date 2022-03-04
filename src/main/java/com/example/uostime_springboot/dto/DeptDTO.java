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
	private String	dept;
	private String	dept_code_nm;
	private String	dept_nm;
	private String	up_nm;
	private String	up_dept;
	private String	dept_div;
	private String	colg;
	private String	colg_nm;

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
	}

	// dto -> entity
	// save DB
	public Dept toEntity(){
		return Dept.builder()
				.id(id)
				.ptrOrd(ptr_ord)
				.dept(dept)
				.deptCodeNm(dept_code_nm)
				.deptNm(dept_nm)
				.upNm(up_nm)
				.dept(up_dept)
				.deptDiv(dept_div)
				.colg(colg)
				.colgNm(colg_nm)
				.build();
	}
}
