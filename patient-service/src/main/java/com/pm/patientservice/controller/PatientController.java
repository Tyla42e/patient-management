package com.pm.patientservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/patient/")
@Data
public class PatientController {

    private final PatientService _PatientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = _PatientService.getAllPatients();

        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) throws BadRequestException {
        // Check to see if record exists

        PatientResponseDTO response = _PatientService.getPatientById(id);
        return ResponseEntity.ok().body(response );
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> AddPatient(@RequestBody PatientRequestDTO patient) {
        //TODO: process POST request


        
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @RequestBody PatientRequestDTO entity) {
        //TODO: process PUT request
        
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id) {
        return ResponseEntity.ok("Patient Delete");
    }
    
}
