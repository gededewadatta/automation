/**
 * 
 */
package admin.fe.engine;

import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String insertSubGrade(SubGrade subGrd) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(subGrd);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/subgrade", HttpMethod.POST, entity,
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


    //Search//

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

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return dashboards;

    }

    public List<Grade> getGrade(Grade grd){

        String result = null;

        String body = null;

        List<Grade> gradeList = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(grd);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/grade", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            System.out.println("respons VAlue is : "+ respons);

            JSONObject jsonResponse = new JSONObject(respons);

            System.out.println("Json respons VAlue is : "+ jsonResponse);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Grade grade = new Grade();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                grade.setIdGrade(jsonObjVal.getLong("idGrade"));
                if(grd.getDivisionCode().equals("")){
                    grade.setDivisionCode("");
                }else{
                    grade.setDivisionCode(jsonObjVal.getString("divisionCode"));
                }

                grade.setDepartementCode(jsonObjVal.getString("departementCode"));
                grade.setGradeCode(jsonObjVal.getString("gradeCode"));
                grade.setGradeName(jsonObjVal.getString("gradeName"));
                gradeList.add(grade);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gradeList;

    }


    public List<GradeJson> getGradeJson(GradeJson grdJson){

    String result = null;

    String body = null;

    List<GradeJson> gradeJsons = new ArrayList<>();
        try {

        body = mapper.writeValueAsString(grdJson);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/gradeJson", HttpMethod.POST, entity,
                String.class);

        System.out.println("Respon entity value is :"+ responseEntity);
        System.out.println("Respon entity body value is :"+ responseEntity.getBody());

        String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

        System.out.println("respons VAlue is : "+ respons);

        JSONObject jsonResponse = new JSONObject(respons);

        System.out.println("Json respons VAlue is : "+ jsonResponse);

        JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

        System.out.println("jsoon Array VAlue is : "+ jsonArray);

        for (int i = 0; i < jsonArray.length(); i++) {
            GradeJson grade = new GradeJson();
            JSONObject jsonObjVal = jsonArray.getJSONObject(i);
            System.out.println("Json Object Adalah :"+jsonObjVal);

            grade.setIdGrade(jsonObjVal.getLong("idGrade"));
            grade.setIdSubGrade(jsonObjVal.getLong("idSubGrade"));
            grade.setDivisionCode(jsonObjVal.getString("divisionCode"));
            grade.setDepartementCode(jsonObjVal.getString("departementCode"));
            grade.setGradeCode(jsonObjVal.getString("gradeCode"));
            grade.setGradeName(jsonObjVal.getString("gradeName"));
            grade.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
            grade.setSubGradeName(jsonObjVal.getString("subGradeName"));
            gradeJsons.add(grade);

        }

    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }

        return gradeJsons;

}

    public List<SubGrade> getSubGrade(SubGrade subGrd){

        String result = null;

        String body = null;

        List<SubGrade> subGrades = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(subGrd);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/subgrade", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                SubGrade subGrade = new SubGrade();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                subGrade.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                subGrade.setSubGradeName(jsonObjVal.getString("subGradeName"));
                subGrades.add(subGrade);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return subGrades;

    }



    public List<Employee> getEmployee(Employee emp){

        String result = null;

        String body = null;

        List<Employee> employees = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(emp);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/employee", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                employee.setId(String.valueOf(jsonObjVal.get("id")));
                employee.setDivisionCode(jsonObjVal.getString("divisionCode"));
                employee.setDepartementCode(jsonObjVal.getString("departementCode"));
                employee.setGradeCode(jsonObjVal.getString("gradeCode"));
                employee.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                employee.setEmployeeName(jsonObjVal.getString("employeeName"));
                employee.setEmployeeCode(jsonObjVal.getString("employeeCode"));
                employees.add(employee);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return employees;

    }

    public List<Question> getQuestion(Question quest){

        String result = null;

        String body = null;

        List<Question> questions = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(quest);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013//led/api/automation/search/question", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Question question = new Question();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                question.setGrade(jsonObjVal.getString("grade"));
                question.setSubGrade(jsonObjVal.getString("subGrade"));
                question.setQuestions(jsonObjVal.getString("questions"));
                question.setAnswer1(jsonObjVal.getString("answer1"));
                question.setAnswer2(jsonObjVal.getString("answer2"));
                question.setAnswer3(jsonObjVal.getString("answer3"));
                question.setAnswer4(jsonObjVal.getString("answer4"));
                question.setAnswer5(jsonObjVal.getString("answer5"));
                question.setCorrectAnswer(jsonObjVal.getString("correctAnswer"));
                question.setCompetency(jsonObjVal.getString("competency"));
                question.setLevel(jsonObjVal.getString("level"));
                questions.add(question);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return questions;

    }


    public List<Competency> getCompetency(Competency comp){

        String result = null;

        String body = null;

        List<Competency> competencies = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(comp);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/competency", HttpMethod.POST, entity,
                        String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Competency competency = new Competency();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                competency.setId(String.valueOf(jsonObjVal.getLong("id")));
                competency.setDepartementCode(jsonObjVal.getString("departementCode"));
                competency.setGradeCode(jsonObjVal.getString("gradeCode"));
                competency.setCompetencyName(jsonObjVal.getString("competencyName"));
                competency.setCompetencyCode(jsonObjVal.getString("competencyCode"));
                competency.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                competencies.add(competency);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return competencies;

    }

    public List<Competency> getCompetencyByGradeCode(Competency comp){

        String result = null;

        String body = null;

        List<Competency> competencies = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(comp);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);

            ResponseEntity<String>  responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/competencyByGradeCode", HttpMethod.POST, entity,
                        String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array VAlue is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Competency competency = new Competency();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                competency.setId(String.valueOf(jsonObjVal.getLong("id")));
                competency.setDepartementCode(jsonObjVal.getString("departementCode"));
                competency.setGradeCode(jsonObjVal.getString("gradeCode"));
                competency.setCompetencyName(jsonObjVal.getString("competencyName"));
                competency.setCompetencyCode(jsonObjVal.getString("competencyCode"));
                competency.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                competencies.add(competency);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return competencies;

    }

    public List<Division> getDivision(Division div){

        String result = null;

        String body = null;

        List<Division> divisions = new ArrayList<>();

        try {

            body = mapper.writeValueAsString(div);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/division", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array Value is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Division division = new Division();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                division.setId(jsonObjVal.getLong("id"));
                division.setDivisionCode(jsonObjVal.getString("divisionCode"));
                division.setDivisionName(jsonObjVal.getString("divisionName"));
                divisions.add(division);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public List<Departement> getDepartment (Departement dep){

        String result = null;

        String body = null;

        List<Departement> departements = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(dep);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/departement", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array Value is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Departement departement = new Departement();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                departement.setId(jsonObjVal.getLong("id"));
                departement.setDivisionCode(jsonObjVal.getString("divisionCode"));
                departement.setDepartementCode(jsonObjVal.getString("departementCode"));
                departement.setDepartementName(jsonObjVal.getString("departementName"));
                departements.add(departement);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return departements;
    }

    public String insertDivision(Division div) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(div);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/division", HttpMethod.POST, entity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            System.out.println("Response isinya adalah :"+responseEntity.getBody());
            System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

            result = String.valueOf(responseEntity.getStatusCode());

        }
        return result;
    }

    public String insertDepartement(Departement dpr) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(dpr);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/departement", HttpMethod.POST, entity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            System.out.println("Response isinya adalah :"+responseEntity.getBody());
            System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

            result = String.valueOf(responseEntity.getStatusCode());

        }
        return result;
	}

//	End Search

//    Update
        public String updateGrade(Grade grd) throws JsonProcessingException {
            String result = null;

            String body = mapper.writeValueAsString(grd);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/update/grade", HttpMethod.POST, entity,
                    String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {

                System.out.println("Response isinya adalah :"+responseEntity.getBody());
                System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

                result = String.valueOf(responseEntity.getStatusCode());

            }
            return result;
        }

    public String updateSubGrade(SubGrade subGrd) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(subGrd);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/update/subgrade", HttpMethod.POST, entity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            System.out.println("Response isinya adalah :"+responseEntity.getBody());
            System.out.println("Response Code isinya adalah :"+responseEntity.getStatusCode());

            result = String.valueOf(responseEntity.getStatusCode());

        }
        return result;
    }

    public List<Report> getReport(Report rpt) {
	    String result = null;

	    String body = null;

	    List<Report> reports = new ArrayList<>();

	    try {
	        body = mapper.writeValueAsString(rpt);

            System.out.println("===== INPUT ==== " + body);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/search/reportEmployee", HttpMethod.POST, entity,
                    String.class);

            System.out.println("Respon entity value is :"+ responseEntity);
            System.out.println("Respon entity body value is :"+ responseEntity.getBody());

            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

            JSONObject jsonResponse = new JSONObject(respons);

            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

            System.out.println("jsoon Array Value is : "+ jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                Report report = new Report();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                report.setEmployeeCode(jsonObjVal.getString("employeeCode"));
                report.setEmployeeName(jsonObjVal.getString("employeeName"));
                report.setGrade(jsonObjVal.getString("grade"));
                report.setCompetencies(jsonObjVal.getString("competencies"));
                report.setMark(jsonObjVal.getString("mark"));
                reports.add(report);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return reports;
    }

}
