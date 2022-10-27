package com.ocr.ExamenOscarCastroRosales.exception.handlers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.ocr.ExamenOscarCastroRosales.exception.BusinessException;
import com.ocr.ExamenOscarCastroRosales.exception.GenericException;
import com.ocr.ExamenOscarCastroRosales.exception.NotFoundException;
import com.ocr.ExamenOscarCastroRosales.model.CamposError;
import com.ocr.ExamenOscarCastroRosales.model.ErrorDescripcion;
import com.ocr.ExamenOscarCastroRosales.model.ErrorDetalle;
import com.ocr.ExamenOscarCastroRosales.utils.LexUtils;
import com.ocr.ExamenOscarCastroRosales.utils.LfieldErrorMapper;


import lombok.Generated;




@RestControllerAdvice
@Generated
public class ApiExceptionHandler {

	
	static final String ERROR_MENSAJE="No se pudo completar la operacion";

	@ExceptionHandler({GenericException.class})
	public  ResponseEntity<ErrorDetalle> genericException(GenericException ex) {
		ErrorDetalle errorResponse =crearErrorDetalle(ex.getMensaje(),ex.getCodigo(),ex.getNivel(),ex.getDescripcion(),ex.getDetalle(),null);
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus().value()));
	}
	
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorDetalle> methodArgument(MethodArgumentNotValidException ex) {
		
		ErrorDetalle errorResponse =crearErrorDetalle(LexUtils.getInstance().getMessage(ERROR_MENSAJE),
				"TEST","WARNING","Hubo un error al validar el request","Hubo un error al validar el request",LfieldErrorMapper.getErrors(ex.getBindingResult().getFieldErrors()));
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({BusinessException.class})
	public  ResponseEntity<ErrorDetalle> businessException(BusinessException ex) {
		ErrorDetalle errorResponse =crearErrorDetalle(ex.getMensaje(),ex.getCodigo(),ex.getNivel(),ex.getDescripcion(),ex.getDetalle(),null);
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus().value()));
	}
	
	@ExceptionHandler({NotFoundException.class})
	public  ResponseEntity<ErrorDetalle> notFoundException(NotFoundException ex) {
		ErrorDetalle errorResponse =crearErrorDetalle(ex.getMensaje(),ex.getCodigo(),ex.getNivel(),ex.getDescripcion(),ex.getDetalle(),null);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
		
	/**
	* @return ErrorDetalle
	*/
	private ErrorDetalle crearErrorDetalle(String mensaje, String codigo,String nivel,String descripcion,String detalle,List<CamposError> errores) {
	ErrorDetalle errorDetails = new ErrorDetalle();
	ErrorDescripcion detail = new ErrorDescripcion();
	detail.setCodigo(codigo);
	detail.setDescripcion(descripcion);
	detail.setDetalle(detalle);
	detail.setNivel(nivel);
	detail.setMensaje(mensaje);
	if(errores!=null && !errores.isEmpty()) {
	detail.setAdditionalProperty("errores",errores);
	}
	List<ErrorDescripcion> errors = new ArrayList<>();
	errors.add(detail);
	errorDetails.setErrors(errors);
	return errorDetails;
	}
	
	

}
