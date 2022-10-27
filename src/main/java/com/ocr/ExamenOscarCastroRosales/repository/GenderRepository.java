package com.ocr.ExamenOscarCastroRosales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocr.ExamenOscarCastroRosales.entity.Genders;

@Repository
public interface GenderRepository extends JpaRepository<Genders, Long> {

}
