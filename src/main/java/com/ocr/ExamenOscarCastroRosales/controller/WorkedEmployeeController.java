package com.ocr.ExamenOscarCastroRosales.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.ExamenOscarCastroRosales.entity.EmployeesWorkedHours;
import com.ocr.ExamenOscarCastroRosales.model.ResponseCreateModel;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasRequestModel;
import com.ocr.ExamenOscarCastroRosales.model.TotalHorasResponse;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioRequest;
import com.ocr.ExamenOscarCastroRosales.model.TotalSalarioResponse;
import com.ocr.ExamenOscarCastroRosales.model.WorkedEmployeesModel;
import com.ocr.ExamenOscarCastroRosales.service.IWorkedEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/api/workedhours")
public class WorkedEmployeeController {
	
	@Autowired
	private IWorkedEmployee workedHours;
	
	@Operation(summary = "registra empleado")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleado creado con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error Internal Server") })
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/")
	public ResponseEntity<ResponseCreateModel> createEmployees(@RequestBody WorkedEmployeesModel workedEmployeesModel)
			throws ParseException {

		ResponseCreateModel response = new ResponseCreateModel();
		EmployeesWorkedHours responseSave = workedHours.saveWorkedHours(workedEmployeesModel);
		if (responseSave != null) {

			response.setResponse(responseSave.getId());
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		}
		response.setResponse(null);
		response.setSuccess(false);

		return ResponseEntity.status(HttpStatus.IM_USED).body(response);
	}
	
	
	@Operation(summary = "calcular pago empleado")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleado creado con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error Internal Server") })
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/obtenerpago")
	public ResponseEntity<TotalSalarioResponse> calcularSalario(@RequestBody TotalSalarioRequest totalSalarioRequest)
			throws ParseException {

		return ResponseEntity.status(HttpStatus.IM_USED).body(workedHours.totalPayments(totalSalarioRequest));
	}
	
	@Operation(summary = "calcular horas trabajo empleado")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empleado creado con éxito."),
			@ApiResponse(responseCode = "404", description = "Atributos no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error Internal Server") })
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/obtenerhoras")
	public ResponseEntity<TotalHorasResponse> buscarHorasTrabajo(@RequestBody TotalHorasRequestModel totalModel)
			throws ParseException {

		

		return ResponseEntity.status(HttpStatus.OK).body(workedHours.totalHours(totalModel));
	}
}
