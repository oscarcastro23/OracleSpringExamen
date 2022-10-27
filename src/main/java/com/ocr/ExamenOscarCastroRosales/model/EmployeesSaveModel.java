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
public class EmployeesSaveModel {
	
	private Long gender_id;
	
	private Long job_id;
	
	private String name;
	
	private String lastName;
	
	private String birthdate;
}
