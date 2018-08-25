package led.automation.admin;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import led.automation.admin.model.Employee;
import led.automation.admin.model.Grade;
import led.automation.admin.model.Question;
import led.automation.admin.model.SubGrade;
import led.automation.admin.model.Competency;
import led.automation.admin.model.Departement;
import led.automation.admin.model.Division;
import led.automation.admin.service.AdminService; 

/**
 *
 * @author gede rana dewadatta
 */
@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public TestDTO test() {

		TestDTO result = new TestDTO();

		result.setDataInt(123);
		result.setDataBoolean(true);
		result.setDataString("abcYZ");

		return result;
	}

	// insert data : start
	@RequestMapping(value = "/led/api/automation/insert/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> insertEmployee(@RequestBody String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertEmployee(body));
	}

	@RequestMapping(value = "/led/api/automation/insert/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertGrade(body));
	}

	@RequestMapping(value = "/led/api/automation/insert/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertSubGrade(body));
	}

	@RequestMapping(value = "/led/api/automation/insert/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertQuestion(body));
	}

	// insert data : stop
	// search data : start
	@RequestMapping(value = "/led/api/automation/search/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Employee searchEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Employee employee = adminService.searchEmployee(body);
		return employee;
	}

	@RequestMapping(value = "/led/api/automation/search/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Grade searchGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Grade grade = adminService.searchGrade(body);
		return grade;
	}

	@RequestMapping(value = "/led/api/automation/search/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public SubGrade searchSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		SubGrade subGrade = adminService.searchSubGrade(body);
		return subGrade;
	}

	@RequestMapping(value = "/led/api/automation/search/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Question searchQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Question question = adminService.searchQuestion(body);
		return question;
	}
	
	@RequestMapping(value = "/led/api/automation/search/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Departement searchDepartement(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Departement departement = adminService.searchDepartement(body);
		return departement;
	}
	@RequestMapping(value = "/led/api/automation/search/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Division searchDivision(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Division division = adminService.searchDivision(body);
		return division;
	}
	@RequestMapping(value = "/led/api/automation/search/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Competency searchCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		Competency competency = adminService.searchCompetency(body);
		return competency;
	}
	// search data : stop 
	 
	// upload data : start
	public int uploadQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) {
		int result = adminService.uploadQuestion(body);
		return result;
	}
	public int uploadEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) {
		int result = adminService.uploadEmployee(body);
		return result;
	}
	// upload data : stop
	
	//generate data: start
	@RequestMapping(value = "/led/api/automation/generate/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> employee = adminService.generateEmployee(body);
		return employee;
	}

	@RequestMapping(value = "/led/api/automation/generate/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> grade = adminService.generateGrade(body);
		return grade;
	}

	@RequestMapping(value = "/led/api/automation/generate/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> subGrade = adminService.generateSubGrade(body);
		return subGrade;
	}

	@RequestMapping(value = "/led/api/automation/generate/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> question = adminService.generateQuestion(body);
		return question;
	}
	
	@RequestMapping(value = "/led/api/automation/generate/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateDepartement(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> departement = adminService.generateDepartement(body);
		return departement;
	}
	@RequestMapping(value = "/led/api/automation/generate/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateDivision(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> division = adminService.generateDivision(body);
		return division;
	}
	@RequestMapping(value = "/led/api/automation/generate/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> generateCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> competency = adminService.generateCompetency(body);
		return competency;
	}
	//generate data: stop	
}
