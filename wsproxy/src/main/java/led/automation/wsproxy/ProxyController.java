package led.automation.wsproxy;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author gede rana dewadatta
 */
@RestController
public class ProxyController {

    @Value("${led.automation.employee}")
    protected String employeeURL;
    
    @Value("${led.automation.admin.insert.master.employee}")
    protected String insertEmployeeURL;
    
    @Value("${led.automation.admin.insert.master.question}")
    protected String insertQuestionURL;
    
    @Value("${led.automation.admin.insert.master.grade}")
    protected String insertGradeURL;

    @Value("${led.automation.admin.insert.master.subgrade}")
    protected String insertSubGradeURL;
    
    @Value("${led.automation.admin.update.master.employee}")
    protected String updateEmployeeURL;
    
    @Value("${led.automation.admin.search.master.employee}")
    protected String searchEmployeeURL;

    @Value("${led.automation.admin.search.master.grade}")
    protected String searchGradeURL;

    @Value("${led.automation.admin.search.master.subgrade}")
    protected String searchSubGradeURL;

    @Value("${led.automation.admin.search.master.question}")
    protected String searchQuestionURL;
    
    @Value("${led.automation.admin.get.result.competency}")
    protected String getComptencyURL;
    
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public TestDTO test() {
        
        TestDTO result = new TestDTO();
        
        result.setDataInt(123);
        result.setDataBoolean(true);
        result.setDataString("abcYZ");
                
        return result;
    }
    
    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public String config(@RequestParam String name) {
        
        String result = "";
        
        switch (name) {

        	case  "employee" : result = employeeURL; break; 
            case  "insertEmployeeURL" : result = insertEmployeeURL; break; 
            case  "insertQuestionURL" : result = insertQuestionURL; break;
            case  "insertGradeURL" : result = insertGradeURL; break;  
            case  "insertSubGradeURL" : result = insertSubGradeURL; break;
            case  "updateEmployeeURL" : result = updateEmployeeURL; break;  
            case  "searchEmployeeURL" : result = searchEmployeeURL; break;  
            case  "searchGradeURL" : result = searchGradeURL; break;  
            case  "searchSubGradeURL" : result = searchSubGradeURL; break;
            case  "searchQuestionURL" : result = searchQuestionURL; break;
            case  "getComptencyURL" : result = getComptencyURL; break;  
            default: result = "not found"; break;
        }   
        
        return result;
    }
    //insert answer from employee
    @RequestMapping(value="/led/api/automation/employee", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String employee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(employeeURL,  body, method, request, response);
    }
    //insert master data : start
    @RequestMapping(value="/led/api/automation/insert/employee", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String insertEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(insertEmployeeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/insert/grade", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String insertGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(insertGradeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/insert/subgrade", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String insertSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(insertSubGradeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/insert/question", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String insertQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(insertQuestionURL,  body, method, request, response);
    }
    //insert master data : stop
    //search data : start
    @RequestMapping(value="/led/api/automation/search/employee", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String searchEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(searchEmployeeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/search/grade", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String searchGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(searchGradeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/search/subgrade", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String searchSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(searchSubGradeURL,  body, method, request, response);
    }
    @RequestMapping(value="/led/api/automation/search/question", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String searchQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(searchQuestionURL,  body, method, request, response);
    }
    //search data : stop 
    //update data employee : start
    @RequestMapping(value="/led/api/automation/update/employee", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String updateEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(updateEmployeeURL,  body, method, request, response);
    }
    //update data employee : stop

    //get data competency : start
    @RequestMapping(value="/led/api/automation/get/competency", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String getCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) throws URISyntaxException
    {        
        return proxy(getComptencyURL,  body, method, request, response);
    }
    //get data competency : stop
    public String proxy(String destPath, String body, HttpMethod method, HttpServletRequest request,
                                HttpServletResponse response) {
        String result = "";
        
        try {
            URL url = new URL(destPath);            
            String path = url.getPath();
                                 
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), path, request.getQueryString(), null);
            
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) { return true; }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
                
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            
            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            
            if (request.getHeader("AuthenticationToken") != null) {
                
                System.out.println("header -> AuthenticationToken : " + request.getHeader("AuthenticationToken"));
                headers.add("AuthenticationToken", request.getHeader("AuthenticationToken"));
            }                
            
            if (request.getHeader("WsSecurityUserName") != null) {
                
                System.out.println("header -> WsSecurityUserName : " + request.getHeader("WsSecurityUserName"));
                headers.add("WsSecurityUserName", request.getHeader("WsSecurityUserName"));
            }

            if (request.getHeader("WsSecurityPassword") != null) {
             
                System.out.println("header -> WsSecurityPassword : " + request.getHeader("WsSecurityPassword"));
                headers.add("WsSecurityPassword", request.getHeader("WsSecurityPassword"));
            }                
            
            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            
            System.out.println("==> proxy to " + uri.toString() + " : " + body);
            
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, entity, String.class);

            System.out.println("==> reply : " + responseEntity.getStatusCode().toString() + " - " + responseEntity.getBody());
            
            return responseEntity.getBody();
        }
        catch (Exception e) {
            
            System.out.println("Error proxy to avantrade : " + destPath);
            e.printStackTrace(System.out);
        }
        
        return result;
    }
    
    
}
