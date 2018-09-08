/**
 * 
 */
package led.automation.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import led.automation.employee.model.HistoryAnswer;
import led.automation.employee.model.PendingQuestion;

/**
 * @author gederanadewadatta
 *
 */
public interface PendingQuestionRepository extends JpaRepository<PendingQuestion, Long>{

}
