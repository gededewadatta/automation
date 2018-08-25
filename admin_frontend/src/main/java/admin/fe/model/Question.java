/**
 * 
 */
package admin.fe.model;

import java.util.Date;
  
/**
 * @author gederanadewadatta
 *
 */
 
public class Question {
    private Long id;
    private String grade;
    private String subGrade;
    private String question;
    private Date date;
	

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
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


}
