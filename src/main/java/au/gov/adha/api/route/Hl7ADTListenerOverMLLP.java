package au.gov.adha.api.route;
 
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
@Component
public class Hl7ADTListenerOverMLLP extends RouteBuilder {
 
    @Value("${hl7.listener.host}")
    private String host;
 
    @Value("${hl7.listener.port}")
    private int port;
    
    @Override
    public void configure() throws Exception {
        // Define HL7 data format (HAPI parser wrapper)
        HL7DataFormat hl7DataFormat = new HL7DataFormat();
 
        // MLLP consumer endpoint URI
        String mllpEndpoint = String.format("mllp:%s:%d?autoAck=true", host, port);
 
        from(mllpEndpoint)
	        .routeId("mllp-hl7-receiver")
	        .log("Received raw MLLP payload")
	        
	        // Unmarshal the raw HL7 string into a HAPI Message Object
	        .unmarshal(hl7DataFormat)
        
            // 2. Route payload to our custom processor bean
            .to("bean:hl7ADTMessageService?method=process")
 
            // 3. Resulting ACK message back to raw bytes
            .log("Message processed successfully. Auto-ACK transmitted back.");
    }
}