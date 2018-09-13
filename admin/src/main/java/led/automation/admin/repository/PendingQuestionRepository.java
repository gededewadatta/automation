/**
 * 
 */
package led.automation.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import led.automation.admin.model.PendingQuestion;

/**
 * @author gederanadewadatta
 *
 */
public interface PendingQuestionRepository extends JpaRepository<PendingQuestion, Long>{

}
