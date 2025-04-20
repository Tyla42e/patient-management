package com.pm.patient_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/patients/")
@Data
public class PatientController {

    private final PatientService _PatientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getMethodName() {
        List<PatientResponseDTO> patients = _PatientService.getPatients();

        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable UUID id) {
        // Check to see if record exists

        PatientResponseDTO response = _PatientService.find

    }
}
