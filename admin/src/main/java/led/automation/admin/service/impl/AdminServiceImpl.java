/**
 * 
 */
package led.automation.admin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	@Autowired
	private PendingQuestion pendingQuestion;
	@Autowired
	private GradeJson gradeJson;

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
			employee = objectMapper.readValue(body, Employee.class);
			System.out.println("Employee Name is :" + employee.getEmployeeName());
			System.out.println("Employee Code is :" + employee.getEmployeeCode());
//			Insert employee

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
	public String updateEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body, Employee.class);
//			Insert employee

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
		return adminDAO.updateEmployee(employee) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertGrade(String body) {
		// TODO Auto-generated method stub

		try {
			grade = objectMapper.readValue(body, Grade.class);
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
		String result = null;
		int result2 = 0;
		List<String> username = new ArrayList<String>();
		try {
			question = objectMapper.readValue(body, Question.class);
			// get data username based on question grade and sub grade : start
			username = adminDAO.searchEmployeeByGradeAndSubGrade(question.getGrade(), question.getSubGrade());
			// get data username based on question grade and sub grade : stop

			// to insert into PendingQuestion Table:start
			for (String user : username) {
				pendingQuestion.setQuestions(question.getQuestions());
				pendingQuestion.setAnswer1(question.getAnswer1());
				pendingQuestion.setAnswer2(question.getAnswer2());
				pendingQuestion.setAnswer3(question.getAnswer3());
				pendingQuestion.setAnswer4(question.getAnswer4());
				pendingQuestion.setAnswer5(question.getAnswer5());
				pendingQuestion.setCorrectAnswer(question.getCorrectAnswer());
				pendingQuestion.setCreatedDate(question.getCreatedDate());
				pendingQuestion.setCreatedBy(question.getCreatedBy());
				pendingQuestion.setCompetency(question.getCompetency());
				pendingQuestion.setLevel(question.getLevel());
				pendingQuestion.setUserName(user);
				result2 += adminDAO.insertPendingQuestion(pendingQuestion);
			}
			// to insert into PendingQuestion Table:stop
			if (result2 > 0)
				result = adminDAO.insertQuestion(question) == 0 ? "Failure" : "Success";
			else
				result = "Failure";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String insertDivision(String body) {
		// TODO Auto-generated method stub
		try {
			division = objectMapper.readValue(body,Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return adminDAO.insertDivision(division) == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateDivision(String body) {
		// TODO Auto-generated method stub
		try {
			division = objectMapper.readValue(body,Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return adminDAO.updateDivision(division) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertDepartement(String body) {
		// TODO Auto-generated method stub
		try {
			departement = objectMapper.readValue(body,Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		departement = new Departement();
		departement.setDivisionCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		departement.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		departement.setDepartementName(
				jsonResponse.isNull("departementname") ? "" : jsonResponse.getString("departementname"));
		departement.setCreatedBy(createdBy);
		departement.setCreatedDate(createdDate);*/
		return adminDAO.insertDepartement(departement) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		subGrade = new SubGrade();
		subGrade.setIdSubGrade(jsonResponse.isNull("id") ? null : jsonResponse.getLong("id"));
		subGrade.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subGradeName") ? "" : jsonResponse.getString("subGradeName"));
		subGrade.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);

		return adminDAO.insertSubGrade(subGrade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertCompetency(String body) {
		// TODO Auto-generated method stub
		try {
			competency = objectMapper.readValue(body, Competency.class);
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
		int result = 0;
		int result2 = 0;
		List<String> username = new ArrayList<String>();
		try {
			question = objectMapper.readValue(body, Question.class);
			// get data username based on question grade and sub grade : start
			username = adminDAO.searchEmployeeByGradeAndSubGrade(question.getGrade(), question.getSubGrade());
			// get data username based on question grade and sub grade : stop

			// to insert into PendingQuestion Table:start
			for (String user : username) {
				pendingQuestion.setQuestions(question.getQuestions());
				pendingQuestion.setAnswer1(question.getAnswer1());
				pendingQuestion.setAnswer2(question.getAnswer2());
				pendingQuestion.setAnswer3(question.getAnswer3());
				pendingQuestion.setAnswer4(question.getAnswer4());
				pendingQuestion.setAnswer5(question.getAnswer5());
				pendingQuestion.setCorrectAnswer(question.getCorrectAnswer());
				pendingQuestion.setCreatedDate(question.getCreatedDate());
				pendingQuestion.setCreatedBy(question.getCreatedBy());
				pendingQuestion.setCompetency(question.getCompetency());
				pendingQuestion.setLevel(question.getLevel());
				pendingQuestion.setUserName(user);
				result2 += adminDAO.insertPendingQuestion(pendingQuestion);
			}
			// to insert into PendingQuestion Table:stop
			if (result2 > 0)
				result = adminDAO.insertQuestion(question);
			else
				result = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
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
		competency.setCompetencyCode(
				jsonResponse.isNull("competencycode") ? "" : jsonResponse.getString("competencycode"));
		competency.setCompetencyName(
				jsonResponse.isNull("competencyname") ? "" : jsonResponse.getString("competencyname"));
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
			question = objectMapper.readValue(body, Question.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// get grade : stop

		// get question : start

		// get question : stop
		return adminDAO.searchQuestionByGrade(question.getGrade());
	}

	@Override
	public List<Competency> searchCompetency(String body) {
		// TODO Auto-generated method stub

		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetency(competency.getCompetencyCode(),competency.getCompetencyName());
	}

	@Override
	public List<Competency> searchCompetencyByGradeCode(String body) {
		// TODO Auto-generated method stub

		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetencyByGradeCode(competency.getGradeCode());
	}

    @Override
    public List<Report> searchReportEmployee(String body) {
		// TODO Auto-generated method stub
		Report rep = new Report();
		try {
			rep = objectMapper.readValue(body, Report.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		jsonResponse = new JSONObject(body);
//		String divisionCode = null;
//		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
//		String divisionName = null;
//		divisionName = jsonResponse.isNull("divisionname")?"":jsonResponse.getString("divisionname");
		return adminDAO.searchReportEmployee(rep.getEmployeeCode(), rep.getGrade());
    }

    @Override
	public List<SubGrade> searchSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String subGradeCode = null;
		subGradeCode = jsonResponse.isNull("subgradeCode") ? "" : jsonResponse.getString("subgradeCode");
		String subGradeName = null;
		subGradeName = jsonResponse.isNull("subgradeName") ? "" : jsonResponse.getString("subgradeName");

		return adminDAO.searchSubGrade(subGradeCode, subGradeName);
	}

	@Override
	public List<GradeJson> searchGradeJson(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);

		System.out.println(jsonResponse);
		String departmentCode = "";
		departmentCode = jsonResponse.isNull("departementCode") ? "" : jsonResponse.getString("departementCode");
		String departmentName = "";
		departmentName = jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode");

		return adminDAO.searchGradeJson(departmentCode, departmentName);
	}

	@Override
	public List<Grade> searchGrade(String body) {
		// TODO Auto-generated method stub
		Grade grd = new Grade();
		try {
			grd = objectMapper.readValue(body, Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("INI COBA MASUK");
//		String gradeCode = null;
//		String gradeName = null;
//		jsonResponse = new JSONObject(body);
//		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
//		gradeName = jsonResponse.isNull("gradename")?"":jsonResponse.getString("gradename");

		return adminDAO.searchGrade(grd.getDepartementCode(), grd.getDivisionCode());
	}

	@Override
	public List<Grade> searchGradePopup(String body) {
		// TODO Auto-generated method stub
		Grade grd = new Grade();
		try {
			grd = objectMapper.readValue(body, Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("INI COBA MASUK");

		return adminDAO.searchGradePopup(grd.getDepartementCode(), grd.getDivisionCode(),grd.getGradeCode(),grd.getGradeName());
	}


	@Override
	public List<Employee> searchEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body, Employee.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchEmployee(employee.getEmployeeCode(), employee.getEmployeeName());
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
		Departement dep = new Departement();
		try {
			dep = objectMapper.readValue(body, Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * jsonResponse = new JSONObject(body); String departementCode = null;
		 * departementCode =
		 * jsonResponse.isNull("departementcode")?"":jsonResponse.getString(
		 * "departementcode"); String departementName = null; departementName =
		 * jsonResponse.isNull("departementname")?"":jsonResponse.getString(
		 * "departementname");
		 */
		return adminDAO.searchDepartement(dep.getDivisionCode(), dep.getDepartementCode());
	}

	@Override
	public List<Departement> searchDepartementPopup(String body) {
		// TODO Auto-generated method stub
		Departement dep = new Departement();
		try {
			dep = objectMapper.readValue(body, Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchDepartementPopup(dep.getDivisionCode(), dep.getDepartementCode(), dep.getDepartementName());
	}

	@Override
	public List<Division> searchDivision(String body) {
		// TODO Auto-generated method stub
		Division div = new Division();
		try {
			div = objectMapper.readValue(body, Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		jsonResponse = new JSONObject(body);
//		String divisionCode = null;
//		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
//		String divisionName = null;
//		divisionName = jsonResponse.isNull("divisionname")?"":jsonResponse.getString("divisionname");
		return adminDAO.searchDivision(div.getDivisionCode(), div.getDivisionName());
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

		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		employeeCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("employeecode");
		departementCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("departementcode");
		return adminDAO.generateEmployee(divisionCode, employeeCode, departementCode);
	}

	@Override
	public List<String> generateGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String company = null;
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("departementcode");

		return adminDAO.generateGrade(company, divisionCode, departementCode);
	}

	@Override
	public List<String> generateSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String gradeCode = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateSubGrade(divisionCode, departementCode, gradeCode);
	}

	@Override
	public List<String> generateQuestion(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String departementCode = null;
		String gradeCode = null;
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateQuestion(departementCode, gradeCode);
	}

	@Override
	public List<String> generateDepartement(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String company = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");

		return adminDAO.generateDepartement(divisionCode, company);
	}

	@Override
	public List<String> generateDivision(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String company = null;
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");

		return adminDAO.generateDivision(company);
	}

	@Override
	public List<String> generateCompetency(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String gradeCode = null;
		String departementCode = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateCompetency(divisionCode, departementCode, gradeCode);
	}

	@Override
	public List<String> findSubGradeByGradeCode(String body) {
		// TODO Auto-generated method stub
		String gradeCode = null;
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");
		return adminDAO.findSubGradeByGradeCode(gradeCode);
	}

	/*@Override
	public List<String> findGradeByDeptCode(String body) {
		return null;
	}

	@Override
	public List<String> findDepartementByDivCode(String body) {
		return null;
	}*/

	// generate data : stop

}
