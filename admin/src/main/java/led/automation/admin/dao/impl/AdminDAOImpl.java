/**
 * 
 */
package led.automation.admin.dao.impl;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import led.automation.admin.dao.AdminDAO;
import led.automation.admin.model.*;
import led.automation.admin.repository.*;

/**
 * @author gederanadewadatta
 *
 */
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	DataSource dataSource;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	SubGradeRepository subGradeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartementRepository departementRepository;

	@Autowired
	DivisionRepository divisionRepository;
	
	@Autowired
	CompetencyRepository competencyRepository;
	@Override
	public int insertEmployee(Employee body) {
		// TODO Auto-generated method stub
		Employee res = new Employee();
		res = employeeRepository.save(body);
		return res.getId()==0?0:1;
	}

	@Override
	public int insertGrade(Grade body) {
		// TODO Auto-generated method stub
		Grade grade = new Grade();
		grade = gradeRepository.save(body);
		return grade.getId()==0?0:1;
	}

	@Override
	public int insertSubGrade(SubGrade body) {
		// TODO Auto-generated method stub
		SubGrade subGrade = new SubGrade();
		subGrade = subGradeRepository.save(body);
		
		return subGrade.getId()==0?0:1;
	}

	@Override
	public int insertQuestion(Question body) {
		// TODO Auto-generated method stub
		Question question = new Question();
		question = questionRepository.save(body);
		return question.getId()>0?1:0;
	}

	@Override
	public int insertDivision(Division division) {
		// TODO Auto-generated method stub
		Division div = new Division();
		div = divisionRepository.save(division);
		return div.getId()>0?1:0;
	}

	@Override
	public int insertDepartement(Departement departement) {
		// TODO Auto-generated method stub
		Departement dep = new Departement();
		dep = departementRepository.save(departement);
		return dep.getId()>0?1:0;
	}

	@Override
	public int insertCompetency(Competency competency) {
		// TODO Auto-generated method stub
		Competency com = new Competency();
		com = competencyRepository.save(competency);
		return com.getId()>0?1:0;
	}

	@Override
	public Question searchQuestion(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubGrade searchSubGrade(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grade searchGrade(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(String body) {
		// TODO Auto-generated method stub
		return null;
	}

}
