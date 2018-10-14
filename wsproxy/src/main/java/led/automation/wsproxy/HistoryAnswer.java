/**
 * 
 */
package led.automation.wsproxy;
 
/**
 * @author gederanadewadatta
 *
 */



public class HistoryAnswer {
	private String id;
	private String userName;
	private String idQuestion;
	private String answerQuestion; 
	private String createdBy;
	private String createdDate;
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
	 * @return the answerQuestion
	 */
	public String getAnswerQuestion() {
		return answerQuestion;
	}
	/**
	 * @param answerQuestion the answerQuestion to set
	 */
	public void setAnswerQuestion(String answerQuestion) {
		this.answerQuestion = answerQuestion;
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
		return "HistoryAnswer [id=" + id + ", userName=" + userName + ", idQuestion=" + idQuestion + ", answerQuestion="
				+ answerQuestion + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	} 
	
	
}
