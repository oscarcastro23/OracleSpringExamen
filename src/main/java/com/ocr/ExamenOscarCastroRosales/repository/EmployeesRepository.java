package com.ocr.ExamenOscarCastroRosales.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ocr.ExamenOscarCastroRosales.entity.Employees;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
	
	
	public Employees findByNameAndLastName(String name,String lastname);
	
	@Query("select p from Employees p where p.jobs.id=?1")
	public List<Employees> findByJobsIdSQL(Long jobId);
	
	Page<Employees> findAll(Pageable pageable);
	
	
}
