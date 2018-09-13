package led.automation.wsproxy;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author gede rana dewadatta
 */
@RestController
public class ProxyController {
	// insert : start
	@Value("${led.automation.admin.insert.employee}")
	protected String employeeUrlInsert;
	@Value("${led.automation.admin.insert.grade}")
	protected String gradeUrlInsert;
//	@Value("${led.automation.admin.get.employeeTest}")
//	protected String employeeDummyInsert;
	@Value("${led.automation.admin.insert.subgrade}")
	protected String subgradeUrlInsert;
	@Value("${led.automation.admin.insert.question}")
	protected String questionUrlInsert;
	@Value("${led.automation.admin.insert.departement}")
	protected String departementUrlInsert;
	@Value("${led.automation.admin.insert.division}")
	protected String divisionUrlInsert;
	@Value("${led.automation.admin.insert.competency}")
	protected String competencyUrlInsert;
	// insert : stop##
	// search : start##
	@Value("${led.automation.admin.search.dashboard}")
	protected String dashboardUrlSearch;
	@Value("${led.automation.admin.search.employee}")
	protected String employeeUrlSearch;
	@Value("${led.automation.admin.search.employeeCompetency)")
	protected String employeeCompetencyUrlSearch;
	@Value("${led.automation.admin.search.grade}")
	protected String gradeUrlSearch;
	@Value("${led.automation.admin.search.subgrade}")
	protected String subgradeUrlSearch;
	@Value("${led.automation.admin.search.question}")
	protected String questionUrlSearch;
	@Value("${led.automation.admin.search.departement}")
	protected String departementUrlSearch;
	@Value("${led.automation.admin.search.division}")
	protected String divisionUrlSearch;
	@Value("${led.automation.admin.search.competency}")
	protected String competencyUrlSearch;
	@Value("${led.automation.admin.search.gradeJson}")
	protected String gradeJsonUrlSearch;
	// search : stop##
	// update : start##
	@Value("${led.automation.admin.update.employee}")
	protected String employeeUrlUpdate;
	@Value("${led.automation.admin.update.grade}")
	protected String gradeUrlUpdate;
	@Value("${led.automation.admin.update.subgrade}")
	protected String subgradeUrlUpdate;
	@Value("${led.automation.admin.update.question}")
	protected String questionUrlUpdate;
	@Value("${led.automation.admin.update.departement}")
	protected String departementUrlUpdate;
	@Value("${led.automation.admin.update.division}")
	protected String divisionUrlUpdate;
	@Value("${led.automation.admin.update.competency}")
	protected String competencyUrlUpdate;
	// update : stop##
	// upload : start##
	@Value("${led.automation.admin.upload.employee}")
	protected String employeeUrlUpload;
	@Value("${led.automation.admin.upload.question}")
	protected String questionUrlUpload;
	// upload : stop##
	// generate : start##
	@Value("${led.automation.admin.generate.employee}")
	protected String employeeUrlGenerate;
	@Value("${led.automation.admin.generate.grade}")
	protected String gradeUrlGenerate;
	@Value("${led.automation.admin.generate.subgrade}")
	protected String subgradeUrlGenerate;
	@Value("${led.automation.admin.generate.question}")
	protected String questionUrlGenerate;
	@Value("${led.automation.admin.generate.departement}")
	protected String departementUrlGenerate;
	@Value("${led.automation.admin.generate.division}")
	protected String divisionUrlGenerate;
	@Value("${led.automation.admin.generate.competency}")
	protected String competencyUrlGenerate;

	// generate : stop##

	// employee : start
	@Value("${led.automation.employee.search.question}")
	protected String employeeSearchQuestion;
	@Value("${led.automation.employee.submit.history}")
	protected String employeeSubmitHistory;
	@Value("${led.automation.employee.submit.question}")
	protected String employeeSubmitQuestion;

	// employee : stop

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

//        	case  "employee" : result = employeeURL; break;  
		case "employeeUrlInsert":
			result = employeeUrlInsert;
			break;
		case "gradeUrlInsert":
			result = gradeUrlInsert;
			break;
		case "subgradeUrlInsert":
			result = subgradeUrlInsert;
			break;
		case "questionUrlInsert":
			result = questionUrlInsert;
			break;
		case "employeeUrlSearch":
			result = employeeUrlSearch;
			break;
		case "gradeUrlSearch":
			result = gradeUrlSearch;
			break;
		case "subgradeUrlSearch":
			result = subgradeUrlSearch;
			break;
		case "questionUrlSearch":
			result = questionUrlSearch;
			break;
		case "departementUrlSearch":
			result = departementUrlSearch;
			break;
		case "divisionUrlSearch":
			result = divisionUrlSearch;
			break;
		case "competencyUrlSearch":
			result = competencyUrlSearch;
			break;
		case "employeeUrlUpdate":
			result = employeeUrlUpdate;
			break;
		case "gradeUrlUpdate":
			result = gradeUrlUpdate;
			break;
		case "subgradeUrlUpdate":
			result = subgradeUrlUpdate;
			break;
		case "questionUrlUpdate":
			result = questionUrlUpdate;
			break;
		case "departementUrlUpdate":
			result = departementUrlUpdate;
			break;
		case "divisionUrlUpdate":
			result = divisionUrlUpdate;
			break;
		case "competencyUrlUpdate":
			result = competencyUrlUpdate;
			break;
		case "employeeUrlUpload":
			result = employeeUrlUpload;
			break;
		case "questionUrlUpload":
			result = questionUrlUpload;
			break;
		case "employeeUrlGenerate":
			result = employeeUrlGenerate;
			break;
		case "gradeUrlGenerate":
			result = gradeUrlGenerate;
			break;
		case "subgradeUrlGenerate":
			result = subgradeUrlGenerate;
			break;
		case "questionUrlGenerate":
			result = questionUrlGenerate;
			break;
		case "departementUrlGenerate":
			result = departementUrlGenerate;
			break;
		case "divisionUrlGenerate":
			result = divisionUrlGenerate;
			break;
		case "competencyUrlGenerate":
			result = competencyUrlGenerate;
			break;
		case "employeeSearchQuestion":
			result = employeeSearchQuestion;
			break;
		case "employeeSubmitHistory":
			result = employeeSubmitHistory;
			break;
		case "employeeSubmitQuestion":
			result = employeeSubmitQuestion;
			break;
		default:
			result = "not found";
			break;
		}

		return result;
	}
//    //insert answer from employee
//    @RequestMapping(value="/led/api/automation/employee", produces="application/json", method = RequestMethod.POST)
//    @ResponseBody
//    public String employee(@RequestBody String body, HttpMethod method, HttpServletRequest request,HttpServletResponse response) throws URISyntaxException
//    {        
//        return proxy(employeeURL,  body, method, request, response);
//    }

	public String proxy(String destPath, String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "";

		try {
			URL url = new URL(destPath);
			String path = url.getPath();

			URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), path, request.getQueryString(),
					null);

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
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

			System.out.println(
					"==> reply : " + responseEntity.getStatusCode().toString() + " - " + responseEntity.getBody());

			return responseEntity.getBody();
		} catch (Exception e) {

			System.out.println("Error proxy : " + destPath);
			e.printStackTrace(System.out);
		}

		return result;
	}

	// insert data : start
	@RequestMapping(value = "/led/api/automation/insert/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String employeeInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeUrlInsert, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/insert/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String gradeInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(gradeUrlInsert, body, method, request, response);

	}
//
//	@RequestMapping(value = "/led/api/automation/get/employee/{employeeCode}", produces = "application/json", method = RequestMethod.GET)
//	@ResponseBody
//	public String employeeTest(@RequestBody String body, HttpMethod method, HttpServletRequest request,
//							  HttpServletResponse response, @PathVariable("employeeCode") String employeeCode) throws URISyntaxException {
//		return proxy(employeeDummyInsert+"/"+employeeCode , body, method, request, response);
//
//	}

	@RequestMapping(value = "/led/api/automation/insert/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String subgradeInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(subgradeUrlInsert, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/insert/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String questionInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(questionUrlInsert, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/insert/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String departementInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(departementUrlInsert, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/insert/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String divisionInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(divisionUrlInsert, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/insert/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String competencyInsert(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(competencyUrlInsert, body, method, request, response);

	}

	// insert data : stop
	// update data : start
	@RequestMapping(value = "/led/api/automation/update/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String employeeUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String gradeUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(gradeUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String subgradeUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(subgradeUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String questionUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(questionUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String departementUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(departementUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String divisionUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(divisionUrlUpdate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/update/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String competencyUpdate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(competencyUrlUpdate, body, method, request, response);

	}

	// update data : stop
	// search data : start
	@RequestMapping(value = "/led/api/automation/search/dashboard", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String dashboardSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(dashboardUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String employeeSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
								 HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/automation/api/search/employeeCompetency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String searchEmployeeCompetency(@RequestBody String body, HttpMethod method, HttpServletRequest request,
								 HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeCompetencyUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String gradeSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(gradeUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/gradeJson", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String subgradeSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(gradeJsonUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String questionSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(questionUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String departementSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(departementUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String divisionSearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(divisionUrlSearch, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/search/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String competencySearch(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(competencyUrlSearch, body, method, request, response);

	}

	// search data : stop
	// generate data : start
	@RequestMapping(value = "/led/api/automation/generate/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String employeeGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/grade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String gradeGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(gradeUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/subgrade", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String subgradeGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(subgradeUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String questionGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(questionUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/departement", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String departementGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(departementUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/division", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String divisionGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(divisionUrlGenerate, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/generate/competency", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String competencyGenerate(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(competencyUrlGenerate, body, method, request, response);

	}

	// generate data : stop
	// upload data : start
	@RequestMapping(value = "/led/api/automation/upload/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String uploadQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(questionUrlUpload, body, method, request, response);

	}

	@RequestMapping(value = "/led/api/automation/upload/employee", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String uploadEmployee(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeUrlUpload, body, method, request, response);

	}

	// upload data : stop
	// employee part : start
	@RequestMapping(value = "/automation/api/search/pendingquestion/{userName}", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String searchQuestion(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeSearchQuestion, body, method, request, response);

	}

	@RequestMapping(value = "/automation/api/insert/history", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String submitHistory(@RequestBody String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeSubmitHistory, body, method, request, response);

	}
	@RequestMapping(value = "/automation/api/insert/question", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String submitQuestion(@RequestBody String body, HttpMethod method,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		return proxy(employeeSubmitQuestion, body, method, request, response);

	}
	// employee part : stop

}
