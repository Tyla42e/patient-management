package com.pm.patient_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;

@Repository
public interface PatientRespository extends JpaRepository<Patient,UUID>{
    //public PatientResponseDTO findByID(UUID id) ;
    
}
