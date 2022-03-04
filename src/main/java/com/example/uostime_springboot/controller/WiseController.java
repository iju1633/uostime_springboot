package com.example.uostime_springboot.controller;

import com.example.uostime_springboot.dto.RequestDTO;
import com.example.uostime_springboot.service.WiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("wise")
public class WiseController {
	private final WiseService wiseService;

	@PutMapping("/lecture")
	public String updateLecture(@RequestBody RequestDTO requestDTO){
		return wiseService.updateLecture(requestDTO).toString();
	}

//	@PutMapping("/dept")
//	public void updateDept(){
//
//	}
}
