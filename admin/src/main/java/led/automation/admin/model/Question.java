/**
 * 
 */
package led.automation.admin.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author gederanadewadatta
 *
 */
@Entity
@Table(name = "QUESTION")
@EntityListeners(AuditingEntityListener.class)

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ")
	@SequenceGenerator(sequenceName = "question_seq", allocationSize = 1, name = "QUESTION_SEQ")
	private Long id;

	@Column(name = "GRADE")
	private String grade;
	@Column(name = "SUB_GRADE")
	private String subGrade;
	@Column(name = "QUESTIONS")
	private String questions;
	@Column(name = "ANSWER_1")
	private String answer1;
	@Column(name = "ANSWER_2")
	private String answer2;
	@Column(name = "ANSWER_3")
	private String answer3;
	@Column(name = "ANSWER_4")
	private String answer4;
	@Column(name = "ANSWER_5")
	private String answer5;
	@Column(name = "CORRECT_ANSWER")
	private String correctAnswer;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "CREATED_BY")
	private String createdBy; 
	@Column(name = "COMPETENCY")
	private String competency;
	@Column(name = "LEVEL")
	private String level;
	//ini untuk nampung (Optional,Yes/No,FreeText):start
	@Column(name = "QUESTION_TYPE")
	private String questionType;
	//ini untuk nampung (Optional,Yes/No,FreeText):start
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [id=" + id + ", grade=" + grade + ", subGrade=" + subGrade + ", questions=" + questions
				+ ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4
				+ ", answer5=" + answer5 + ", correctAnswer=" + correctAnswer + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", competency=" + competency + ", level=" + level + ", questionType="
				+ questionType + "]";
	}
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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the subGrade
	 */
	public String getSubGrade() {
		return subGrade;
	}
	/**
	 * @param subGrade the subGrade to set
	 */
	public void setSubGrade(String subGrade) {
		this.subGrade = subGrade;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
	 
	
}
