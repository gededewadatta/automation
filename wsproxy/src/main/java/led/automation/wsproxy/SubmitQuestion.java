package led.automation.wsproxy;



/**
 * @author gederanadewadatta
 *
 */

public class SubmitQuestion {
	private String id;
	private String userName;
	private String idQuestion;
	private String attemptAnswer;
	private String answerStatus;
	private String answer;
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	public String getIdQuestion() {
		return idQuestion;
	}
	/**
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}
	/**
	 * @return the attemptAnswer
	 */
	public String getAttemptAnswer() {
		return attemptAnswer;
	}
	/**
	 * @param attemptAnswer the attemptAnswer to set
	 */
	public void setAttemptAnswer(String attemptAnswer) {
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmitQuestion [id=" + id + ", userName=" + userName + ", idQuestion=" + idQuestion + ", attemptAnswer="
				+ attemptAnswer + ", answerStatus=" + answerStatus + ", answer=" + answer + "]";
	}  
	
	
	
	
}
