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
	@Column(name = "DEPARTEMENT_CODE")
	private String departementCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSubGrade() {
		return subGrade;
	}
	public void setSubGrade(String subGrade) {
		this.subGrade = subGrade;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCompetency() {
		return competency;
	}
	public void setCompetency(String competency) {
		this.competency = competency;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public String getDepartementCode() {
		return departementCode;
	}

	public void setDepartementCode(String departementCode) {
		this.departementCode = departementCode;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", grade=" + grade + ", subGrade=" + subGrade + ", questions=" + questions
				+ ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4
				+ ", answer5=" + answer5 + ", correctAnswer=" + correctAnswer + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", competency=" + competency + ", level=" + level + ", departementCode=" + departementCode +"]";
	}
 
	
	
}
