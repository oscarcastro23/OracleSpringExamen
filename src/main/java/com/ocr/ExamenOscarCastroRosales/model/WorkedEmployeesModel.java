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
public class WorkedEmployeesModel {
	
	private Long employees;
	
	private Long workedHours;
	
	private String workedDate;
}
