/**
 * 
 */
package led.automation.admin.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import led.automation.admin.model.Employee;
import led.automation.admin.model.Grade;
import led.automation.admin.model.Question;
import led.automation.admin.model.SubGrade;
import led.automation.admin.model.Competency;
import led.automation.admin.model.Departement;
import led.automation.admin.model.Division;

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
	//insert data : stop
	 

	public Departement searchDepartement(String divisionCode, String divisionName);

	public Employee searchEmployee(String employeeCode, String employeeName);

	public Grade searchGrade(String departementCode, String departementName);

	public SubGrade searchSubGrade(String gradeCode, String gradeName);

	public Competency searchCompetency(String gradeCode, String gradeName);

	public Division searchDivision(String divisionCode, String divisionName);

	public String searchEmployeeByName(String employeeName);

	public Question searchQuestionByGrade(String gradeCode);

	public List<String> generateEmployee(String divisionCode, String employeeCode, String departementCode);

	public List<String> generateGrade(String company, String divisionCode, String departementCode);

	public List<String> generateCompetency(String divisionCode, String departementCode, String gradeCode);

	public List<String> generateDivision(String company);

	public List<String> generateDepartement(String divisionCode, String company);

	public List<String> generateQuestion(String departementCode, String gradeCode);

	public List<String> generateSubGrade(String divisionCode, String departementCode, String gradeCode);
 

	 

}
