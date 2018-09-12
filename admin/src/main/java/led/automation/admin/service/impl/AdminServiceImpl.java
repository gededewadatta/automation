/**
 * 
 */
package led.automation.admin.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import led.automation.admin.model.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import led.automation.admin.dao.AdminDAO;
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
	ObjectMapper objectMapper = new ObjectMapper();

	// insert data : start
//            Maap gw ganti pake object Mapper
	@Override
	public String insertEmployee(String body) {
		// TODO Auto-generated method stub
        try {
            employee = objectMapper.readValue(body,Employee.class);
            System.out.println("Employee Name is :"+employee.getEmployeeName());
            System.out.println("Employee Code is :"+employee.getEmployeeCode());

//            jsonResponse = new JSONObject(body);
//            employee = new Employee();
//            employee.setEmployeeName(jsonResponse.isNull("employeeName") ? "" : jsonResponse.getString("employeeName"));
//            employee.setEmployeeCode(jsonResponse.isNull("employeeCode") ? "" : jsonResponse.getString("employeeCode"));
//            employee.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
//            employee.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
//            employee.setDepartementCode(jsonResponse.isNull("departmentCode") ? "" : jsonResponse.getString("departmentCode"));
//            employee.setDivisionCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));

        } catch (IOException e) {
            e.printStackTrace();
        }
		return adminDAO.insertEmployee(employee) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertGrade(String body) {
		// TODO Auto-generated method stub

		try {
			grade = objectMapper.readValue(body,Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		grade = new Grade();
//		jsonResponse = new JSONObject(body);
//		grade.setDepartementCode(
//				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
//		grade.setCreatedBy(createdBy);
//		grade.setCreatedDate(createdDate);
//		grade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
//		grade.setGradeName(jsonResponse.isNull("gradename") ? "" : jsonResponse.getString("gradename"));

		return adminDAO.insertGrade(grade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertQuestion(String body) {
		// TODO Auto-generated method stub
		try {
			question = objectMapper.readValue(body,Question.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			competency = objectMapper.readValue(body,Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		competency.setCompetencyCode(jsonResponse.isNull("competencycode") ? "" : jsonResponse.getString("competencycode"));
		competency.setCompetencyName(jsonResponse.isNull("competencyname") ? "" : jsonResponse.getString("competencyname"));
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
	public List<Dashboard> findDashboard() {
		// TODO Auto-generated method stub
		return adminDAO.findDashboard();
	}

	@Override
	public List<Question> searchQuestion(String body) {
		// TODO Auto-generated method stub

		try {
			question = objectMapper.readValue(body,Question.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get grade : stop

		//get question : start

		//get question : stop
		return adminDAO.searchQuestionByGrade(question.getGrade());
	}
	@Override
	public List<Competency> searchCompetency(String body) {
		// TODO Auto-generated method stub

		try {
			competency = objectMapper.readValue(body,Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetency(competency.getGradeCode());
	}
	@Override
	public SubGrade searchSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String subGradeCode = null;
		subGradeCode = jsonResponse.isNull("subgradecode")?"":jsonResponse.getString("subgradecode");
		String subGradeName = null;
		subGradeName = jsonResponse.isNull("subgradename")?"":jsonResponse.getString("subgradename");
		
		return adminDAO.searchSubGrade(subGradeCode,subGradeName);
	}

	@Override
	public List<Grade> searchGrade(String body) {
		// TODO Auto-generated method stub
		Grade grd = new Grade();
		try {
			grd =  objectMapper.readValue(body,Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String gradeCode = null;
//		String gradeName = null;
//		jsonResponse = new JSONObject(body);
//		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
//		gradeName = jsonResponse.isNull("gradename")?"":jsonResponse.getString("gradename");
		
		return adminDAO.searchGrade(grd.getDepartementCode(),grd.getDivisionCode());
	}

	@Override
	public List<Employee> searchEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body,Employee.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchEmployee(employee.getEmployeeCode(),employee.getEmployeeName());
	}

	@Override
	public Employee searchEmployeeByCode(String body) {
		// TODO Auto-generated method stub
//		return adminDAO.searchEmployee(body,"");
		return null;
	}

	@Override
	public List<Departement> searchDepartement(String body) {
		// TODO Auto-generated method stub
		Departement dpr = new Departement();
		try {
			dpr =  objectMapper.readValue(body,Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		String departementCode = null;
		departementCode = jsonResponse.isNull("departementcode")?"":jsonResponse.getString("departementcode");
		String departementName = null;
		departementName = jsonResponse.isNull("departementname")?"":jsonResponse.getString("departementname");*/
		return adminDAO.searchDepartement(dpr.getDepartementCode(),dpr.getDepartementName());
	}

	@Override
	public List<Division> searchDivision(String body) {
		// TODO Auto-generated method stub
		Division dvs = new Division();
		try {
			dvs =  objectMapper.readValue(body,Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		jsonResponse = new JSONObject(body);
//		String divisionCode = null;
//		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
//		String divisionName = null;
//		divisionName = jsonResponse.isNull("divisionname")?"":jsonResponse.getString("divisionname");
		return adminDAO.searchDivision(dvs.getDivisonCode(),dvs.getDivisionName());
	}
	// search data : stop

	// generate data : start

	@Override
	public List<String> generateEmployee(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String employeeCode = null;
		
		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
		employeeCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("employeecode");
		departementCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("departementcode");
		return adminDAO.generateEmployee(divisionCode,employeeCode,departementCode);
	}

	@Override
	public List<String> generateGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String company = null;
		company=jsonResponse.isNull("company")?"":jsonResponse.getString("company");
		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("departementcode");
		
		return adminDAO.generateGrade(company,divisionCode,departementCode);
	}

	@Override
	public List<String> generateSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String gradeCode = null;
		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode")?"":jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
		
		return adminDAO.generateSubGrade(divisionCode,departementCode,gradeCode);
	}

	@Override
	public List<String> generateQuestion(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String departementCode = null;
		String gradeCode = null;
		departementCode = jsonResponse.isNull("departementcode")?"":jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
		
		return adminDAO.generateQuestion(departementCode,gradeCode);
	}

	@Override
	public List<String> generateDepartement(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String company = null;
		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
		company = jsonResponse.isNull("company")?"":jsonResponse.getString("company");
		
		return adminDAO.generateDepartement(divisionCode,company);
	}

	@Override
	public List<String> generateDivision(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String company = null;
		company = jsonResponse.isNull("company")?"":jsonResponse.getString("company");
		
		return adminDAO.generateDivision(company);
	}

	@Override
	public List<String> generateCompetency(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String gradeCode = null;
		String departementCode = null;
		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode")?"":jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
		
		return adminDAO.generateCompetency(divisionCode,departementCode,gradeCode);
	}

	// generate data : stop

}
