/**
 * 
 */
package led.automation.employee.dao.impl;

 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

 
import led.automation.employee.dao.EmployeeDAO;
import led.automation.employee.repository.*;

/**
 * @author gederanadewadatta
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	HistoryAnswerRepository historyAnswerRepository;
	@Autowired
	PendingQuestionRepository pendingQuestionRepository;
	@Autowired
	SubmitQuestionRepository submitQuestionRepository;
	 
	  

}
