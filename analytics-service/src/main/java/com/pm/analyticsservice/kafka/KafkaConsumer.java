package com.pm.analyticsservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // TODO: ... perform any business logic related to this service

            LOGGER.info(
                "Received patient event: [PatientId={}, PatientName={}, PatientEmail={}]", 
                patientEvent.getPatientId(), 
                patientEvent.getName(),
                patientEvent.getEmail()
            );
        } catch (InvalidProtocolBufferException e) {
            LOGGER.error("Error deserializing event {}", e.getMessage());
        }
    }
}
