package led.automation.wsproxy;

import java.io.IOException;
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
