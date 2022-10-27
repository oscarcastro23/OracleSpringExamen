package com.ocr.ExamenOscarCastroRosales.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ocr.ExamenOscarCastroRosales.entity.EmployeesWorkedHours;

@Repository
public interface EmployeesWorkedHoursRepository extends JpaRepository<EmployeesWorkedHours,Long> {
	
	@Query("select p from EmployeesWorkedHours p where p.workedDate=?1 and p.employees.id=?2")
	Optional<EmployeesWorkedHours> findByWorkedDateAndEmployeesSQL(Date workedDate,Long idEmployee);
	
	@Query("select p from EmployeesWorkedHours p where p.employees.id=?1")
	List<EmployeesWorkedHours> findByEmployeesSQL(Long employees);
	
	
	
}
