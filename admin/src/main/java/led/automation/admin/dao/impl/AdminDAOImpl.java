/**
 *
 */
package led.automation.admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import led.automation.admin.dao.AdminDAO;
import led.automation.admin.model.*;
import led.automation.admin.repository.*;

/**
 * @author gederanadewadatta
 *
 */
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	DataSource dataSource;

	@Autowired
	DashboardRepository dashboardRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	SubGradeRepository subGradeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartementRepository departementRepository;

	@Autowired
	DivisionRepository divisionRepository;

	@Autowired
	CompetencyRepository competencyRepository;
	@Autowired
	PendingQuestionRepository pendingQuestionRepository;
	//insert data :start
	@Override
	public int insertEmployee(Employee body) {
		// TODO Auto-generated method stub
		System.out.println("Dao Employee Name is :"+ body.getEmployeeName());
		System.out.println("Dao Employee Code is :"+ body.getEmployeeCode());
		Employee res = new Employee();
		res = employeeRepository.save(body);
		return res.getId()==0?0:1;
	}

	@Override
	public int insertGrade(Grade body) {
		// TODO Auto-generated method stub
		Grade grade = new Grade();
		grade = gradeRepository.save(body);
		return grade.getId()==0?0:1;
	}

	@Override
	public int insertSubGrade(SubGrade body) {
		// TODO Auto-generated method stub
		SubGrade subGrade = new SubGrade();
		subGrade = subGradeRepository.save(body);

		return subGrade.getId()==0?0:1;
	}

	@Override
	public int insertQuestion(Question body) {
		// TODO Auto-generated method stub
		Question question = new Question();
		question = questionRepository.save(body);
		return question.getId()>0?1:0;
	}

	@Override
	public int insertDivision(Division division) {
		// TODO Auto-generated method stub
		Division div = new Division();
		div = divisionRepository.save(division);
//		return div.getId()>0?1:0;
        return div.getId()==0?0:1;
    }

	@Override
	public int insertDepartement(Departement departement) {
		// TODO Auto-generated method stub
		Departement dep = new Departement();
		dep = departementRepository.save(departement);
//		return dep.getId()>0?1:0;
		return dep.getId()==0?1:0;
	}

	@Override
	public int insertCompetency(Competency competency) {
		// TODO Auto-generated method stub
		Competency com = new Competency();
		competency.setCreatedDate(new Date());
		com = competencyRepository.save(competency);
		return com.getId()>0?1:0;
	}

	//insert data : stop
	@Override
	public List<Dashboard> findDashboard() {
		// TODO Auto-generated method stub
		List<Dashboard> dashboard = new ArrayList<>();

		dashboard = dashboardRepository.findAll();

		return dashboard;
	}

	@Override
	public List<Departement> searchDepartement(String departementCode, String departementName) {
		// TODO Auto-generated method stub
		List<Departement> departement = new ArrayList<>();
		if(departementCode.equals("")&&departementName.equals("")) {
			departement = departementRepository.findAll();
		}
		else if(departementCode.equals("")&& !departementName.equals("")) {
			departement = departementRepository.findByDepartementName(departementName);
		}
		else if(!departementCode.equals("")&& departementName.equals("")) {
			departement = departementRepository.findByDepartementCode(departementCode);
		}else {
			departement = departementRepository.findByDepartementCodeAndName(departementCode,departementName);
		}

		return departement;
	}

	@Override
	public List<Employee> searchEmployee(String employeeCode, String employeeName) {
		// TODO Auto-generated method stub
		List<Employee> employee = new ArrayList<>();
		if((employeeCode.equals("")||employeeCode == null)&&(employeeName.equals("")||employeeName == null)) {
			employee = employeeRepository.findAll();
		}
		else if(employeeCode.equals("")&&!employeeName.equals("")){
			employee = employeeRepository.findByEmployeeName(employeeName);
		}
		else if(!employeeCode.equals("")&& employeeName.equals("")) {
			employee = employeeRepository.findByEmployeeCode(employeeCode);
		}else {
			employee = employeeRepository.findByEmployeeCodeAndName(employeeCode,employeeName);
		}

		return employee;
	}

	@Override
	public List<Grade> searchGrade(String divisionCode, String departmentCode) {
		// TODO Auto-generated method stub
		List<Grade> grade = new ArrayList<>();
		if(divisionCode.equals("")&&departmentCode.equals("")) {
			grade =  gradeRepository.findAll();
		}
		else if(divisionCode.equals("")&&!departmentCode.equals("")){
			grade = gradeRepository.findByDepartmentCode(departmentCode);
		}
		else if(!divisionCode.equals("")&& departmentCode.equals("")) {
			grade = gradeRepository.findByDivisionCode(divisionCode);
		}else {
			grade = gradeRepository.findByDepartmentCodeAndDivisionName(divisionCode,departmentCode);
		}

		return grade;
	}

	@Override
	public SubGrade searchSubGrade(String subGradeCode, String subGradeName) {
		// TODO Auto-generated method stub
		SubGrade subGrade = new SubGrade();
		if(subGradeCode.equals("")&& subGradeName.equals("")) {
			subGrade = (SubGrade) subGradeRepository.findAll();
		}
		else if(subGradeCode.equals("")&&!subGradeName.equals("")){
			subGrade = subGradeRepository.findBySubGradeName(subGradeName);
		}
		else if(!subGradeCode.equals("")&& subGradeName.equals("")) {
			subGrade = subGradeRepository.findBySubGradeCode(subGradeCode);
		}else {
			subGrade = subGradeRepository.findBySubGradeCodeAndName(subGradeCode,subGradeName);
		}

		return subGrade;
	}

	@Override
	public List<GradeJson> searchGradeJson(String departmentCode, String divisionCode) {
		// TODO Auto-generated method stub
		List<GradeJson> gradeJsons = new ArrayList<>();
		System.out.println("Dao Impl Departemen:"+departmentCode);
		System.out.println("Dao Impl Division:"+divisionCode);
		List<Grade> gradeList = new ArrayList<>();

		if(departmentCode.equals("")&& divisionCode.equals("")) {

			gradeList = gradeRepository.findAll();

			for(Grade grade : gradeList){

				SubGrade subGrd =  new SubGrade();

				GradeJson grdjson =  new GradeJson();

				subGrd = subGradeRepository.findBySubGradeCode(grade.getGradeCode());

				grdjson.setGradeCode(grade.getGradeCode());
				grdjson.setGradeName(grade.getGradeName());
				grdjson.setSubGradeCode(subGrd.getSubGradeCode());
				grdjson.setSubGradeName(subGrd.getSubGradeName());
				grdjson.setDepartementCode(grade.getDepartementCode());
				grdjson.setDivisionCode(grade.getDivisionCode());
				grdjson.setIdGrade(grade.getId());
				grdjson.setIdSubGrade(subGrd.getId());

				gradeJsons.add(grdjson);
			}

		}
		else if(!departmentCode.equals("")&&divisionCode.equals("")){

			gradeList = gradeRepository.findByDepartmentCode(departmentCode);

			for(Grade grade : gradeList){

				SubGrade subGrd =  new SubGrade();

				GradeJson grdjson =  new GradeJson();

				subGrd = subGradeRepository.findBySubGradeCode(grade.getGradeCode());

				grdjson.setGradeCode(grade.getGradeCode());
				grdjson.setGradeName(grade.getGradeName());
				grdjson.setSubGradeCode(subGrd.getSubGradeCode());
				grdjson.setSubGradeName(subGrd.getSubGradeName());
				grdjson.setDepartementCode(grade.getDepartementCode());
				grdjson.setDivisionCode(grade.getDivisionCode());
				grdjson.setIdGrade(grade.getId());
				grdjson.setIdGrade(subGrd.getId());

				gradeJsons.add(grdjson);
			}
		}
		else if(departmentCode.equals("")&& !divisionCode.equals("")) {
			gradeList = gradeRepository.findByDivisionCode(divisionCode);

			for(Grade grade : gradeList){

				SubGrade subGrd =  new SubGrade();

				GradeJson grdjson =  new GradeJson();

				subGrd = subGradeRepository.findBySubGradeCode(grade.getGradeCode());

				grdjson.setGradeCode(grade.getGradeCode());
				grdjson.setGradeName(grade.getGradeName());
				grdjson.setSubGradeCode(subGrd.getSubGradeCode());
				grdjson.setSubGradeName(subGrd.getSubGradeName());
				grdjson.setDepartementCode(grade.getDepartementCode());
				grdjson.setDivisionCode(grade.getDivisionCode());
				grdjson.setIdGrade(grade.getId());
				grdjson.setIdGrade(subGrd.getId());

				gradeJsons.add(grdjson);
			}
		}else {
			gradeList = gradeRepository.findByDepartmentCodeAndDivisionName(departmentCode,divisionCode);

			for(Grade grade : gradeList){

				SubGrade subGrd =  new SubGrade();

				GradeJson grdjson =  new GradeJson();

				subGrd = subGradeRepository.findBySubGradeCode(grade.getGradeCode());

				grdjson.setGradeCode(grade.getGradeCode());
				grdjson.setGradeName(grade.getGradeName());
				grdjson.setSubGradeCode(subGrd.getSubGradeCode());
				grdjson.setSubGradeName(subGrd.getSubGradeName());
				grdjson.setDepartementCode(grade.getDepartementCode());
				grdjson.setDivisionCode(grade.getDivisionCode());
				grdjson.setIdGrade(grade.getId());
				grdjson.setIdGrade(subGrd.getId());

				gradeJsons.add(grdjson);
			}
		}

		return gradeJsons;
	}

//	@Override
//	public List<Competency> searchCompetency(String competencyCode, String competencyName) {
//		// TODO Auto-generated method stub
//		List<Competency> competency = new ArrayList<>();
//		if(competencyCode.equals("")&&competencyName.equals("")) {
//			competency = competencyRepository.findAll();
//		}
//		else if(competencyCode.equals("")&&!competencyName.equals("")){
//			competency = competencyRepository.findByCompetencyName(competencyName);
//		}
//		else if(!competencyCode.equals("")&& competencyName.equals("")) {
//			competency = competencyRepository.findByCompetencyCode(competencyCode);
//		}else {
//			competency = competencyRepository.findByCompetencyCodeAndName(competencyCode,competencyName);
//		}
//
//		return competency;
//	}

	@Override
	public List<Competency> searchCompetency(String gradeCode) {
		// TODO Auto-generated method stub
		List<Competency> competency = new ArrayList<>();
		competency = competencyRepository.findAll();

		return competency;
	}

	@Override
	public List<Division> searchDivision(String divisionCode, String divisionName) {
		// TODO Auto-generated method stub
		List<Division> division = new ArrayList<>();
		if(divisionCode.equals("")&&divisionName.equals("")) {
			division = divisionRepository.findAll();
		}
		else if(divisionCode.equals("")&&!divisionName.equals("")){
			division = divisionRepository.findByDivisionName(divisionName);
		}
		else if(!divisionCode.equals("")&& divisionName.equals("")) {
			division = divisionRepository.findByDivisionCode(divisionCode);
		}else {
			division = divisionRepository.findByDivisionCodeAndName(divisionCode,divisionName);
		}

		return division;
	}

	@Override
	public String searchEmployeeByName(String employeeName) {
		// TODO Auto-generated method stub
//		String grade = employeeRepository.findByEmployeeName(employeeName).getGradeCode();
		return null;
	}

	@Override
	public List<Question> searchQuestionByGrade(String gradeCode) {
		// TODO Auto-generated method stub

		return questionRepository.findByGrade(gradeCode);
	}

	@Override
	public List<String> generateEmployee(String divisionCode, String employeeCode, String departementCode) {
		// TODO Auto-generated method stub
		return employeeRepository.generateEmployee(divisionCode,employeeCode,departementCode);
	}

	@Override
	public List<String> generateGrade(String company, String divisionCode, String departementCode) {
		// TODO Auto-generated method stub
		return gradeRepository.generateGrade(company,divisionCode,departementCode);
	}

	@Override
	public List<String> generateCompetency(String divisionCode, String departementCode, String gradeCode) {
		// TODO Auto-generated method stub
		return competencyRepository.generateCompetency(divisionCode,departementCode,gradeCode);
	}

	@Override
	public List<String> generateDivision(String company) {
		// TODO Auto-generated method stub
		return divisionRepository.generateDivision(company);
	}

	@Override
	public List<String> generateDepartement(String divisionCode, String company) {
		// TODO Auto-generated method stub
		return departementRepository.generateDepartement(divisionCode,company);
	}

	@Override
	public List<String> generateQuestion(String departementCode, String gradeCode) {
		// TODO Auto-generated method stub
		return questionRepository.generateQuestion(departementCode,gradeCode);
	}

	@Override
	public List<String> generateSubGrade(String divisionCode, String departementCode, String gradeCode) {
		// TODO Auto-generated method stub
		return subGradeRepository.generateSubGrade(divisionCode,departementCode,gradeCode);
	}

	@Override
	public int insertPendingQuestion(PendingQuestion pendingQuestion) {
		// TODO Auto-generated method stub
		PendingQuestion pendQuest = new PendingQuestion();
		pendQuest = pendingQuestionRepository.save(pendingQuestion);
		return pendQuest.getId()>0?1:0;
	}

	@Override
	public List<String> searchEmployeeByGradeAndSubGrade(String grade, String subGrade) {
		// TODO Auto-generated method stub
		return employeeRepository.searchEmployeeByGradeAndSubGrade(grade,subGrade);
	}

	//find data parent: start
	@Override
	public List<String> findSubGradeByGradeCode(String gradeCode) {
		// TODO Auto-generated method stub
		return subGradeRepository.findSubGradeByGradeCode(gradeCode);
	}

	@Override
	public List<String> findGradeByDeptCode(String gradeCode) {
		// TODO Auto-generated method stub
//		return gradeRepository.findGradeByDeptCode(gradeCode);
		return null;
	}

	@Override
	public List<String> findDepartementByDivCode(String gradeCode) {
		// TODO Auto-generated method stub
//		return departementRepository.findDepartementByDivCode(gradeCode);
		return null;
	}
	//find data parent: stop


}
