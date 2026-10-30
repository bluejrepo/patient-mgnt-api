package au.gov.adha.api.service;
 
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ADT_A01; // Assuming v2.5 structure
import ca.uhn.hl7v2.model.v25.segment.PID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service("hl7ADTMessageService")
public class Hl7ADTMessageService {
 
	//@Autowired
	//private PatientRepository  patientRepository;
	
	public void process(Message hl7Message) throws Exception {
        // Inspect the type of structural message arriving (e.g., ADT^A01)
        if (hl7Message instanceof ADT_A01) {
            ADT_A01 adtMsg = (ADT_A01) hl7Message;
            
            // Safely fetch deep segments using HAPI API strongly typed objects
            PID pid = adtMsg.getPID();
            String patientId = pid.getPatientIdentifierList(0).getIDNumber().getValue();
            String lastName = pid.getPatientName(0).getFamilyName().getSurname().getValue();
            String firstName = pid.getPatientName(0).getGivenName().getValue();
            System.out.printf("Processing ADT_A01 event -> Patient ID: %s, Name: %s%n", patientId, firstName.concat(" ").concat(lastName));
            
            // Execute persistence or send notification to modern REST endpoints
        } else {
            System.out.println("Received message structure type: " + hl7Message.getName());
        }
    }
}