/**
 * 
 */
package admin.fe.engine;

import admin.fe.model.*;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.HTTP;
import org.json.JSONArray;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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

	public List<Dashboard> getDashBoard(){

		String result = null;

		String body = null;

		List<Dashboard> dashboards = new ArrayList<>();
		try {

			body = mapper.writeValueAsString(new Dashboard());

			System.out.println("===== INPUT ==== " + body);

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();

			headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());

			HttpEntity<String> entity = new HttpEntity<String>(body, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/dashboard", HttpMethod.POST, entity,
					String.class);

			System.out.println("Respon entity value is :"+ responseEntity);
			System.out.println("Respon entity body value is :"+ responseEntity.getBody());

			String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

			JSONObject jsonResponse = new JSONObject(respons);

			JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

			System.out.println("jsoon Array VAlue is : "+ jsonArray);

			for (int i = 0; i < jsonArray.length(); i++) {
				Dashboard dashboard = new Dashboard();
				JSONObject jsonObjVal = jsonArray.getJSONObject(i);
				System.out.println("Json Object Adalah :"+jsonObjVal);

				dashboard.setEmployeeId(jsonObjVal.getString("employeeId"));
				dashboard.setGrade(jsonObjVal.getString("grade"));
				dashboard.setResult(jsonObjVal.getString("result"));
				dashboards.add(dashboard);

			}

//			String responseValue = responseEntity.getBody().replace("[","");
//			String responsValue1 = responseValue.replace("]","");
//			System.out.println("responsValue1 :"+ responsValue1);

//			List<String> arrayJson = Arrays.asList(jsonArray);
//
//            for(String test : arrayJson){
//
//                System.out.println("Test Value Adalah :"+test);
//
//            }

//			List<String> responseValueList = Arrays.asList(responseValue1);



//			List<String> getValues = Collections.singletonList(responseEntity.getBody());

//			for(String getValue : responseValueList){
//				try{
//					Dashboard dashboard1 = mapper.readValue(getValue,Dashboard.class);
//					dashboards.add(dashboard1);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return dashboards;

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
