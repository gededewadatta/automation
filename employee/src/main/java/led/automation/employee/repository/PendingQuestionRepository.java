/**
 * 
 */
package led.automation.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

  
import led.automation.employee.model.PendingQuestion;

/**
 * @author gederanadewadatta
 *
 */
public interface PendingQuestionRepository extends JpaRepository<PendingQuestion, Long>{
	@Query(value = "SELECT * FROM PENDINGQUESTION WHERE USER_NAME = '?1' and competency ='?2' order by level", nativeQuery = true)
	List<PendingQuestion> searchQuestionByUserName(String userName,String competency);
	@Query(value = "SELECT distinct(competency) FROM PENDINGQUESTION", nativeQuery = true)
	List<String> searchCompetency();
	
}
