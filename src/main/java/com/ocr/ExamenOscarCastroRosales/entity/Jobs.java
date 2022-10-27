package com.ocr.ExamenOscarCastroRosales.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name="jobs")
public class Jobs implements Serializable  {/**
	 * 
	 */
	private static final long serialVersionUID = -3446473365848685814L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "salary",scale = 2,precision = 9)
	private BigDecimal salary;
	
	/*
	 * @OneToOne(mappedBy = "jobs") private Employees employees;
	 */
}
