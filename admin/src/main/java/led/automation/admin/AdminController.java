package led.automation.admin;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import led.automation.admin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import led.automation.admin.service.AdminService;

/**
 *
 * @author gede rana dewadatta
 */
@RestController
public class AdminController {
	@Autowired
	AdminService adminService;

	Employee employee;

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
	    System.out.println(body);
//	    Insert employee
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

	@RequestMapping(value = "/led/api/automation/insert/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
												  HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertCompetency(body));
	}

	@RequestMapping(value = "/led/api/automation/insert/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertDivision(@RequestBody String body, HttpMethod method, HttpServletRequest request,
											   HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertDivision(body));
	}

	@RequestMapping(value = "/led/api/automation/insert/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> insertDepartement(@RequestBody String body, HttpMethod method, HttpServletRequest request,
												  HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.insertDepartement(body));
	}

	// insert data : stop

	// update data : start

	@RequestMapping(value = "/led/api/automation/update/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String> updateDivision(@RequestBody String body, HttpMethod method, HttpServletRequest request,
												  HttpServletResponse response) throws URISyntaxException {
		return ResponseEntity.ok(adminService.updateDivision(body));
	}

	@RequestMapping(value = "/led/api/automation/update/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateEmployee(@RequestBody String body, HttpMethod method,
												 HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		System.out.println(body);
//	    update employee
		return ResponseEntity.ok(adminService.updateEmployee(body));
	}

	@RequestMapping(value = "/led/api/automation/update/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updateCompetency(@RequestBody String body, HttpMethod method,
												 HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		System.out.println(body);
//	    update competency
		return ResponseEntity.ok(adminService.updateCompetency(body));
	}

	// update data : stop

	// search data : start
	@RequestMapping(value = "/led/api/automation/search/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Employee> searchEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Employee> employee = adminService.searchEmployee(body);
		return employee;
	}

	@RequestMapping(value = "/led/api/automation/search/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Grade> searchGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Grade> grade = adminService.searchGrade(body);
		return grade;
	}

	@RequestMapping(value = "/led/api/automation/search/gradepopup", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Grade> searchGradePopup(@RequestBody String body, HttpMethod method, HttpServletRequest request,
								   HttpServletResponse response) throws URISyntaxException {
		List<Grade> grade = adminService.searchGradePopup(body);
		return grade;
	}

	@RequestMapping(value = "/led/api/automation/search/gradeJson", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<GradeJson> searchGradeJson(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<GradeJson> subGrade = adminService.searchGradeJson(body);
		return subGrade;
	}

	@RequestMapping(value = "/led/api/automation/search/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<SubGrade> searchSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
										   HttpServletResponse response) throws URISyntaxException {
		List<SubGrade> subGrade = adminService.searchSubGrade(body);
		return subGrade;
	}

	@RequestMapping(value = "/led/api/automation/search/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Question> searchQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Question> question = adminService.searchQuestion(body);
		return question;
	}

//    @RequestMapping(value = "/led/api/automation/get/employee/{employeeCode}", produces = "application/json", method = RequestMethod.GET)
//    @ResponseBody
//    public Employee searchEmployeeDummy(@RequestBody String body, HttpMethod method, HttpServletRequest request,
//                                   HttpServletResponse response, @PathVariable("employeeCode") String employeeCode) throws URISyntaxException {
//        employee = adminService.searchEmployee(employeeCode);
//        return employee;
//    }

	@RequestMapping(value = "/led/api/automation/search/dashboard", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Dashboard> searchDashboard(@RequestBody String body, HttpMethod method, HttpServletRequest request,
									   HttpServletResponse response) throws URISyntaxException {
		List<Dashboard> dashboards = adminService.findDashboard();
		return dashboards;
	}
	
	@RequestMapping(value = "/led/api/automation/search/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Departement> searchDepartement(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Departement> departement = adminService.searchDepartement(body);
		return departement;
	}
	@RequestMapping(value = "/led/api/automation/search/departementpopup", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Departement> searchDepartementPopup(@RequestBody String body, HttpMethod method, HttpServletRequest request,
											   HttpServletResponse response) throws URISyntaxException {
		List<Departement> departement = adminService.searchDepartementPopup(body);
		return departement;
	}
	@RequestMapping(value = "/led/api/automation/search/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Division> searchDivision(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Division> division = adminService.searchDivision(body);
		return division;
	}
	@RequestMapping(value = "/led/api/automation/search/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Competency> searchCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<Competency> competency = adminService.searchCompetency(body);
		return competency;
	}

	@RequestMapping(value = "/led/api/automation/search/reportEmployee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Report> searchReportEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
											 HttpServletResponse response) throws URISyntaxException {
		List<Report> reports = adminService.searchReportEmployee(body);
		return reports;
	}


	@RequestMapping(value = "/led/api/automation/search/competencyByGradeCode", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<Competency> searchCompetencyByGradeCode(@RequestBody String body, HttpMethod method, HttpServletRequest request,
											 HttpServletResponse response) throws URISyntaxException {
		List<Competency> competency = adminService.searchCompetencyByGradeCode(body);
		return competency;
	}
	// search data : stop
	 
	// upload data : start
	@RequestMapping(value = "/led/api/automation/upload/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public int uploadQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) {
		int result = adminService.uploadQuestion(body);
		return result;
	}
	@RequestMapping(value = "/led/api/automation/upload/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
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
	
	//find data parent : start
	@RequestMapping(value = "/led/api/automation/find/parent/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> findSubGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		List<String> subGrade = adminService.findSubGradeByGradeCode(body);
		return subGrade;
	}
	@RequestMapping(value = "/led/api/automation/find/parent/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> findGrade(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
//		List<String> grade = adminService.findGradeByDeptCode(body);
		return null;
	}
	@RequestMapping(value = "/led/api/automation/find/parent/department", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<String> findDepartement(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
//		List<String> grade = adminService.findDepartementByDivCode(body);
		return null;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(@RequestBody String body, HttpMethod method, HttpServletRequest request,
					   HttpServletResponse response)throws URISyntaxException{
		System.out.println("TEST 123");
		return "HELLOW";
	}
	//find data parent : stop
}
