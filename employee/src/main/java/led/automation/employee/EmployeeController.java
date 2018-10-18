package led.automation.employee;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
 
import led.automation.employee.model.PendingQuestion;
import led.automation.employee.service.EmployeeService;

/**
 *
 * @author gede rana dewadatta
 */
@RestController
@RequestMapping("/led/api/automation")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/search/pendingquestion", produces = "application/json",method= RequestMethod.POST)
	@ResponseBody
	public List<PendingQuestion> searchQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<PendingQuestion> departement = employeeService.searchQuestion(body);
		return departement;
	}
	@RequestMapping(value = "/insert/historyanswer", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> submitHistory(@RequestBody String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
	    System.out.println(body);
		return ResponseEntity.ok(employeeService.submitHistory(body));
	}
	@RequestMapping(value = "/insert/submitquestion", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> submitQuestion(@RequestBody String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
	    System.out.println(body);
		return ResponseEntity.ok(employeeService.submitQuestion(body));
	}
	@RequestMapping(value = "/search/competency", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> departement = employeeService.searchCompetency();
		return departement;
	}
	@RequestMapping(value = "/generate/pendingquestion", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<PendingQuestion> generateQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<PendingQuestion> departement = employeeService.generateQuestion();
		return departement;
	}
	
}
