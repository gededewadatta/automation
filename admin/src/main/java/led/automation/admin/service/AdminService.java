/**
 * 
 */
package led.automation.admin.service;

import java.util.List;
import java.util.Set;

import led.automation.admin.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gederanadewadatta
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service
public interface AdminService {
	// insert data : start
	public String insertEmployee(String body);

	public String insertGrade(String body);

	public String insertSubGrade(String body);

	public String insertDivision(String body);

	public String insertDepartement(String body);

	public String insertQuestion(String body);

	public String insertCompetency(String body);

	// insert data : stop

	// update data

	public String updateDivision(String body);

	public String updateEmployee(String body);

	public String updateCompetency(String body);

	// update data stop
	// search data : start
	public List<Dashboard> findDashboard();

	public List<Question> searchQuestion(String body);

	public List<SubGrade> searchSubGrade(String body);

	public List<GradeJson> searchGradeJson(String body);

	public List<Grade> searchGrade(String body);

	public List<Grade> searchGradePopup(String body);

	public List<SubGrade> searchSubGradePopup(String body);

	public List<Departement> searchDepartement(String body);

	public List<Departement> searchDepartementPopup(String body);

	public List<Division> searchDivision(String body);

	public List<Employee> searchEmployee(String body);

	public Employee searchEmployeeByCode(String body);

    public List<Competency> searchCompetency(String body);

    public List<Competency> searchCompetencyByGradeCode(String gradeCode);

	public List<Report> searchReportEmployee(String body);

	// search data : stop

	// upload data : start
	public int uploadQuestion(String body);

	public int uploadEmployee(String body);
	// upload data : stop

	// generate data : start
	public List<String> generateEmployee(String body);

	public List<String> generateGrade(String body);

	public List<String> generateSubGrade(String body);

	public List<String> generateQuestion(String body);

	public List<String> generateDepartement(String body);

	public List<String> generateDivision(String body);

	public List<String> generateCompetency(String body);

	// generate data : start

	// find data : start
	public List<String> findSubGradeByGradeCode(String body);

//	public List<String> findGradeByDeptCode(String body);
//
//	public List<String> findDepartementByDivCode(String body);

	// find data : stop

}
