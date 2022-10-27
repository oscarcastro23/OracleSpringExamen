package com.ocr.ExamenOscarCastroRosales.config;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated
public class DateToStringConverter  implements Converter<Date,String> {
	
	
	@Override
	public String convert(MappingContext<Date,String> context) {
		
		Date configuracion=context.getSource();
		String fecha=null;
		
		if(configuracion ==null) {
			return null;
		}
					
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			fecha=dateFormat.format(configuracion); 
			log.info("la fecha de salida {}",fecha);			
		}catch(Exception e) {
			log.info("el error "+e.getMessage());
		}
		
		
		return fecha;
	}





	

	
	
	
}
