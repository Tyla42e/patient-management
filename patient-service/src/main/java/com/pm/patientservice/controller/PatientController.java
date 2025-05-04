package com.pm.patientservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.net.URI;
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
@RequestMapping("/v1/patients")
@Data
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get All Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();

        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Patient by ID")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) throws BadRequestException {
        // Check to see if record exists

        PatientResponseDTO response = patientService.getPatientById(id);
        return ResponseEntity.ok().body(response );
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patient) {
        
        PatientResponseDTO response = patientService.createPatient(patient);

        URI location = URI.create("/api/v1/patients/" + response.getId());
        
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @RequestBody PatientRequestDTO entity) {
        PatientResponseDTO response = patientService.updatePatient(id,entity);
        
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
       
    }
    
}
