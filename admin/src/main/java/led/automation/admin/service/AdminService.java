/**
 * 
 */
package led.automation.admin.service;

import java.util.List;

import led.automation.admin.model.*;
import org.springframework.stereotype.Service;

/**
 * @author gederanadewadatta
 *
 */
@Service
public interface AdminService {
	//insert data : start
	public String insertEmployee(String body);

	public String insertGrade(String body);

	public String insertSubGrade(String body);

	public String insertDivision(String body);

	public String insertDepartement(String body);

	public String insertQuestion(String body);
	
	public String insertCompetency(String body);
	//insert data : stop
	//search data : start
	public List<Dashboard> findDashboard();

	public Question searchQuestion(String body);

	public SubGrade searchSubGrade(String body);

	public Grade searchGrade(String body);

	public Departement searchDepartement(String body);

	public Division searchDivision(String body);

	public Employee searchEmployee(String body);

    public Employee searchEmployeeByCode(String body);
	
	public Competency searchCompetency(String body);
	//search data : stop
	
	//upload data : start
	public int uploadQuestion(String body);

	public int uploadEmployee(String body);
	//upload data : stop

	//generate data : start
	public List<String> generateEmployee(String body);

	public List<String> generateGrade(String body);

	public List<String> generateSubGrade(String body);

	public List<String> generateQuestion(String body);

	public List<String> generateDepartement(String body);

	public List<String> generateDivision(String body);

	public List<String> generateCompetency(String body);
	
	//generate data : start
	
	
	
}
