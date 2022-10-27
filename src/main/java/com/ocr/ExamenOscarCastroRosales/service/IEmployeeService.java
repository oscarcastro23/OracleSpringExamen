package com.ocr.ExamenOscarCastroRosales.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;
import com.ocr.ExamenOscarCastroRosales.entity.Genders;
import com.ocr.ExamenOscarCastroRosales.model.BuscarIdJobsEmployee;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeDescriptionModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeesSaveModel;
import com.ocr.ExamenOscarCastroRosales.model.ResponseCreateModel;



public interface IEmployeeService {
	
	Page<EmployeeModel> getAll(Pageable pageable);

	Employees saveEmployee(EmployeesSaveModel employee);
	
	List<EmployeeDescriptionModel> getEmployeeJobs(BuscarIdJobsEmployee idJob );
}
