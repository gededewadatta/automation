/**
 * 
 */
package led.automation.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import led.automation.employee.model.HistoryAnswer;
import led.automation.employee.model.SubmitQuestion;

/**
 * @author gederanadewadatta
 *
 */
public interface SubmitQuestionRepository extends JpaRepository<SubmitQuestion, Long>{

}
