package com.ocr.ExamenOscarCastroRosales.model;

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
public class TotalHorasRequestModel {
	
     private Long employee_id; // Id del empleado
    private String start_date; // Fecha de inicio
    private String end_date; // Fecha de fin

}
