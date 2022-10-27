package com.ocr.ExamenOscarCastroRosales.config;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringToDateConverter  implements Converter<String,Date> {
	
	@Generated
	@Override
	public Date convert(MappingContext<String,Date> context) {
		
		String configuracion=context.getSource();
		Date fecha=null;
		
		if(configuracion ==null) {
			return null;
		}
			
		try {
			
			SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
			fecha  = isoFormat.parse(configuracion);
			log.info("la fecha al convertir {}",fecha);
			
		}catch(Exception e) {
			log.info("el error "+e.getMessage());
		}
		
		
		return fecha;
	}





	

	
	
	
}
