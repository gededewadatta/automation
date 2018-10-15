/**
 * 
 */
package led.automation.wsproxy;

import java.util.Date;

 

/**
 * @author gederanadewadatta
 *
 */

public class PendingQuestion {
	private String id;
	private String userName;
	private String questions;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String correctAnswer;  
	private String competency;
	private String level;
	private String questionType;
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
	 * @return the questions
	 */
	public String getQuestions() {
		return questions;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	/**
	 * @return the answer1
	 */
	public String getAnswer1() {
		return answer1;
	}
	/**
	 * @param answer1 the answer1 to set
	 */
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	/**
	 * @return the answer2
	 */
	public String getAnswer2() {
		return answer2;
	}
	/**
	 * @param answer2 the answer2 to set
	 */
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	/**
	 * @return the answer3
	 */
	public String getAnswer3() {
		return answer3;
	}
	/**
	 * @param answer3 the answer3 to set
	 */
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	/**
	 * @return the answer4
	 */
	public String getAnswer4() {
		return answer4;
	}
	/**
	 * @param answer4 the answer4 to set
	 */
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	/**
	 * @return the answer5
	 */
	public String getAnswer5() {
		return answer5;
	}
	/**
	 * @param answer5 the answer5 to set
	 */
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/**
	 * @return the competency
	 */
	public String getCompetency() {
		return competency;
	}
	/**
	 * @param competency the competency to set
	 */
	public void setCompetency(String competency) {
		this.competency = competency;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the questionType
	 */
	public String getQuestionType() {
		return questionType;
	}
	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PendingQuestion [id=" + id + ", userName=" + userName + ", questions=" + questions + ", answer1="
				+ answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", answer5="
				+ answer5 + ", correctAnswer=" + correctAnswer + ", competency=" + competency + ", level=" + level
				+ ", questionType=" + questionType + "]";
	}
 
	
	
	
	
	
}
