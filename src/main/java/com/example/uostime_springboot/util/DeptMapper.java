package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Dept;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"deptEntity"})
public class DeptMapper {
	private String subDept;
	private String dept;
	private String colg;
	private Dept deptEntity;

	DeptMapper(Dept d){
		this.subDept = d.getSubDept();
		this.dept = d.getDept();
		this.colg = d.getColg();
		this.deptEntity = d;
	}
}
