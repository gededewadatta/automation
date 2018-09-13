/**
 * 
 */
package led.automation.employee.dao.impl;

 

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

 
import led.automation.employee.dao.EmployeeDAO;
import led.automation.employee.model.HistoryAnswer;
import led.automation.employee.model.PendingQuestion;
import led.automation.employee.model.SubmitQuestion;
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
	@Override
	public List<PendingQuestion> searchQuestionByUserName(String userName) {
		// TODO Auto-generated method stub
		return pendingQuestionRepository.searchQuestionByUserName(userName);
	}
	@Override
	public int submitQuestion(SubmitQuestion submitQuestion) {
		// TODO Auto-generated method stub
		SubmitQuestion res = new SubmitQuestion();
		res = submitQuestionRepository.save(submitQuestion);
		return res.getId()==0?0:1;
	}
	@Override
	public int submitHistory(HistoryAnswer historyAnswer) {
		// TODO Auto-generated method stub
		HistoryAnswer res = new HistoryAnswer();
		res = historyAnswerRepository.save(historyAnswer);
		return res.getId()==0?0:1;
	}
	 
	  

}
