package com.pm.patient_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRespository _patientRespository;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = _patientRespository.findAll();

        List<PatientResponseDTO> patientsDTO = patients.stream().map(PatientMapper::toDTO).toList();
        return patientsDTO;
    }

    public PatientResponseDTO getById(@PathVariable UUID id)
    {
        Optional<Patient> patient = _patientRespository.findById(id);
        

        if ( !!patient.isPresent() ) {
            ResponseEntity.notFound();
        }
        return PatientMapper.toDTO(patient.get());
    }

}
