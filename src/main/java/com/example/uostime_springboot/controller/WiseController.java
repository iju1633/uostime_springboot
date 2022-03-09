package com.example.uostime_springboot.controller;

import com.example.uostime_springboot.dto.wise.RequestDTO;
import com.example.uostime_springboot.dto.wise.ResponseDTO;
import com.example.uostime_springboot.service.DeptService;
import com.example.uostime_springboot.service.WiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("wise")
public class WiseController {
	private final WiseService wiseService;
	private final DeptService deptService;

	@PutMapping("/lecture")
	public ResponseDTO updateLecture(@RequestBody RequestDTO requestDTO){
		return wiseService.updateLecture(requestDTO);
	}

	@PutMapping("/dept")
	public ResponseDTO updateDept() throws ParserConfigurationException, IOException, SAXException {
		return deptService.updateDept();
	}
}

