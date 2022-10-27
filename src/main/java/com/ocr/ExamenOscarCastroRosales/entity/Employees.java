package com.ocr.ExamenOscarCastroRosales.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="employees")
public class Employees implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -6522510390200627397L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id",referencedColumnName = "id")
	private Genders gender;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobs_id",referencedColumnName = "id")
	private Jobs jobs;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birthdate")
	private Date birthdate;
	
	@OneToMany(mappedBy = "employees" , fetch = FetchType.LAZY)
	private List<EmployeesWorkedHours> employeesWorkedHours = new ArrayList<>();
}
