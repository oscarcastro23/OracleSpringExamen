package com.ocr.ExamenOscarCastroRosales.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@EqualsAndHashCode
@Table(name="employees_worked_hours")
public class EmployeesWorkedHours implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4095823180873913269L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employees employees;
	
	@Column(name = "worked_hours")
	private Long workedHours;
	
	@Column(name = "worked_date")
	private Date workedDate;
}
