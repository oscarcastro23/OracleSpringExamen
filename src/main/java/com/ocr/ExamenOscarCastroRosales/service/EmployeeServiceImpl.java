package com.ocr.ExamenOscarCastroRosales.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;
import com.ocr.ExamenOscarCastroRosales.entity.Genders;
import com.ocr.ExamenOscarCastroRosales.entity.Jobs;
import com.ocr.ExamenOscarCastroRosales.exception.BusinessException;
import com.ocr.ExamenOscarCastroRosales.model.BuscarIdJobsEmployee;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeDescriptionModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeesSaveModel;
import com.ocr.ExamenOscarCastroRosales.model.GenderModel;
import com.ocr.ExamenOscarCastroRosales.model.JobsModel;
import com.ocr.ExamenOscarCastroRosales.model.ResponseCreateModel;
import com.ocr.ExamenOscarCastroRosales.repository.EmployeesRepository;
import com.ocr.ExamenOscarCastroRosales.repository.GenderRepository;
import com.ocr.ExamenOscarCastroRosales.repository.JobsRepository;



@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeesRepository serviceEmployee;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GenderRepository genderRepository;
	
	@Autowired
	private JobsRepository jobsRepository;
	
	
	@Override
	public Page<EmployeeModel> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		 return serviceEmployee.findAll(pageable).map(pE->modelMapper.map(pE, EmployeeModel.class));
	
		
	}

	@Override
	public Employees saveEmployee(EmployeesSaveModel employee) {
		// TODO Auto-generated method stub
		
		Genders genders = genderRepository.findById(employee.getGender_id()).orElse(null);
		Jobs jobs = jobsRepository.findById(employee.getJob_id()).orElse(null);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(employee.getBirthdate(), formatter);
		Period edad = Period.between(fechaNacimiento, LocalDate.now());
		Employees temp = serviceEmployee.findByNameAndLastName(employee.getName(), employee.getLastName());
		if ( temp != null) {
			throw new BusinessException("ya existe un registro con el mismo nombre y apellido");
		}
		
		if (edad.getYears() < 18   ) {
			throw new BusinessException("La persona no es mayor de edad");
		}
		if (genders.getName().isEmpty() || genders == null) {
			throw new BusinessException("El genero no existe en la tabla");
		}
		
		if ( jobs.getName().isEmpty() || jobs == null) {
			throw new BusinessException("El trabajo no existe en la tabla");
		}
		Employees employees = modelMapper.map(employee, Employees.class);
		employees.setGender(genders);    ;
		employees.setJobs(jobs);
		
		Employees employeesResponse =  serviceEmployee.save(employees);
		
		
		return employeesResponse;
	}

	@Override
	public List<EmployeeDescriptionModel> getEmployeeJobs(BuscarIdJobsEmployee idJob) {
		// TODO Auto-generated method stub
	    Jobs jobsExits = jobsRepository.findById(idJob.getJob_id()).orElse(null);
		
	    if (jobsExits == null) {
			throw new BusinessException("No existe este Trabajo en la tabla de Jobs");
		}
	    
	   List<Employees> searchEmployee = serviceEmployee.findByJobsIdSQL(jobsExits.getId());
	   if (searchEmployee== null) {
		   throw new BusinessException("no se encontraron empleados relacionado con el id de job");
	}
	   List<EmployeeDescriptionModel> temp = new ArrayList<>();
	   for (Employees employees : searchEmployee) {
		  JobsModel tempJbos = modelMapper.map(employees.getJobs(), JobsModel.class);
		  GenderModel tempGender = modelMapper.map(employees.getGender(), GenderModel.class);
		  EmployeeModel tempEmployee = modelMapper.map(employees,EmployeeModel.class);
	
		  EmployeeDescriptionModel tempEmployee2 = modelMapper.map(tempEmployee,EmployeeDescriptionModel.class);
		   tempEmployee2.setSuccess(true);
		  
		   temp.add(tempEmployee2);
	}
		
		 
		  return temp;
		 
	    
	}
	
	
	

}
