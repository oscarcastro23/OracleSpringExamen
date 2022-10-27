package com.ocr.ExamenOscarCastroRosales.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;
import com.ocr.ExamenOscarCastroRosales.entity.EmployeesWorkedHours;
import com.ocr.ExamenOscarCastroRosales.exception.BusinessException;
import com.ocr.ExamenOscarCastroRosales.exception.NotFoundException;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasRequestModel;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasResponse;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioRequest;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioResponse;
import com.ocr.ExamenOscarCastroRosales.model.WorkedEmployeesModel;
import com.ocr.ExamenOscarCastroRosales.repository.EmployeesRepository;
import com.ocr.ExamenOscarCastroRosales.repository.EmployeesWorkedHoursRepository;
import com.ocr.ExamenOscarCastroRosales.repository.JobsRepository;

@Service
public class WorkedEmployeeImpl implements IWorkedEmployee {

	@Autowired
	private EmployeesWorkedHoursRepository serviceEmployeeWorkedHours;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmployeesRepository serviceEmployee;
	
	@Autowired
	private JobsRepository jobsRepository;

	@Override
	public Page<WorkedEmployeesModel> getWorkedEmployeeAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return serviceEmployeeWorkedHours.findAll(pageable).map(pE -> modelMapper.map(pE, WorkedEmployeesModel.class));
	}

	@Override
	public EmployeesWorkedHours saveWorkedHours(WorkedEmployeesModel workedEmployee) {
		// TODO Auto-generated method stub
		Employees employees = serviceEmployee.findById(workedEmployee.getEmployees()).orElse(null);

		if (employees == null) {
			throw new NotFoundException("el id de trabajador no existe");
		}

		if (workedEmployee.getWorkedHours() > 20) {
			throw new BusinessException("no se pueden registrar mas de 20 horas x dia");
		}

		EmployeesWorkedHours workEmployee = modelMapper.map(workedEmployee, EmployeesWorkedHours.class);
		workEmployee.setEmployees(employees);

		if (workEmployee.getWorkedDate().before(new Date()) || workEmployee.getWorkedDate().equals(new Date())) {
			// entra antes de la fecha actual o igual al dia de hoy
			EmployeesWorkedHours tempWorkedDate = serviceEmployeeWorkedHours
					.findByWorkedDateAndEmployeesSQL(workEmployee.getWorkedDate(), workEmployee.getEmployees().getId())
					.orElse(null);

			if (tempWorkedDate == null) {
				return serviceEmployeeWorkedHours.save(workEmployee);
			}

		}
		throw new BusinessException("no se pueden registrar fechas que no sean menores o iguales a la actual");

	}

	@Override
	public TotalHorasResponse totalHours(TotalHorasRequestModel horasRequestModel) throws ParseException {
		// TODO Auto-generated method stub
		Employees tempEmployees = serviceEmployee.findById(horasRequestModel.getEmployee_id()).orElse(null);

		if (tempEmployees == null) {
			throw new BusinessException("el id de empleado no existe");
		}

		Date startDate = stringToDate(horasRequestModel.getStart_date());
		Date endDate = stringToDate(horasRequestModel.getEnd_date());
		Long sumaHoras = 0L;

		List<EmployeesWorkedHours> tempWorked = serviceEmployeeWorkedHours
				.findByEmployeesSQL(horasRequestModel.getEmployee_id());

		if (tempWorked == null) {
			throw new BusinessException("no se encontraron registros de horas del trabajador");
		}
		if (startDate.after(endDate)) {
			throw new BusinessException("la fecha de inicio es mayor que la fecha de fin");
		}

		for (EmployeesWorkedHours employeesWorkedHours : tempWorked) {
			if (employeesWorkedHours.getWorkedDate().after(endDate)
					|| employeesWorkedHours.getWorkedDate().equals(startDate)
					|| employeesWorkedHours.getWorkedDate().before(startDate)
					|| employeesWorkedHours.getWorkedDate().equals(endDate)) {
			} else {
				sumaHoras += employeesWorkedHours.getWorkedHours();
			}
		}
		TotalHorasResponse sumaHorasTotal = new TotalHorasResponse();
		sumaHorasTotal.setTotal_worked_hours(sumaHoras);
		sumaHorasTotal.setSuccess(true);
		return sumaHorasTotal;
	}

	@Override
	public TotalSalarioResponse totalPayments(TotalSalarioRequest totalSalarioRequest) throws ParseException {
		// TODO Auto-generated method stub
		Employees tempEmployees = serviceEmployee.findById(totalSalarioRequest.getEmployee_id()).orElse(null);
		
		if (tempEmployees == null) {
			throw new BusinessException("el id de empleado no existe");
		}

		Date startDate = stringToDate(totalSalarioRequest.getStart_date());
		Date endDate = stringToDate(totalSalarioRequest.getEnd_date());
		BigDecimal sumaSueldo = new BigDecimal("0.0").setScale(0);

		List<EmployeesWorkedHours> tempWorked = serviceEmployeeWorkedHours
				.findByEmployeesSQL(totalSalarioRequest.getEmployee_id());

		if (tempWorked == null) {
			throw new BusinessException("no se encontraron registros de horas del trabajador");
		}
		if (startDate.after(endDate)) {
			throw new BusinessException("la fecha de inicio es mayor que la fecha de fin");
		}
		System.out.println(tempEmployees.getJobs().getSalary());
		for (EmployeesWorkedHours employeesWorkedHours : tempWorked) {
			if (employeesWorkedHours.getWorkedDate().after(endDate)
					|| employeesWorkedHours.getWorkedDate().equals(startDate)
					|| employeesWorkedHours.getWorkedDate().before(startDate)
					|| employeesWorkedHours.getWorkedDate().equals(endDate)) {
			} else {
				sumaSueldo = sumaSueldo.add(tempEmployees.getJobs().getSalary());
			}
		}
		TotalSalarioResponse sumaHorasTotal = new TotalSalarioResponse();
		sumaHorasTotal.setPayment(sumaSueldo);
		sumaHorasTotal.setSuccess(true);
		return sumaHorasTotal;
		
	}

	public Date stringToDate(String fecha) throws ParseException {
		SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dateParse = isoFormat.parse(fecha);
		return dateParse;
	}

}
