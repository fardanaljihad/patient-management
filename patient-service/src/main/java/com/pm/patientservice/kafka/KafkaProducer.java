package com.pm.patientservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pm.patientservice.model.Patient;

import lombok.RequiredArgsConstructor;
import patient.events.PatientEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
            .setPatientId(patient.getId().toString())
            .setName(patient.getName())
            .setEmail(patient.getEmail())
            .setEventType("PATIENT_CREATED")
            .build();

        try {
            kafkaTemplate.send("patient", event.toByteArray());
            LOGGER.info("Sending PATIENT_CREATED event: {}", event);
        } catch (Exception e) {
            LOGGER.error("Error sending PATIENT_CREATED event: {}", event);
        }
    }
}
