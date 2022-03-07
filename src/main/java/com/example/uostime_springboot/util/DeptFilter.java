package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Dept;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class DeptFilter {
	public List<Dept> getDeletedDept(List<Dept> oldDeptList, List<Dept> newDeptList){
		List<DeptMapper> oldDeptMapper = mapList(oldDeptList);
		List<DeptMapper> newDeptMapper = mapList(newDeptList);

		oldDeptMapper.removeAll(newDeptMapper);

		return oldDeptMapper.stream()
				.map(d -> d.getDeptEntity())
				.collect(Collectors.toList());
	}

	public List<Dept> getAddedDept(List<Dept> oldDeptList, List<Dept> newDeptList){
		List<DeptMapper> oldDeptMapper = mapList(oldDeptList);
		List<DeptMapper> newDeptMapper = mapList(newDeptList);

		newDeptMapper.removeAll(oldDeptMapper);

		return newDeptMapper.stream()
				.map(d -> d.getDeptEntity())
				.collect(Collectors.toList());
	}

	public List<Dept> getUpdatedDept(List<Dept> oldDeptList, List<Dept> newDeptList){
		List<DeptMapper> oldDeptMapper = mapList(oldDeptList);
		List<DeptMapper> newDeptMapper = mapList(newDeptList);

		oldDeptMapper.retainAll(newDeptMapper);
		newDeptMapper.retainAll(oldDeptMapper);

		oldDeptMapper.sort(Comparator.comparing(DeptMapper::getColg)
				.thenComparing(DeptMapper::getDept)
				.thenComparing(DeptMapper::getSubDept));

		newDeptMapper.sort(Comparator.comparing(DeptMapper::getColg)
				.thenComparing(DeptMapper::getDept)
				.thenComparing(DeptMapper::getSubDept));

		for (int i = 0; i < oldDeptMapper.size(); i++){
			oldDeptMapper.get(i).getDeptEntity().updateInfo(newDeptMapper.get(i).getDeptEntity());
		}

		return oldDeptMapper.stream()
				.map(d -> d.getDeptEntity())
				.collect(Collectors.toList());
	}

	public List<DeptMapper> mapList(List<Dept> deptList){
		return deptList.stream()
				.map(d -> new DeptMapper((Dept) d))
				.collect(Collectors.toList());
	}
}
