package com.ocr.ExamenOscarCastroRosales.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import com.ocr.ExamenOscarCastroRosales.model.CamposError;

import lombok.Generated;



public class LfieldErrorMapper {
	
	@Generated
	private LfieldErrorMapper() {
	    throw new IllegalStateException("Utility class");
	  }
	

	
	/**
	 * Converts a ConstraintViolation
	 * to a FieldError
	 */
	@Generated
	private static CamposError of(FieldError fielderror) {
		
		// Get the field name by removing the first part of the propertyPath.
		// (The first part would be the service method name)
		
		return new CamposError(fielderror.getField(),
				fielderror.getDefaultMessage());		
	}
	
	/**
	 * Converts a List of FieldError
	 * to a list of FieldErrors
	 * 
	 * @param constraintViolations
	 */
	@Generated
	public static List<CamposError> getErrors(List<FieldError> fieldsErrors) {
		
		return fieldsErrors.stream()
				.map(LfieldErrorMapper::of).collect(Collectors.toList());	
	}
	
	
	

	

}
