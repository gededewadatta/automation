/**
 * 
 */
package led.automation.admin.dao;

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
	public int insertEmployee(Employee body);

	public int insertGrade(Grade body);

	public int insertSubGrade(SubGrade body);

	public int insertQuestion(Question body);
	
	public int insertDivision(Division division);

	public int insertDepartement(Departement departement);

	public int insertCompetency(Competency competency);

	public Question searchQuestion(String body);

	public SubGrade searchSubGrade(String body);

	public Grade searchGrade(String body);

	public Employee searchEmployee(String body);

}
