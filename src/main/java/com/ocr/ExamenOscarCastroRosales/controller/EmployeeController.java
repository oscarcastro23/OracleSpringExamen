package com.ocr.ExamenOscarCastroRosales.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;
import com.ocr.ExamenOscarCastroRosales.entity.Genders;
import com.ocr.ExamenOscarCastroRosales.exception.GenericException;
import com.ocr.ExamenOscarCastroRosales.exception.NotFoundException;
import com.ocr.ExamenOscarCastroRosales.model.BuscarIdJobsEmployee;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeDescriptionModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeeModel;
import com.ocr.ExamenOscarCastroRosales.model.EmployeesSaveModel;
import com.ocr.ExamenOscarCastroRosales.model.ResponseCreateModel;
import com.ocr.ExamenOscarCastroRosales.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.*;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeesImpl;

	@Operation(summary = "registra empleado")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleado creado con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error Internal Server") })
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/")
	public ResponseEntity<ResponseCreateModel> createEmployees(@RequestBody EmployeesSaveModel employeesModel)
			throws ParseException {

		ResponseCreateModel response = new ResponseCreateModel();
		Employees responseSave = employeesImpl.saveEmployee(employeesModel);
		if (responseSave != null) {

			response.setResponse(responseSave.getId());
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		}
		response.setResponse(null);
		response.setSuccess(false);

		return ResponseEntity.status(HttpStatus.IM_USED).body(response);
	}

	@Operation(summary = "busca empleado por id de trabajo")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleados encontrados con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no validos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GenericException.class)) }), })
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/idJobs")
	public ResponseEntity<List<EmployeeDescriptionModel>> buscarPorIdJob(@RequestBody BuscarIdJobsEmployee buscarId) {

		return ResponseEntity.status(HttpStatus.OK).body(employeesImpl.getEmployeeJobs(buscarId));
	}

	@Operation(summary = "muestra toda la informacion de workedemployee")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleados encontrados con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no validos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GenericException.class)) }), })
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<EmployeeModel>> findAll(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(employeesImpl.getAll(pageable));
	}
	
	
}
