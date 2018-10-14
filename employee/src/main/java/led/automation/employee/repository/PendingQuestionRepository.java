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
	@Query(value = "SELECT * FROM PENDINGQUESTION WHERE  not exists (select null from submitquestion sq where sq.id_question=pq.id) and level = (select min(level) from pendingquestion) and USER_NAME = '?1'", nativeQuery = true)
	List<PendingQuestion> searchQuestionByUserName(String userName);
	@Query(value = "SELECT distinct(competency) FROM PENDINGQUESTION", nativeQuery = true)
	List<String> searchCompetency();
	@Query(value = "select * from pendingquestion pq where not exists (select null from submitquestion sq where sq.id_question=pq.id) and level = (select min(level) from pendingquestion)")
	List<PendingQuestion>generateQuestionNotAnswer();
	
}
