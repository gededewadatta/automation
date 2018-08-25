/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.SubGrade;

/**
 * @author gederanadewadatta
 *
 */
public interface SubGradeRepository extends CrudRepository<SubGrade, Long>{

	@Query(value = "SELECT * FROM SubGrade WHERE SubGrade_NAME like %?1", nativeQuery = true)
	SubGrade findBySubGradeName(String SubGradeName);
	@Query(value = "SELECT * FROM SubGrade WHERE SubGrade_CODE like %?1", nativeQuery = true)
	SubGrade findBySubGradeCode(String SubGradeCode);
	@Query(value = "SELECT * FROM SubGrade WHERE SubGrade_CODE like %?1 AND SubGrade_NAME like %?2", nativeQuery = true)
	SubGrade findBySubGradeCodeAndName(String SubGradeCode, String SubGradeName);
	@Query(value = "SELECT * FROM Employee WHERE Employee_CODE like %?1 AND Employee_NAME like %?2", nativeQuery = true)
	List<String> generateSubGrade(String divisionCode, String departementCode, String gradeCode);

}
