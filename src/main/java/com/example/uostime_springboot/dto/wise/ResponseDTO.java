package com.example.uostime_springboot.dto.wise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	private Integer insert;
	private Integer update;
	private Integer delete;
}
