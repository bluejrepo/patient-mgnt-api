package au.gov.adha.api.service;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;


@Service
public class PatientFhirService {

    // Simulating a backend FHIR repository or data store
    private final Map<String, Patient> patientRepository = new ConcurrentHashMap<>();

    @Cacheable(value = "fhirPatients", key = "#id")
    public Patient getPatientById(String id) {
        System.out.println("Fetching patient from FHIR database for ID: " + id);
        Patient patient = patientRepository.get(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found: " + id);
        }
        return patient;
    }

    @CachePut(value = "fhirPatients", key = "#patient.idElement.idPart")
    public Patient saveOrUpdatePatient(Patient patient) {
        String id = patient.getIdElement().getIdPart();
        System.out.println("Save patient to FHIR database for ID: " + id);
        patientRepository.put(id, patient);
        return patient;
    }

    //@CacheEvict(value = "fhirPatients", key = "#id")
    public void deletePatient(String id) {
    	System.out.println("Delete patient from FHIR database for ID: " + id);
    	patientRepository.remove(id);
    }
}