package com.ocr.ExamenOscarCastroRosales.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Generated;

@Configuration
@Generated
public class BeansConfig implements WebMvcConfigurer {

	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addConverter(new DateToStringConverter());
		modelMapper.addConverter(new StringToDateConverter());

		return modelMapper;
	}

	
}
