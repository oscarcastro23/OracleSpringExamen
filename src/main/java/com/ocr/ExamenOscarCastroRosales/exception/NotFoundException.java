package com.ocr.ExamenOscarCastroRosales.exception;

import org.springframework.http.HttpStatus;

import com.ocr.ExamenOscarCastroRosales.utils.LexUtils;

import lombok.Generated;



@Generated
public class NotFoundException extends GenericException {
	
	private static final long serialVersionUID = 3476869070109357604L;
	
	public NotFoundException(HttpStatus status, String mensaje, String codigo,String nivel,String descripcion,String detalle) {
		super(status,mensaje,codigo,nivel,descripcion,detalle);
	}
	
	public NotFoundException() {
		super(HttpStatus.NOT_FOUND,"no se encontro lo que buscas","TEST","WARNING","No se encontraron resultados","verifica el objeto");
	} 
	
	public NotFoundException(Object id) {
		super(HttpStatus.NOT_FOUND,LexUtils.getInstance().getMessage("No se pudo encontrar lo que buscaba", id),"404","WARNING","No se encontraron resultados","verifica el objeto");
	}
	
	public NotFoundException(String id) {
		super(HttpStatus.NOT_FOUND,LexUtils.getInstance().getMessage("No se pudo encontrar lo que buscaba", id),"404","WARNING","No se encontraron resultados","verifica el objeto");
	}

}
