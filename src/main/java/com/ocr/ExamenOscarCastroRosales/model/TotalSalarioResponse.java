package com.ocr.ExamenOscarCastroRosales.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TotalSalarioResponse {
	  private BigDecimal payment;
	    private Boolean success; 
}
