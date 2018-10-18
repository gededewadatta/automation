package led.automation.employee.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

 
import led.automation.employee.dao.EmployeeDAO;
import led.automation.employee.model.HistoryAnswer;
import led.automation.employee.model.PendingQuestion;
import led.automation.employee.model.SubmitQuestion;
import led.automation.employee.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {
	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private PendingQuestion pendingQuestion;
	@Autowired
	private HistoryAnswer historyAnswer;
	@Autowired
	private SubmitQuestion submitQuestion;
	
	@Override
	public List<PendingQuestion> searchQuestion(String body) {
		// TODO Auto-generated method stub
		try {
			pendingQuestion = objectMapper.readValue(body, PendingQuestion.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeDAO.searchQuestionByUserName(pendingQuestion.getUserName());
	}

	@Override
	public String submitHistory(String body) {
		// TODO Auto-generated method stub
		try {
			historyAnswer = objectMapper.readValue(body, HistoryAnswer.class);
			System.out.println("User Name is :" + historyAnswer.getUserName());
			System.out.println("ID Question is :" + historyAnswer.getIdQuestion());
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeDAO.submitHistory(historyAnswer) == 0 ? "Failure" : "Success";
	}

	@Override
	public String submitQuestion(String body) {
		// TODO Auto-generated method stub
		try {
			submitQuestion = objectMapper.readValue(body, SubmitQuestion.class);
			System.out.println("User Name is :" + submitQuestion.getUserName());
			System.out.println("ID Question is :" + submitQuestion.getIdQuestion());
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeDAO.submitQuestion(submitQuestion) == 0 ? "Failure" : "Success";
	
	}

	@Override
	public List<String> searchCompetency() {
		// TODO Auto-generated method stub
		return employeeDAO.searchCompetency();
	}

	@Override
	public List<PendingQuestion> generateQuestion() {
		// TODO Auto-generated method stub
		return employeeDAO.generateQuestion();
	}

}
