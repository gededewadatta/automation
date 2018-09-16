package com.example.a48900.ledsistem.Util;

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


    public List<GradeJson> getGrade(GradeJson grdJson){

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

                grade.setId(String.valueOf(jsonObjVal.getLong("id")));
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

    public SubGrade getSubGrade(Grade subGrd){

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

//            String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

//            JSONObject jsonResponse = new JSONObject(respons);

//            JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

//            System.out.println("jsoon Array VAlue is : "+ jsonArray);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                SubGrade subGrade = new SubGrade();
//                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
//                System.out.println("Json Object Adalah :"+jsonObjVal);
//
//                subGrade.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
//                subGrade.setSubGradeName(jsonObjVal.getString("subGradeName"));
//                subGrades.add(subGrade);
//
//            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

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

                competency.setDepartementCode(jsonObjVal.getString("departementCode"));
                competency.setGradeCode(jsonObjVal.getString("gradeCode"));
                competency.setCompetencyName(jsonObjVal.getString("competencyName"));
                competencies.add(competency);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return competencies;

    }

    public List<Division> getDivision(Division dvs){

        String result = null;

        String body = null;

        List<Division> divisions = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(dvs);

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

                division.setDivisionCode(jsonObjVal.getString("divisionCode"));
                division.setDivisionName(jsonObjVal.getString("divisionName"));
                divisions.add(division);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public List<Department> getDepartment (Department dpr){

        String result = null;

        String body = null;

        List<Department> departments = new ArrayList<>();
        try {

            body = mapper.writeValueAsString(dpr);

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
                Department department = new Department();
                JSONObject jsonObjVal = jsonArray.getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                department.setDivisionCode(jsonObjVal.getString("divisionCode"));
                department.setDepartementCode(jsonObjVal.getString("departementCode"));
                department.setDepartmentName(jsonObjVal.getString("departementName"));
                departments.add(department);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public String insertDivision(Division dvs) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(dvs);

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

    public String insertDepartment(Department dpr) throws JsonProcessingException {
        String result = null;

        String body = mapper.writeValueAsString(dpr);

        System.out.println("===== INPUT ==== " + body);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7013/led/api/automation/insert/department", HttpMethod.POST, entity,
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


}
