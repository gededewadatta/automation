package admin.fe.config;

import java.util.HashMap;
import java.util.Map;

import admin.fe.engine.PageNavigation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

@Configuration
public class AppConfig {

	@Bean
	public ServletRegistrationBean dHtmlLayoutServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau");
		DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
		ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
		reg.setLoadOnStartup(1);
		reg.setInitParameters(params);
		return reg;
	}

//	@Bean
//	@ConditionalOnProperty(prefix = "zk", name = "springboot-packaging", havingValue = "war", matchIfMissing = false)
//	public ServletRegistrationBean dHtmlLayoutServlet() {
//		final String[] mappings = {"*.zul", "*.zhtml"};
//		ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlLayoutServlet(), mappings);
//		reg.setInitParameters(Collections.singletonMap("update-uri", zkProperties.getUpdateUri()));
//		reg.setLoadOnStartup(0);
////		logger.info("ZK-Springboot: ServletRegistrationBean for DHtmlLayoutServlet with url pattern " + Arrays.asList(mappings));
//		return reg;
//	}


	@Bean
	public ServletRegistrationBean dHtmlUpdateServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau/*");
		ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
		reg.setLoadOnStartup(2);
		reg.setInitParameters(params);
		return reg;
	}
	
	@Bean
	public HttpSessionListener httpSessionListener() {
		return new HttpSessionListener();
	}

	@Bean
	public PageNavigation pageNavigation() {

		return new PageNavigation();
	}

}
