package com.ocr.ExamenOscarCastroRosales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocr.ExamenOscarCastroRosales.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

}
