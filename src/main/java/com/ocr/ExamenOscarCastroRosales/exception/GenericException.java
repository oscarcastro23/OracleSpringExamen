package com.ocr.ExamenOscarCastroRosales.exception;

import org.springframework.http.HttpStatus;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Generated
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 5613331526146494500L;
	
	private final HttpStatus status;
	private final String mensaje;
	private final String codigo;
	private final String nivel;
	private final String descripcion;
	private final String detalle;
	
	
	
	public GenericException(HttpStatus status, String mensaje, String codigo,String nivel,String descripcion,String detalle) {
		super(mensaje);
		this.status = status;
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.nivel=nivel;
		this.descripcion=descripcion;
		this.detalle=detalle;
	}
	
	
	

}
