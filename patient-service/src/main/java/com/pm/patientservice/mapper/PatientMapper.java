package com.pm.patientservice.mapper;

import java.time.LocalDate;

import com.pm.patientservice.dto.PatientRequest;
import com.pm.patientservice.dto.PatientResponse;
import com.pm.patientservice.model.Patient;

public class PatientMapper {
    
    public static PatientResponse toDTO(Patient patient) {
        return PatientResponse.builder()
            .id(patient.getId().toString())
            .name(patient.getName())
            .email(patient.getEmail())
            .address(patient.getAddress())
            .dateOfBirth(patient.getDateOfBirth().toString())
            .build();
    }

    public static Patient toModel(PatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(request.getRegisteredDate()));

        return patient;
    }
}
