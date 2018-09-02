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

@Entity
@Table(name = "SUBMIT_QUESTION")
@EntityListeners(AuditingEntityListener.class)

public class SubmitQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMITQUESTION_SEQ")
	@SequenceGenerator(sequenceName = "submitquestion_seq", allocationSize = 1, name = "SUBMITQUESTION_SEQ")
	private Long id;
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	@Column(name="ID_QUESTION")
	private Long idQuestion;
	@Column(name="LEVEL_QUESTION")
	private Long levelQuestion;
	@Column(name="ATTEMPT_ANSWER")
	private Long attemptAnswer;
	@Column(name="ANSWER_STATUS")
	private String answerStatus;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private String createdDate;
	@Override
	public String toString() {
		return "SubmitQuestion [id=" + id + ", employeeCode=" + employeeCode + ", idQuestion=" + idQuestion
				+ ", attemptAnswer=" + attemptAnswer + ", answerStatus=" + answerStatus + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public Long getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Long getAttemptAnswer() {
		return attemptAnswer;
	}
	public void setAttemptAnswer(Long attemptAnswer) {
		this.attemptAnswer = attemptAnswer;
	}
	public String getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
}
