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
public class EmployeeModel {
	
	private Long id;
	private String name;
	private String lastName;
	private String birthdate;
	
	private JobsModel jobs = new JobsModel();
	private GenderModel gender = new GenderModel();
}
