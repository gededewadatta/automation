/**
 * 
 */
package led.automation.admin.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import led.automation.admin.model.Question;

/**
 * @author gederanadewadatta
 *
 */
public interface QuestionRepository extends CrudRepository<Question, Long>{
	@Query("select c.question from Question c where c.grade = :grade and c.subgrade= :subgrade and c.id=:id")
    Stream<Question> findByGradeReturnStream(@Param("grade") String grade,@Param("subgrade") String subGrade,@Param("id") String id);
//	@Query("select c.answer from Question c where c.grade = :grade and c.subgrade= :subgrade and c.id=:id")
//    Stream<Question> findAnswerByGradeReturnStream(@Param("grade") String grade,@Param("subgrade") String subGrade,@Param("id") String id);

}
