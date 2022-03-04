package com.example.uostime_springboot.repository;

import com.example.uostime_springboot.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
	List<Dept> findByDept(String dept);
}

