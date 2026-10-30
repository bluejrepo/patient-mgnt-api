package au.gov.adha.api.config;
 
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import jakarta.servlet.ServletException;
 
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import java.util.ArrayList;
import java.util.List;
 
@Configuration
public class FhirConfig {
 
 
    @Bean
    public ServletRegistrationBean<RestfulServer> fhirServletRegistration(ApplicationContext context) {
        // Collect all Spring Beans implementing IResourceProvider
        List<IResourceProvider> providers = new ArrayList<>(context.getBeansOfType(IResourceProvider.class).values());
 
        RestfulServer servlet = new RestfulServer(FhirContext.forR4()) {
            @Override
            protected void initialize() throws ServletException {
                super.initialize();
                // Register the collected providers to the HAPI engine
                setResourceProviders(providers);
            }
        };
 
        // Map the FHIR engine to the "/fhir/*" URI base path
        return new ServletRegistrationBean<>(servlet, "/fhir/*");
    }
}