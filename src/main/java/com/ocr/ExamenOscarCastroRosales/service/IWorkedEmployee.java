package com.ocr.ExamenOscarCastroRosales.service;

import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;
import com.ocr.ExamenOscarCastroRosales.entity.EmployeesWorkedHours;
import com.ocr.ExamenOscarCastroRosales.model.EmployeesSaveModel;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasRequestModel;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasResponse;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioRequest;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioResponse;
import com.ocr.ExamenOscarCastroRosales.model.WorkedEmployeesModel;

public interface IWorkedEmployee {
	
	Page<WorkedEmployeesModel> getWorkedEmployeeAll(Pageable pageable);

	EmployeesWorkedHours saveWorkedHours(WorkedEmployeesModel workedEmployee);
	
	TotalHorasResponse totalHours(TotalHorasRequestModel horasRequestModel) throws ParseException;
	TotalSalarioResponse totalPayments(TotalSalarioRequest totalSalarioRequest) throws ParseException;

	
}
