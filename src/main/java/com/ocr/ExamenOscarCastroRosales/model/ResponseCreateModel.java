package com.ocr.ExamenOscarCastroRosales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ResponseCreateModel {
	
	private Object response;
	
	private Boolean success;
}
