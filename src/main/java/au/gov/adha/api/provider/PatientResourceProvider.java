package au.gov.adha.api.provider;
 
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.gov.adha.api.service.PatientFhirService;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
 
@Component
public class PatientResourceProvider implements IResourceProvider {
 
	@Autowired
	PatientFhirService patientFhirService;
	
    @Override
    public Class<Patient> getResourceType() {
        return Patient.class; // Ties this provider to the /Patient endpoint
    }
 
    /**
     * READ Operation: GET /Patient/{id}
     */
    @Read()
    public Patient getResourceById(@IdParam IdType theId) {
    	return patientFhirService.getPatientById(theId.getIdPart());
    }
 
    /**
     * SEARCH Operation: GET /Patient?family=...
     */
    @Search()
    public List<Patient> searchPatients(@OptionalParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
        if (theFamilyName != null) {
            Patient patient = new Patient();
            patient.setId("1");
            patient.addName().setFamily(theFamilyName.getValue()).addGiven("Jane");
            return Collections.singletonList(patient);
        }
        return Collections.emptyList();
    }
 
    /**
     * CREATE Operation: POST /Patient
     */
    @Create()
    public MethodOutcome createPatient(@ResourceParam Patient patient) {
        // Handle persistence logic here (e.g., save to DB)
    	UUID uuid = UUID.randomUUID();
        // Convert it to its standard 36-character string representation
        String guidString = uuid.toString();
        patient.setId(guidString);
        patientFhirService.saveOrUpdatePatient(patient);
        MethodOutcome outcome = new MethodOutcome();
        outcome.setResource(patient);
        return outcome;
    }
    
    /**
     * DELETE Operation: DELETE /Patient/{id}
     */
    @Delete()
    public void deleteResourceById(@IdParam IdType theId) {
    	patientFhirService.deletePatient(theId.getIdPart());
    }
}


