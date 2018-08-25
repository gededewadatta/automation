/**
 * 
 */
package led.automation.admin.service.impl;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import led.automation.admin.dao.AdminDAO;
import led.automation.admin.model.Employee;
import led.automation.admin.model.Grade;
import led.automation.admin.model.Question;
import led.automation.admin.model.SubGrade;
import led.automation.admin.model.Competency;
import led.automation.admin.model.Departement;
import led.automation.admin.model.Division;
import led.automation.admin.service.AdminService;

/**
 * @author gederanadewadatta
 *
 */
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private Employee employee;
	@Autowired
	private Grade grade;
	@Autowired
	private Departement departement;
	@Autowired
	private Division division;
	@Autowired
	private SubGrade subGrade;
	@Autowired
	private Question question;
	@Autowired
	private Competency competency;

	private JSONObject jsonResponse;

	static String createdBy = "ADMIN";
	static Date createdDate = new Date();

	// insert data : start
	@Override
	public String insertEmployee(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		employee = new Employee();
		employee.setEmployeeName(jsonResponse.isNull("employeename") ? "" : jsonResponse.getString("employeename"));
		employee.setEmployeeCode(jsonResponse.isNull("employeecode") ? "" : jsonResponse.getString("employeecode"));
		employee.setSubGradeCode(jsonResponse.isNull("subgrade") ? "" : jsonResponse.getString("subgrade"));
		employee.setGradeCode(jsonResponse.isNull("grade") ? "" : jsonResponse.getString("grade"));
		employee.setDepartementCode(jsonResponse.isNull("departement") ? "" : jsonResponse.getString("departement"));
		employee.setDivisionCode(jsonResponse.isNull("division") ? "" : jsonResponse.getString("division"));
		return adminDAO.insertEmployee(employee) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertGrade(String body) {
		// TODO Auto-generated method stub
		grade = new Grade();
		jsonResponse = new JSONObject(body);
		grade.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		grade.setCreatedBy(createdBy);
		grade.setCreatedDate(createdDate);
		grade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		grade.setGradeName(jsonResponse.isNull("gradename") ? "" : jsonResponse.getString("gradename"));

		return adminDAO.insertGrade(grade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertQuestion(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		question = new Question();
		question.setGrade(jsonResponse.isNull("grade") ? "" : jsonResponse.getString("grade"));
		question.setSubGrade(jsonResponse.isNull("subGrade") ? "" : jsonResponse.getString("subGrade"));
		question.setAnswer1(jsonResponse.isNull("answer1") ? "" : jsonResponse.getString("answer1"));
		question.setAnswer2(jsonResponse.isNull("answer2") ? "" : jsonResponse.getString("answer2"));
		question.setAnswer3(jsonResponse.isNull("answer3") ? "" : jsonResponse.getString("answer3"));
		question.setAnswer4(jsonResponse.isNull("answer4") ? "" : jsonResponse.getString("answer4"));
		question.setAnswer5(jsonResponse.isNull("answer5") ? "" : jsonResponse.getString("answer5"));
		question.setCorrectAnswer(jsonResponse.isNull("correctAnswer") ? "" : jsonResponse.getString("correctAnswer"));
		question.setCreatedDate(createdDate);
		question.setCreatedBy(createdBy);
		question.setCompetency(jsonResponse.isNull("competency") ? "" : jsonResponse.getString("competency"));
		question.setQuestions(jsonResponse.isNull("questions") ? "" : jsonResponse.getString("questions"));
		return adminDAO.insertQuestion(question) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertDivision(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		division.setDivisionName(jsonResponse.isNull("divisionname") ? "" : jsonResponse.getString("divisionname"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);

		return adminDAO.insertDivision(division) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertDepartement(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		departement = new Departement();
		departement.setDivisionCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		departement.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		departement.setDepartementName(
				jsonResponse.isNull("departementname") ? "" : jsonResponse.getString("departementname"));
		departement.setCreatedBy(createdBy);
		departement.setCreatedDate(createdDate);
		return adminDAO.insertDepartement(departement) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		subGrade = new SubGrade();
		subGrade.setSubGradeCode(jsonResponse.isNull("subgradecode") ? "" : jsonResponse.getString("subgradecode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subgradename") ? "" : jsonResponse.getString("subgradename"));
		subGrade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);

		return adminDAO.insertSubGrade(subGrade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertCompetency(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		competency = new Competency();
		competency.setCompetency(jsonResponse.isNull("competency") ? "" : jsonResponse.getString("competency"));
		competency.setCreatedBy(createdBy);
		competency.setCreatedDate(createdDate);
		competency.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		competency.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		return adminDAO.insertCompetency(competency) == 0 ? "Failure" : "Success";
	}
	// insert data : stop

	// upload data : start
	@Override
	public int uploadQuestion(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		question = new Question();
		question.setGrade(jsonResponse.isNull("grade") ? "" : jsonResponse.getString("grade"));
		question.setSubGrade(jsonResponse.isNull("subGrade") ? "" : jsonResponse.getString("subGrade"));
		question.setAnswer1(jsonResponse.isNull("answer1") ? "" : jsonResponse.getString("answer1"));
		question.setAnswer2(jsonResponse.isNull("answer2") ? "" : jsonResponse.getString("answer2"));
		question.setAnswer3(jsonResponse.isNull("answer3") ? "" : jsonResponse.getString("answer3"));
		question.setAnswer4(jsonResponse.isNull("answer4") ? "" : jsonResponse.getString("answer4"));
		question.setAnswer5(jsonResponse.isNull("answer5") ? "" : jsonResponse.getString("answer5"));
		question.setCorrectAnswer(jsonResponse.isNull("correctAnswer") ? "" : jsonResponse.getString("correctAnswer"));
		question.setQuestions(jsonResponse.isNull("questions") ? "" : jsonResponse.getString("questions"));
		question.setCreatedBy(createdBy);
		question.setCreatedDate(createdDate);
		question.setCompetency(jsonResponse.isNull("competency") ? "" : jsonResponse.getString("competency"));
		return adminDAO.insertQuestion(question);
	}

	@Override
	public int uploadEmployee(String body) {
		// TODO Auto-generated method stub
		int result = 0;
		jsonResponse = new JSONObject(body);
		division = new Division();
		departement = new Departement();
		grade = new Grade();
		subGrade = new SubGrade();
		competency = new Competency();
		// insert division :start
		division.setDivisonCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		division.setDivisionName(jsonResponse.isNull("divisionname") ? "" : jsonResponse.getString("divisionname"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);
		result += adminDAO.insertDivision(division);
		// insert division :stop
		// insert departement :start
		departement.setDivisionCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		departement.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		departement.setDepartementName(
				jsonResponse.isNull("departementname") ? "" : jsonResponse.getString("departementname"));
		departement.setCreatedBy(createdBy);
		departement.setCreatedDate(createdDate);
		result += adminDAO.insertDepartement(departement);
		// insert departement :stop
		// insert grade :start
		grade.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		grade.setCreatedBy(createdBy);
		grade.setCreatedDate(createdDate);
		grade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		grade.setGradeName(jsonResponse.isNull("gradename") ? "" : jsonResponse.getString("gradename"));
		result += adminDAO.insertGrade(grade);
		// insert grade :stop
		// insert sub grade :start
		subGrade.setSubGradeCode(jsonResponse.isNull("subgradecode") ? "" : jsonResponse.getString("subgradecode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subgradename") ? "" : jsonResponse.getString("subgradename"));
		subGrade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);
		result += adminDAO.insertSubGrade(subGrade);
		// insert sub grade :stop
		// insert competency : start
		competency.setCompetency(jsonResponse.isNull("competency") ? "" : jsonResponse.getString("competency"));
		competency.setCreatedBy(createdBy);
		competency.setCreatedDate(createdDate);
		competency.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		competency.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		result += adminDAO.insertCompetency(competency);
		// insert competency : stop
		return result;
	}
	// upload data : stop

	// search data : start
	@Override
	public Question searchQuestion(String body) {
		// TODO Auto-generated method stub

		return adminDAO.searchQuestion(body);
	}

	@Override
	public SubGrade searchSubGrade(String body) {
		// TODO Auto-generated method stub

		return adminDAO.searchSubGrade(body);
	}

	@Override
	public Grade searchGrade(String body) {
		// TODO Auto-generated method stub

		return adminDAO.searchGrade(body);
	}

	@Override
	public Employee searchEmployee(String body) {
		// TODO Auto-generated method stub

		return adminDAO.searchEmployee(body);
	}

	@Override
	public Departement searchDepartement(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Division searchDivision(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	// search data : stop

}
