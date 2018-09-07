/**
 * 
 */
package led.automation.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author gederanadewadatta
 *
 */
@Entity
public class HistoryAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMITQUESTION_SEQ")
	@SequenceGenerator(sequenceName = "submitquestion_seq", allocationSize = 1, name = "SUBMITQUESTION_SEQ")
	private Long id;
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	@Column(name="ID_QUESTION")
	private Long idQuestion;
	@Column(name="ANSWER_QUESTION")
	private Long answerQuestion;
	@Column(name="CORRECT_ANSWER")
	private Long correctQuestion;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private String createdDate;
}
