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
@Table(name = "SUBMITQUESTION")
@EntityListeners(AuditingEntityListener.class)

public class SubmitQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMITQUESTION_SEQ")
	@SequenceGenerator(sequenceName = "submitquestion_seq", allocationSize = 1, name = "SUBMITQUESTION_SEQ")
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name="ID_QUESTION")
	private Long idQuestion;
	@Column(name="ATTEMPT_ANSWER")
	private Long attemptAnswer;
	@Column(name="ANSWER_STATUS")
	private String answerStatus;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private String createdDate;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the idQuestion
	 */
	public Long getIdQuestion() {
		return idQuestion;
	}
	/**
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}
	/**
	 * @return the attemptAnswer
	 */
	public Long getAttemptAnswer() {
		return attemptAnswer;
	}
	/**
	 * @param attemptAnswer the attemptAnswer to set
	 */
	public void setAttemptAnswer(Long attemptAnswer) {
		this.attemptAnswer = attemptAnswer;
	}
	/**
	 * @return the answerStatus
	 */
	public String getAnswerStatus() {
		return answerStatus;
	}
	/**
	 * @param answerStatus the answerStatus to set
	 */
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmitQuestion [id=" + id + ", userName=" + userName + ", idQuestion=" + idQuestion + ", attemptAnswer="
				+ attemptAnswer + ", answerStatus=" + answerStatus + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + "]";
	}
	 
	 
	
	
	
	
}
