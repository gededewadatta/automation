/**
 *
 */
package led.automation.admin.dao.impl;

import java.util.*;

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
	GradeJsonRepoSitory gradeJsonRepoSitory;

	@Autowired
	CompetencyRepository competencyRepository;
	@Autowired
	PendingQuestionRepository pendingQuestionRepository;

	@Autowired
	ReportRepository reportRepository;

	Departement dep = new Departement();

	Grade grd = new Grade();

	SubGrade subgrd = new SubGrade();

	Division div = new Division();

	Employee res = new Employee();
	//insert data :start
	@Override
	public int insertEmployee(Employee body) {
		// TODO Auto-generated method stub
		if(body.getUserName()==null||body.getUserName().equals("")){
			body.setUserName("");
		}

		int result = employeeRepository.insertEmployee(body.getCreatedBy(),new Date(),body.getDepartementCode(),body.getDivisionCode()
				,body.getEmployeeCode().toUpperCase(),body.getEmployeeName(),body.getGradeCode(),body.getSubGradeCode(),body.getUserName());
		if(result > 0){
			employeeRepository.updateEmployeeSeq();
		}

		return result==0?0:1;
	}

	@Override
	public int updateEmployee(Employee body) {
		// TODO Auto-generated method stub
		res = employeeRepository.save(body);
		return res.getId()==0?0:1;
	}

	@Override
	public int updateQuestion(Question body) {
		// TODO Auto-generated method stub
		int quest = questionRepository.updateQuestion(body.getAnswer1(),body.getAnswer2(),body.getAnswer3()
				,body.getAnswer4(),body.getAnswer5(),body.getCompetency(),body.getCorrectAnswer(),body.getCreatedBy()
				,body.getCreatedDate(),body.getDepartementCode(),body.getGrade(),body.getLevel(),body.getQuestionType()
				,body.getQuestionCode(),body.getQuestions(),body.getSubGrade());

		return quest==0?0:1;
	}

	@Override
	public int updatePendingQuestion(PendingQuestion pendingQuestion) {
		// TODO Auto-generated method stub
			int pendQuest = questionRepository.updatePendingQuestion(pendingQuestion.getAnswer1()
					,pendingQuestion.getAnswer2(),pendingQuestion.getAnswer3()
					,pendingQuestion.getAnswer4(),pendingQuestion.getAnswer5()
					,pendingQuestion.getCompetency(),pendingQuestion.getCorrectAnswer(),pendingQuestion.getCreatedBy()
					,pendingQuestion.getCreatedDate(),pendingQuestion.getLevel(),pendingQuestion.getQuestionType()
					,pendingQuestion.getQuestions(),pendingQuestion.getUserName());

		return pendQuest==0?0:1;
	}

	@Override
	public int insertGrade(Grade grade) {
		// TODO Auto-generated method stub
		int a = gradeRepository.insertGrade(grade.getGradeCode().toUpperCase(),grade.getGradeName(),grade.getDepartementCode().toUpperCase(),
				grade.getDivisionCode().toUpperCase(),grade.getCreatedBy(),grade.getCreatedDate());

		if(a > 0){
			gradeRepository.updateGradeSeq();
		}

		return a==0?0:1;
	}

	@Override
	public int insertSubGrade(SubGrade subGrade) {
		// TODO Auto-generated method stub
		int a = subGradeRepository.insertSubGrade(subGrade.getSubGradeCode().toUpperCase(),subGrade.getSubGradeName(),subGrade.getGradeCode().toUpperCase(),subGrade.getDepartementCode(),subGrade.getCreatedBy(),subGrade.getCreatedDate());

		if(a > 0){
			subGradeRepository.updateSubGradeSeq();
		}

		return a==0?0:1;
	}

	@Override
	public int insertQuestion(Question body) {
		// TODO Auto-generated method stub
		int question = questionRepository.insertQuestion(body.getAnswer1(),body.getAnswer2(),body.getAnswer3(),
				body.getAnswer4(),body.getAnswer5(),body.getCompetency(),body.getCorrectAnswer(),body.getCreatedBy()
				,body.getCreatedDate(),body.getDepartementCode(),body.getGrade(),body.getLevel()
				,body.getQuestionType(),body.getQuestionCode(),body.getQuestions(),body.getSubGrade());

		if(question>0){
			questionRepository.updateQuestionSeq();
		}

		return question >0?1:0;
	}

	@Override
	public int insertDivision(Division division) {
		// TODO Auto-generated method stub
		int a = divisionRepository.insertDivision(division.getCreatedBy(),division.getCreatedDate(),division.getDivisionCode().toUpperCase(),division.getDivisionName());

		if(a > 0){
			divisionRepository.updateDivisionSeq();
		}

		//		return div.getId()>0?1:0;
        return a==0?0:1;
    }

	@Override
	public int updateDivision(Division division) {
		// TODO Auto-generated method stub
		div = divisionRepository.save(division);

		return div.getId()==0?0:1;
	}

	@Override
	public int updateDepartement(Departement departement) {
		// TODO Auto-generated method stub
		dep = departementRepository.save(departement);

		return dep.getId()==0?0:1;
	}

	@Override
	public int updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		grd = gradeRepository.save(grade);

		return grd.getIdGrade()==0?0:1;
	}

	@Override
	public int updateSubGrade(SubGrade subGrade) {
		// TODO Auto-generated method stub
		subgrd = subGradeRepository.save(subGrade);

		return subgrd.getIdSubGrade()==0?0:1;
	}

	@Override
	public int insertDepartement(Departement departement) {
		// TODO Auto-generated method stub
		int a = departementRepository.insertDepartement(departement.getDepartementCode().toUpperCase(),departement.getDepartementName(),departement.getDivisionCode().toUpperCase(),departement.getCreatedBy(),departement.getCreatedDate());

		if(a > 0){
			departementRepository.updateDepartementSeq();
		}
//		return dep.getId()>0?1:0;
		return a==0?0:1;
	}

	@Override
	public int insertCompetency(Competency competency) {
		// TODO Auto-generated method stub
		competency.setCreatedDate(new Date());
		int com = competencyRepository.insertCompetency(competency.getCreatedBy(),competency.getCreatedDate()
				,competency.getDepartementCode(),competency.getGradeCode(),competency.getSubGradeCode(),competency.getCompetencyCode(),competency.getCompetencyName());

		if(com > 0){
			competencyRepository.updateCompetencySeq();
		}
		return com>0?1:0;
	}

	@Override
	public int updateCompetency(Competency competency) {
		// TODO Auto-generated method stub
		Competency compe =  new Competency();
		competency.setCreatedDate(new Date());
		compe = competencyRepository.save(competency);
		return compe.getId()>0?1:0;
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
	public List<Departement> searchDepartement(String divisionCode, String departementCode) {
		// TODO Auto-generated method stub
		List<Departement> departement = new ArrayList<>();
		if(departementCode.equals("") && divisionCode.equals("")) {
			departement = departementRepository.findAll();
		}
		else if(departementCode.equals("")&& !divisionCode.equals("")) {
			departement = departementRepository.findByDivisionCode("%"+divisionCode.toUpperCase()+"%");
		}
		else if(!departementCode.equals("")&& divisionCode.equals("")) {
			departement = departementRepository.findByDepartementCode("%"+departementCode.toUpperCase()+"%");
		}else {
			departement = departementRepository.findByDepartementCodeAndDivisionCode("%"+departementCode.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%");
		}

		return departement;
	}

	@Override
	public List<Departement> searchDepartementPopup(String divisionCode, String departementCode, String departementName) {
		// TODO Auto-generated method stub
		List<Departement> departement = new ArrayList<>();
		if(departementCode.equals("") && divisionCode.equals("") && departementName.equals("")) {
			departement = departementRepository.findAll();
		}
		else if(departementCode.equals("")&& !divisionCode.equals("")&& departementName.equals("")) {
			departement = departementRepository.findByDivisionCode("%"+divisionCode.toUpperCase()+"%");
		}
		else if(!departementCode.equals("")&& divisionCode.equals("")&& departementName.equals("")) {
			departement = departementRepository.findByDepartementCode("%"+departementCode.toUpperCase()+"%");
		}
		else if(departementCode.equals("")&& divisionCode.equals("")&& !departementName.equals("")) {
			departement = departementRepository.findByDepartementName("%"+departementName.toUpperCase()+"%");
		}
		else if(!departementCode.equals("")&& !divisionCode.equals("")&& departementName.equals("")) {
			departement = departementRepository.findByDepartementCodeAndDivisionCode("%"+departementCode.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%");
		}
		else if(departementCode.equals("")&& !divisionCode.equals("")&& !departementName.equals("")) {
			departement = departementRepository.findByDepartementNameAndDivisionCode("%"+departementName.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%");
		}else {
			departement = departementRepository.findByDepartementCodeAndDivisionCodeAndDepartementName("%"+departementCode.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%","%"+departementName.toUpperCase()+"%");
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
			grade = gradeRepository.findByDepartmentCode("%"+departmentCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& departmentCode.equals("")) {
			grade = gradeRepository.findByDivisionCode("%"+divisionCode.toUpperCase()+"%");
		}else {
			grade = gradeRepository.findByDepartmentCodeAndDivisionCode("%"+divisionCode.toUpperCase()+"%","%"+departmentCode.toUpperCase()+"%");
		}

		return grade;
	}

	@Override
	public List<Grade> searchGradePopup(String departementCode, String divisionCode, String gradeCode, String gradeName) {
		// TODO Auto-generated method stub
		List<Grade> grade = new ArrayList<>();
		if(divisionCode.equals("")&&departementCode.equals("")
				&&gradeCode.equals("")&&gradeName.equals("")) {
			grade =  gradeRepository.findAll();
		}
		else if(divisionCode.equals("")&&!departementCode.equals("")
				&&gradeCode.equals("")&&gradeName.equals("")){
			grade = gradeRepository.findByDepartmentCode("%"+departementCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& departementCode.equals("")
				&&gradeCode.equals("")&&gradeName.equals("")) {
			grade = gradeRepository.findByDivisionCode("%"+divisionCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& !departementCode.equals("")
				&&gradeCode.equals("")&&gradeName.equals("")) {
			grade = gradeRepository.findByDepartmentCodeAndDivisionCode("%"+departementCode.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%");
		}
		else if(divisionCode.equals("")&& departementCode.equals("")
				&&!gradeCode.equals("")&&gradeName.equals("")) {
			grade = gradeRepository.findByGradeCode("%"+gradeCode.toUpperCase()+"%");
		}
		else if(divisionCode.equals("")&& departementCode.equals("")
				&&gradeCode.equals("")&&!gradeName.equals("")) {
			grade = gradeRepository.findByGradeName("%"+gradeName.toUpperCase()+"%");
		}
		else if(divisionCode.equals("")&& departementCode.equals("")
				&&!gradeCode.equals("")&&!gradeName.equals("")) {
			grade = gradeRepository.findByGradeCodeAndGradeName("%"+gradeCode.toUpperCase()+"%","%"+gradeName.toUpperCase()+"%");
		}
		else if(divisionCode.equals("")&& !departementCode.equals("")
				&&!gradeCode.equals("")&&!gradeName.equals("")) {
			grade = gradeRepository.findByGradeCodeAndGradeNameAndDepartementCode("%"+gradeCode.toUpperCase()+"%","%"+gradeName.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& departementCode.equals("")
				&&!gradeCode.equals("")&&!gradeName.equals("")) {
			grade = gradeRepository.findByGradeCodeAndGradeNameAndDivisionCode("%"+gradeCode.toUpperCase()+"%","%"+gradeName.toUpperCase()+"%","%"+divisionCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& !departementCode.equals("")
				&&!gradeCode.equals("")&&gradeName.equals("")) {
			grade = gradeRepository.findByDepartmentCodeAndDivisionCodeAndGradeCode("%"+divisionCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& !departementCode.equals("")
				&&gradeCode.equals("")&&!gradeName.equals("")) {
			grade = gradeRepository.findByDepartmentCodeAndDivisionCodeAndGradeName("%"+divisionCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeName.toUpperCase()+"%");
		}else {
			grade = gradeRepository.findByDepartmentCodeAndDivisionCodeAndGradeCodeAndGradeName("%"+divisionCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+gradeName.toUpperCase()+"%");
		}

		return grade;
	}

	@Override
	public List<SubGrade> searchSubGrade(String subGradeCode, String subGradeName) {
		// TODO Auto-generated method stub
		List<SubGrade> subGrade = new ArrayList<>();

		if(subGradeCode.equals("")&& subGradeName.equals("")) {
			subGrade =  subGradeRepository.findAll();
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
    public List<SubGrade> searchSubGradePopup (String departementCode, String gradeCode, String subGradeCode, String subGradeName) {
        // TODO Auto-generated method stub
        List<SubGrade> subGrade = new ArrayList<>();

        if(subGradeCode.equals("")&& subGradeName.equals("")
                && departementCode.equals("") && gradeCode.equals("")) {
            subGrade =  subGradeRepository.findAll();
        }
        else if(subGradeCode.equals("")&&!subGradeName.equals("")
                && departementCode.equals("") && gradeCode.equals("")){
            subGrade = subGradeRepository.findBySubGradeName("%"+subGradeName.toUpperCase()+"%");
        }
        else if(!subGradeCode.equals("")&& subGradeName.equals("")
                && departementCode.equals("") && gradeCode.equals("")) {
            subGrade = subGradeRepository.findBySubGradeCode("%"+subGradeCode.toUpperCase()+"%");
        }
        else if(subGradeCode.equals("")&&!subGradeName.equals("")
                && departementCode.equals("") && gradeCode.equals("")){
            subGrade = subGradeRepository.findBySubGradeCodeAndName("%"+subGradeCode.toUpperCase()+"%","%"+subGradeName.toUpperCase()+"%");
        }
        else if(subGradeCode.equals("")&& subGradeName.equals("")
                && !departementCode.equals("") && !gradeCode.equals("")) {
            subGrade =  subGradeRepository.findByGradeCode("%"+gradeCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%");
        }
        else if(subGradeCode.equals("")&& !subGradeName.equals("")
                && !departementCode.equals("") && !gradeCode.equals("")) {
            subGrade = subGradeRepository.findBySubGradeNameAndGradeCode("%"+subGradeName.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%");
        }
        else if(!subGradeCode.equals("")&& subGradeName.equals("")
                && !departementCode.equals("") && !gradeCode.equals("")) {
            subGrade = subGradeRepository.findBySubGradeCodeAndGradeCode("%"+subGradeCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%");
        }
        else {
            subGrade = subGradeRepository.findBySubGradeCodeAndSubGradeNameAndGradeCode("%"+subGradeCode.toUpperCase()+"%","%"+subGradeName.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%");
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

			gradeJsons = gradeJsonRepoSitory.findGradeAndSubGrade();

		}else if(!departmentCode.equals("")&&divisionCode.equals("")){
			gradeJsons = gradeJsonRepoSitory.findByDepartmentCode(departmentCode);
		}else if(departmentCode.equals("")&& !divisionCode.equals("")) {
			gradeJsons = gradeJsonRepoSitory.findByDivisionCode(divisionCode);
		}else {
			gradeJsons = gradeJsonRepoSitory.findByDivisionCodeAndDivisionName(departmentCode,divisionCode);
		}

		return gradeJsons;
	}

	@Override
	public List<Competency> searchCompetency(String competencyCode, String competencyName) {
		// TODO Auto-generated method stub
		List<Competency> competency = new ArrayList<>();
		if(competencyCode.equals("")&&competencyName.equals("")) {
			competency = competencyRepository.findAll();
		}
		else if(competencyCode.equals("")&&!competencyName.equals("")){
			competency = competencyRepository.findByCompetencyName("%"+competencyName.toUpperCase()+"%");
		}
		else if(!competencyCode.equals("")&& competencyName.equals("")) {
			competency = competencyRepository.findByCompetencyCode("%"+competencyCode.toUpperCase()+"%");
		}else {
			competency = competencyRepository.findByCompetencyCodeAndName("%"+competencyCode.toUpperCase()+"%","%"+competencyName.toUpperCase()+"%");
		}
		return competency;
	}

	@Override
	public List<Competency> searchCompetencyPopup(String competencyCode, String competencyName, String departementCode, String gradeCode, String subGradeCode) {
		// TODO Auto-generated method stub
		List<Competency> competency = new ArrayList<>();
		if(competencyCode.equals("")&&competencyName.equals("")
				&&departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")) {
			competency = competencyRepository.findAll();
		}
		else if(competencyCode.equals("")&&!competencyName.equals("")
				&&departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")){
			competency = competencyRepository.findByCompetencyName("%"+competencyName.toUpperCase()+"%");
		}
		else if(!competencyCode.equals("")&& competencyName.equals("")
				&&departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")) {
			competency = competencyRepository.findByCompetencyCode("%"+competencyCode.toUpperCase()+"%");
		}
		else if(!competencyCode.equals("")&& !competencyName.equals("")
				&&departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")) {
			competency = competencyRepository.findByCompetencyCodeAndName("%"+competencyCode.toUpperCase()+"%","%"+competencyName.toUpperCase()+"%");
		}
		else if(competencyCode.equals("")&& competencyName.equals("")
				&&!departementCode.equals("")&&!gradeCode.equals("")&&!subGradeCode.equals("")) {
			competency = competencyRepository.findByDepartementCodeGradeCodeSubGradeCode("%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+subGradeCode.toUpperCase()+"%");
		}
		else if(competencyCode.equals("")&& !competencyName.equals("")
				&&!departementCode.equals("")&&!gradeCode.equals("")&&!subGradeCode.equals("")) {
			competency = competencyRepository.findByCompetencyNameDepartementCodeGradeCodeSubGradeCode("%"+competencyName.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+subGradeCode.toUpperCase()+"%");
		}
		else if(!competencyCode.equals("")&& competencyName.equals("")
				&&!departementCode.equals("")&&!gradeCode.equals("")&&!subGradeCode.equals("")) {
			competency = competencyRepository.findByCompetenencyCodeDepartementCodeGradeCodeSubGradeCode("%"+competencyCode.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+subGradeCode.toUpperCase()+"%");
		}else {
			competency = competencyRepository.findByAllParams("%"+competencyCode.toUpperCase()+"%","%"+competencyName.toUpperCase()+"%","%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+subGradeCode.toUpperCase()+"%");
		}
		return competency;
	}

	@Override
	public List<Competency> searchCompetencyByGradeCode(String gradeCode, String subGradeCode, String departementCode) {
		// TODO Auto-generated method stub
		List<Competency> competency = new ArrayList<>();
		if(departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")){
			competency = competencyRepository.findAll();
		}else if(!departementCode.equals("")&&gradeCode.equals("")&&subGradeCode.equals("")){
			competency = competencyRepository.findByDepartementCode("%"+departementCode.toUpperCase()+"%");
		}else if(departementCode.equals("")&&!gradeCode.equals("")&&subGradeCode.equals("")){
			competency = competencyRepository.findByGradeCode("%"+gradeCode.toUpperCase()+"%");
		}else if(departementCode.equals("")&&gradeCode.equals("")&&!subGradeCode.equals("")){
			competency = competencyRepository.findBySubGradeCode("%"+subGradeCode.toUpperCase()+"%");
		}else {
			competency = competencyRepository.findByDepartementCodeGradeCodeSubGradeCode("%"+departementCode.toUpperCase()+"%","%"+gradeCode.toUpperCase()+"%","%"+subGradeCode.toUpperCase()+"%");
		}

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
			division = divisionRepository.findByDivisionName("%"+divisionName.toUpperCase()+"%");
		}
		else if(!divisionCode.equals("")&& divisionName.equals("")) {
			division = divisionRepository.findByDivisionCode("%"+divisionCode.toUpperCase()+"%");
		}else {
			division = divisionRepository.findByDivisionCodeAndName("%"+divisionCode.toUpperCase()+"%","%"+divisionName.toUpperCase()+"%");
		}

		return division;
	}

	@Override
	public List<Report> searchReportEmployee(String employeeCode, String grade) {
		List<Report> reports = new ArrayList<>();
		if(employeeCode.equals("")&&grade.equals("")){
			reports = reportRepository.findAll();
		}
		else if(employeeCode.equals("")&&!grade.equals("")){
			reports = reportRepository.findByGrade("%"+grade.toUpperCase()+"%");
		}
		else if(!employeeCode.equals("")&&grade.equals("")){
			reports = reportRepository.findByEmployee("%"+employeeCode.toUpperCase()+"%");
		}else {
			reports = reportRepository.findByEmployeeAndGrade("%"+employeeCode.toUpperCase()+"%","%"+grade.toUpperCase()+"%");
		}
		return reports;
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
	public List<Question> searchQuestionByCompetencies(String competencies) {
		// TODO Auto-generated method stub

		return questionRepository.findByCompetency(competencies);
	}

	@Override
	public List<Question> searchQuestionByGradeAndCompetencies(String gradeCode, String competencies) {
		// TODO Auto-generated method stub

		return questionRepository.findByGradeAndCompetency(gradeCode,competencies);
	}

	@Override
	public List<Question> searchQuestionAll() {
		// TODO Auto-generated method stub

		return questionRepository.findAll();
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
