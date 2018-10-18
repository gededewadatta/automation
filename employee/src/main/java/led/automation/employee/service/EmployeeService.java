/**
 * 
 */
package led.automation.employee.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import led.automation.employee.model.PendingQuestion;

 

/**
 * @author gederanadewadatta
 *
 */
@Transactional(rollbackFor=Exception.class)
@Service
@Component
public interface EmployeeService {

	public List<PendingQuestion> searchQuestion(String body);
	public String submitHistory(String body);
	public String submitQuestion(String body);
	public List<String> searchCompetency();
	public List<PendingQuestion> generateQuestion();
}
