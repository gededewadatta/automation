/**
 * 
 */
package led.automation.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author gederanadewadatta
 *
 */
@Entity
@Table(name = "PENDING_QUESTION")
@EntityListeners(AuditingEntityListener.class)

public class PendingQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMITQUESTION_SEQ")
	@SequenceGenerator(sequenceName = "submitquestion_seq", allocationSize = 1, name = "SUBMITQUESTION_SEQ")
	private Long id;
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	@Column(name="ID_QUESTION")
	private Long idQuestion; 
}
