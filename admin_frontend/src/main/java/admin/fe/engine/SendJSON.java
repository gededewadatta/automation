/**
 *
 */
package admin.fe.engine;

import admin.fe.controller.common.Resources;
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

    public String insertEmployee(Employee emp) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(emp),Resources.proxyHost+"led/api/automation/insert/employee");
    }

    public String insertGrade(GradeJson grd) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(grd),Resources.proxyHost+"led/api/automation/insert/grade");
    }

    public String insertQuestion(Question quest) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(quest),Resources.proxyHost+"led/api/automation/insert/question");
    }

    public String insertDivision(Division div) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(div),Resources.proxyHost+"led/api/automation/insert/division");
    }

    public String insertDepartement(Departement dpr) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(dpr),Resources.proxyHost+"led/api/automation/insert/departement");
    }

    public String insertCompetency(Competency cmp) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(cmp),Resources.proxyHost+"led/api/automation/insert/competency");
    }

//    Update

    public String updateGrade(GradeJson grd) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(grd),Resources.proxyHost+"led/api/automation/update/grade");
    }

    public String updateSubGrade(SubGrade subGrd) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(subGrd),Resources.proxyHost+"led/api/automation/update/subgrade");
    }

    public String updateDivision(Division div) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(div),"http://localhost:7002/led/api/automation/update/division");
    }

    public String updateDepartement(Departement dep) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(dep),"http://localhost:7002/led/api/automation/update/departement");
    }

    public String updateEmployee(Employee emp) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(emp),Resources.proxyHost+"led/api/automation/update/employee");
    }

    public String updateCompetency(Competency cmp) throws JsonProcessingException {
        return insertAndUpdate(mapper.writeValueAsString(cmp),"http://localhost:7002/led/api/automation/update/competency");
    }

//  end update

//  Search

    public List<Dashboard> getDashBoard(){

        List<Dashboard> dashboards = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(new Dashboard()), Resources.proxyHost+"led/api/automation/search/dashboard").length(); i++) {
                Dashboard dashboard = new Dashboard();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(new Dashboard()), Resources.proxyHost+"led/api/automation/search/dashboard").getJSONObject(i);
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

        List<Grade> gradeList = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(grd), Resources.proxyHost+"led/api/automation/search/grade").length(); i++) {
                Grade grade = new Grade();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(grd), Resources.proxyHost+"led/api/automation/search/grade").getJSONObject(i);
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
            for (int i = 0; i < getData(mapper.writeValueAsString(grdJson),Resources.proxyHost+"led/api/automation/search/gradeJson").length(); i++) {
                GradeJson grade = new GradeJson();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(grdJson),Resources.proxyHost+"led/api/automation/search/gradeJson").getJSONObject(i);
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

        List<SubGrade> subGrades = new ArrayList<>();
        try {
            for (int i = 0; i < getData(mapper.writeValueAsString(subGrd),Resources.proxyHost+"led/api/automation/search/subgrade").length(); i++) {
                SubGrade subGrade = new SubGrade();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(subGrd),Resources.proxyHost+"led/api/automation/search/subgrade").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                subGrade.setId(jsonObjVal.getLong("idSubGrade"));
                subGrade.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                subGrade.setSubGradeName(jsonObjVal.getString("subGradeName"));
                subGrades.add(subGrade);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return subGrades;
    }

    public List<SubGrade> getSubGradePopUp (SubGrade subgrd){

        List<SubGrade> subGrades = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(subgrd),Resources.proxyHost+"led/api/automation/search/subgradepopup").length(); i++) {
                SubGrade subGrade = new SubGrade();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(subgrd),Resources.proxyHost+"led/api/automation/search/subgradepopup").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                subGrade.setId(jsonObjVal.getLong("idSubGrade"));
                subGrade.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                subGrade.setSubGradeName(jsonObjVal.getString("subGradeName"));
                subGrade.setGradeCode(jsonObjVal.getString("gradeCode"));
                subGrades.add(subGrade);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return subGrades;
    }



    public List<Employee> getEmployee(Employee emp){

        List<Employee> employees = new ArrayList<>();
        try {
            for (int i = 0; i < getData(mapper.writeValueAsString(emp),Resources.proxyHost+"led/api/automation/search/employee").length(); i++) {
                Employee employee = new Employee();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(emp),Resources.proxyHost+"led/api/automation/search/employee").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                employee.setId(String.valueOf(jsonObjVal.get("id")));
                employee.setDivisionCode(jsonObjVal.getString("divisionCode"));
                employee.setDepartementCode(jsonObjVal.getString("departementCode"));
                employee.setGradeCode(jsonObjVal.getString("gradeCode"));
                employee.setSubGradeCode(jsonObjVal.getString("subGradeCode"));
                employee.setEmployeeName(jsonObjVal.getString("employeeName"));
                employee.setEmployeeCode(jsonObjVal.getString("employeeCode"));
                employee.setUserName(jsonObjVal.getString("userName"));
                employees.add(employee);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return employees;

    }

    public List<Question> getQuestion(Question quest){

        List<Question> questions = new ArrayList<>();
        try {
            for (int i = 0; i < getData(mapper.writeValueAsString(quest),Resources.proxyHost+"/led/api/automation/search/question").length(); i++) {
                Question question = new Question();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(quest),Resources.proxyHost+"/led/api/automation/search/question").getJSONObject(i);
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

        List<Competency> competencies = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(comp),Resources.proxyHost+"led/api/automation/search/competency").length(); i++) {
                Competency competency = new Competency();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(comp),Resources.proxyHost+"led/api/automation/search/competency").getJSONObject(i);
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

        List<Competency> competencies = new ArrayList<>();
        try {
            for (int i = 0; i < getData(mapper.writeValueAsString(comp),Resources.proxyHost+"led/api/automation/search/competencyByGradeCode").length(); i++) {
                Competency competency = new Competency();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(comp),Resources.proxyHost+"led/api/automation/search/competencyByGradeCode").getJSONObject(i);
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

        List<Division> divisions = new ArrayList<>();

        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(div),Resources.proxyHost+"led/api/automation/search/division").length(); i++) {
                Division division = new Division();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(div),Resources.proxyHost+"led/api/automation/search/division").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                division.setId(jsonObjVal.getLong("id"));
                division.setDivisionCode(jsonObjVal.getString("divisionCode"));
                division.setDivisionName(jsonObjVal.getString("divisionName"));
                division.setCreatedBy(jsonObjVal.getString("createdBy"));
                divisions.add(division);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public List<Departement> getDepartment (Departement dep){

        List<Departement> departements = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(dep),Resources.proxyHost+"led/api/automation/search/departement").length(); i++) {
                Departement departement = new Departement();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(dep),Resources.proxyHost+"led/api/automation/search/departement").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                departement.setId(jsonObjVal.getLong("id"));
                departement.setDivisionCode(jsonObjVal.getString("divisionCode"));
                departement.setDepartementCode(jsonObjVal.getString("departementCode"));
                departement.setDepartementName(jsonObjVal.getString("departementName"));
                departement.setCreatedBy(jsonObjVal.getString("createdBy"));
                departements.add(departement);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return departements;
    }

    public List<Departement> getDepartmentPopUp (Departement dep){

        List<Departement> departements = new ArrayList<>();
        try {
            for (int i = 0; i < getData(mapper.writeValueAsString(dep),Resources.proxyHost+"led/api/automation/search/departementpopup" ).length(); i++) {
                Departement departement = new Departement();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(dep),Resources.proxyHost+"led/api/automation/search/departementpopup" ).getJSONObject(i);
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

    public List<Grade> getGradePopup(Grade grd){

        List<Grade> gradeList = new ArrayList<>();
        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(grd),Resources.proxyHost+"led/api/automation/search/gradepopup").length(); i++) {
                Grade grade = new Grade();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(grd),Resources.proxyHost+"led/api/automation/search/gradepopup").getJSONObject(i);
                System.out.println("Json Object Adalah :"+jsonObjVal);

                grade.setIdGrade(jsonObjVal.getLong("idGrade"));
                grade.setDivisionCode(jsonObjVal.getString("divisionCode"));
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


    public List<Report> getReport(Report rpt) {

        List<Report> reports = new ArrayList<>();

        try {

            for (int i = 0; i < getData(mapper.writeValueAsString(rpt),Resources.proxyHost+"led/api/automation/search/reportEmployee").length(); i++) {
                Report report = new Report();
                JSONObject jsonObjVal = getData(mapper.writeValueAsString(rpt),Resources.proxyHost+"led/api/automation/search/reportEmployee").getJSONObject(i);
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

//	End Search

// Function get & updateInsert

    public JSONArray getData(String body,String uri){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity,
                String.class);

        System.out.println("Respon entity value is :"+ responseEntity);
        System.out.println("Respon entity body value is :"+ responseEntity.getBody());

        String respons = "{\"arrayJson\""+":"+responseEntity.getBody()+"}";

        System.out.println("respons VAlue is : "+ respons);

        JSONObject jsonResponse = new JSONObject(respons);

        System.out.println("Json respons VAlue is : "+ jsonResponse);

        JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");

        return  jsonArray;
    }

    public String insertAndUpdate(String body, String uri){
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity,
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            if (responseEntity.getStatusCode() == HttpStatus.OK) {

                if(responseEntity.getBody().equals(null) || responseEntity.getBody() ==  null){
                    result = null;
                }else{
                    if(responseEntity.getBody().equals("Failure")){
                        result = String.valueOf(responseEntity.getBody());
                    }else{
                        result = String.valueOf(responseEntity.getStatusCode());
                    }
                }
            }

        }

        return result;
    }

}
