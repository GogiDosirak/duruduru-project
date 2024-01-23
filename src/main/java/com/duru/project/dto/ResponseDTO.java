package com.duru.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T>{

	//HTTP 응답 상태 코드
	private int status;
	
	
	//실제 응답할 데이터
	private T data;
}
