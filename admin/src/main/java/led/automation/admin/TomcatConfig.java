package led.automation.admin;

import java.io.IOException;

import led.automation.admin.model.*;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import led.automation.admin.dao.AdminDAO;
import led.automation.admin.dao.impl.AdminDAOImpl;
import led.automation.admin.service.AdminService;
import led.automation.admin.service.impl.AdminServiceImpl;

/**
 *
 * @author gede rana dewadatta
 */
@Configuration
public class TomcatConfig {

    @Value("${server.http.port}")
    private String httpPort;

    @Bean
    public ServletWebServerFactory servletContainer() {
            
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
        return tomcat;
    }
    
    @Bean
    public AdminService adminService() {
    	return new AdminServiceImpl();
    }
    
    @Bean
    public AdminDAO adminDao() {
    	return new AdminDAOImpl();
    }
    
    @Bean
    public Employee employee() {
    	return new Employee();
    }
    
    @Bean
    public Departement department() {
    	return new Departement();
    }
    
    @Bean
    public Division division() {
    	return new Division();
    }
    
    @Bean
    public PendingQuestion pendingQuestion() {
    	return new PendingQuestion();
    }
    
    @Bean
    public Question question() {
    	return new Question();
    }
    
    @Bean
    public SubGrade subGrade() {
    	return new SubGrade();
    }

    @Bean
    public Competency competency() {
        return new Competency();
    }

    @Bean
    public Grade grade() {
        return new Grade();
    }

    @Bean
    public GradeJson gradeJson() {
        return new GradeJson();
    }

    @Bean
    public SubmitQuestion submitQuestion() {
        return new SubmitQuestion();
    }

    private Connector createHTTPConnector() {
        
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            connector.setScheme("https");
            connector.setPort(httpPort == null ? 7104 : Integer.parseInt(httpPort));
            return connector;
        }
        catch (Exception e) {

            System.out.println("Error create connector :");
            e.printStackTrace(System.out);
            return null;
        }
    }
}
