package com.ocr.ExamenOscarCastroRosales.exception;

import org.springframework.http.HttpStatus;

import lombok.Generated;

@Generated
public class BusinessException extends GenericException {

	private static final long serialVersionUID = 3476869070109357604L;

	public BusinessException(HttpStatus status, String mensaje, String codigo, String nivel, String descripcion,
			String detalle) {
		super(status, mensaje, codigo, nivel, descripcion, detalle);
	}

	public BusinessException() {
		super(HttpStatus.BAD_REQUEST, "message", "Test", "ERROR",
				"No se encontraron resultados", "verifica el objeto");
	}
	
	
	public BusinessException(String mensaje) {
		super(HttpStatus.BAD_REQUEST,mensaje, "Test", "ERROR",
				mensaje, "verifica el objeto");
	}
}
