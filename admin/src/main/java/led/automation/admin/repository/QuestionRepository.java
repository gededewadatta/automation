/**
 * 
 */
package led.automation.admin.repository;
 

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import led.automation.admin.model.Question;

/**
 * @author gederanadewadatta
 *
 */
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query(value = "SELECT * FROM Question WHERE GRADE = ?1", nativeQuery = true)
	List<Question> findByGrade(String gradeCode);
	@Query(value = "SELECT * FROM Question WHERE competency = ?1", nativeQuery = true)
	List<Question> findByCompetency(String competencyCode);
	@Query(value = "SELECT * FROM Question WHERE GRADE = ?1 and competency = ?2", nativeQuery = true)
	List<Question> findByGradeAndCompetency(String Grade, String competencyCode);
	@Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,com.competency_name,quest.QUESTION"
			+ " FROM Division div join Departement dept on div.division_name = dept.division_name"
			+ " join Grade g on dept.departement_code = g.departement_code"
			+ " join Competency com on com.departement_code = dept.departement_code"
			+ " join Question q on q.competency = com.competency_code"
			+ " WHERE dept.departement_code like %?1 and g.grade_code like %?2", nativeQuery = true)
	List<String> generateQuestion(String departementCode, String gradeCode);

	@Modifying
	@Query(value = "insert into question (id, answer_1, answer_2, answer_3, " +
			"answer_4, answer_5, competency, correct_answer, created_by, created_date, " +
			"departement_code, grade, level, question_type, question_code, questions, sub_grade) \n" +
			"select (SELECT distinct next_val FROM question_seq),?1,?2,?3,?4,?5,?6,?7,?8," +
			"?9,?10,?11,?12,?13,?14,?15,?16 from dual  \n" +
			"    where not exists(select question_code from question where question_code = ?14)LIMIT 1", nativeQuery = true)
	int insertQuestion(String answer1, String answer2, String answer3, String answer4, String answer5,
					   String competency, String correctAnswer, String createdBy, Date createdDate, String departmentCode
						,String grade, String level, String questionType, String questionCode, String questions, String subGrade);

	@Modifying
	@Query(value = "update question_seq set next_val=next_val + 1", nativeQuery = true)
	int updateQuestionSeq();

	@Modifying
	@Query(value = "update question set answer_1 = ?1, answer_2= ?2, answer_3= ?3, " +
			"answer_4= ?4, answer_5= ?5, competency= ?6, correct_answer= ?7, created_by= ?8, created_date= ?9, " +
			"departement_code= ?10, grade= ?11, level= ?12, question_type= ?13, question_code= ?14, " +
			"questions= ?15, sub_grade= ?16 where question_code = ?14", nativeQuery = true)
	int updateQuestion(String answer1, String answer2, String answer3, String answer4, String answer5,
					   String competency, String correctAnswer, String createdBy, Date createdDate, String departmentCode
			,String grade, String level, String questionType, String questionCode, String questions, String subGrade);


	@Modifying
	@Query(value = "update pendingquestion set answer_1 = ?1, answer_2= ?2, answer_3= ?3, " +
			"answer_4= ?4, answer_5= ?5, competency= ?6, correct_answer= ?7, created_by= ?8, created_date= ?9, " +
			"level= ?10, question_type= ?11," +
			"questions= ?12, user_name= ?13 where questions = ?12", nativeQuery = true)
	int updatePendingQuestion(String answer1, String answer2, String answer3, String answer4, String answer5,
					   String competency, String correctAnswer, String createdBy, Date createdDate,
						String level, String questionType, String questions, String userName);


}
