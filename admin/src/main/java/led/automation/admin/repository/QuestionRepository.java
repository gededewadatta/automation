/**
 * 
 */
package led.automation.admin.repository;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 

import led.automation.admin.model.Question;

/**
 * @author gederanadewadatta
 *
 */
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query(value = "SELECT * FROM Questions WHERE GRADE = ?1", nativeQuery = true)
	Question findByGrade(String gradeCode);
	@Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,com.competency_name,quest.QUESTIONS"
			+ " FROM Division div join Departement dept on div.division_name = dept.division_name"
			+ " join Grade g on dept.departement_code = g.departement_code"
			+ " join Competency com on com.departement_code = dept.departement_code"
			+ " join Question q on q.competency = com.competency_code"
			+ " WHERE dept.departement_code like %?1 and g.grade_code like %?2", nativeQuery = true)
	List<String> generateQuestion(String departementCode, String gradeCode);

 
	 
}
