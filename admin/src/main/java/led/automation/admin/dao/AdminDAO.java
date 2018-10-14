/**
 * 
 */
package led.automation.admin.dao;

import java.util.List;
import java.util.Set;

import led.automation.admin.model.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gederanadewadatta
 *
 */
@Transactional(rollbackFor=Exception.class)
public interface AdminDAO {
	//insert data: start
	public int insertEmployee(Employee body);

	public int insertGrade(Grade body);

	public int insertSubGrade(SubGrade body);

	public int insertQuestion(Question body);
	
	public int insertDivision(Division division);

	public int insertDepartement(Departement departement);

	public int insertCompetency(Competency competency);
	
	public int insertPendingQuestion(PendingQuestion pendingQuestion);
	//insert data : stop

	//update data

	public int updateDivision(Division division);
	public int updateEmployee(Employee body);
	public int updateCompetency(Competency competency);

	//update data stop
	 

	public List<Departement> searchDepartement(String divisionCode, String departementCode);

	public List<Departement> searchDepartementPopup(String divisionCode, String departementCode, String departementName);

	public List<Employee> searchEmployee(String employeeCode, String employeeName);

	public List<Grade> searchGrade(String departementCode, String departementName);

	public List<Grade> searchGradePopup(String departementCode, String divisionCode, String gradeCode, String gradeName);

	public List<SubGrade> searchSubGrade(String gradeCode, String gradeName);

	public List<SubGrade> searchSubGradePopup(String departementCode, String gradeCode, String subGradeCode, String subGradeName);

	public List<GradeJson> searchGradeJson(String departementCode, String departementName);

	public List<Competency> searchCompetency(String competencyCode, String competencyName);

	public List<Competency> searchCompetencyByGradeCode(String gradeCode);

	public List<Division> searchDivision(String divisionCode, String divisionName);

	public List<Report> searchReportEmployee(String employeeCode, String grade);

	public String searchEmployeeByName(String employeeName);

	public List<Question> searchQuestionByGrade(String gradeCode);

	public List<String> generateEmployee(String divisionCode, String employeeCode, String departementCode);

	public List<String> generateGrade(String company, String divisionCode, String departementCode);

	public List<String> generateCompetency(String divisionCode, String departementCode, String gradeCode);

	public List<String> generateDivision(String company);

	public List<String> generateDepartement(String divisionCode, String company);

	public List<String> generateQuestion(String departementCode, String gradeCode);

	public List<String> generateSubGrade(String divisionCode, String departementCode, String gradeCode);

	public List<Dashboard> findDashboard();

	public List<String> searchEmployeeByGradeAndSubGrade(String grade, String subGrade);

	//find data parent: start
	public List<String> findSubGradeByGradeCode(String gradeCode);
	public List<String> findGradeByDeptCode(String gradeCode);
	public List<String> findDepartementByDivCode(String gradeCode);

	//find data parent: stop
}
