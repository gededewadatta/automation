/**
 * 
 */
package led.automation.employee.dao;
 
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import led.automation.employee.model.HistoryAnswer;
import led.automation.employee.model.PendingQuestion;
import led.automation.employee.model.SubmitQuestion;

 

/**
 * @author gederanadewadatta
 *
 */
@Transactional(rollbackFor=Exception.class)
public interface EmployeeDAO {
	 
	public List<PendingQuestion> searchQuestionByUserName(String userName,String competency);
	public int submitQuestion(SubmitQuestion submitQuestion);
	public int submitHistory(HistoryAnswer historyAnswer);
	public List<String> searchCompetency();
	
}
