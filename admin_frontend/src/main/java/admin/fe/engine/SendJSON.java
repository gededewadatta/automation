/**
 * 
 */
package admin.fe.engine;

import admin.fe.model.Competency;
import admin.fe.model.Grade;
import admin.fe.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import admin.fe.model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author gederanadewadatta
 *
 */
public class SendJSON {

	ObjectMapper mapper = new ObjectMapper();

	Employee emp = new Employee();
	@Value("${led.Employee.insert}")
	protected String employeeInsert;

	public String insertEmployee(Employee emp) throws JsonProcessingException {
		String result = null;

		String body = mapper.writeValueAsString(emp);

		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/employee", HttpMethod.POST, entity,
				String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :"+responseEntity.getBody());
			System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

			result = String.valueOf(responseEntity.getStatusCode());

		}
		return result;
	}




	protected String nullString(Object obj) {

		return nullString(obj, 1000);
	}

	protected String nullString(Object obj, int maxLength) {

		String result = "";

		if (obj != null)
			result = obj.toString();

		if (result.length() > maxLength)
			result = result.substring(0, maxLength);

		return result;
	}

	public String insertGrade(Grade grd) throws JsonProcessingException {
		String result = null;

		String body = mapper.writeValueAsString(grd);

		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/grade", HttpMethod.POST, entity,
				String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :"+responseEntity.getBody());
			System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

			result = String.valueOf(responseEntity.getStatusCode());

		}
		return result;
	}

	public String insertQuestion(Question quest) throws JsonProcessingException {
		String result = null;

		String body = mapper.writeValueAsString(quest);

		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/question", HttpMethod.POST, entity,
				String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :"+responseEntity.getBody());
			System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

			result = String.valueOf(responseEntity.getStatusCode());

		}
		return result;
	}

	public  Employee emp(Employee employee){

		return this.emp = employee;

	}


	@RequestMapping(value = "/led/api/automation/search/question", consumes = "application/json", method = RequestMethod.GET)
	public Employee getEmp(@RequestBody String body, HttpMethod method, HttpServletRequest request,
								   HttpServletResponse response) throws URISyntaxException {

		ObjectMapper obj = new ObjectMapper();

		try {
			emp =	obj.readValue(body,Employee.class);
			emp(emp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public String insertCompetency(Competency cmp) throws JsonProcessingException {
		String result = null;

		String body = mapper.writeValueAsString(cmp);

		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/competency", HttpMethod.POST, entity,
				String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :"+responseEntity.getBody());
			System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

			result = String.valueOf(responseEntity.getStatusCode());

		}
		return result;
	}
}
