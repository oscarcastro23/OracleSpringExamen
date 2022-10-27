package com.ocr.ExamenOscarCastroRosales.model;

import com.fasterxml.jackson.annotation.JsonRootName;

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
public class EmployeeDescriptionModel {
	
	
	private EmployeeModel employee = new EmployeeModel();
	private Boolean success;
	
	
}
