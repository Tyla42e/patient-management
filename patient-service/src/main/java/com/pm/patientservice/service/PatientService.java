package com.pm.patientservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRespository _patientRespository;

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = _patientRespository.findAll();

        List<PatientResponseDTO> patientsDTO = patients.stream().map(PatientMapper::toDTO).toList();
        return patientsDTO;
    }

    public PatientResponseDTO getPatientById(UUID id) throws BadRequestException
    {
        Optional<Patient> patient = _patientRespository.findById(id);
        if (!patient.isPresent()) {
            throw new BadRequestException("Patient Not Found");
        }

        return PatientMapper.toDTO(patient.get());

    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        return null;
    }

    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO) {
        return null;
    }

    public PatientResponseDTO deletePatient(UUID id) {
        return null;
    }

    

}
