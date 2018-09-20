/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.SubGrade;

/**
 * @author gederanadewadatta
 *
 */
public interface SubGradeRepository extends JpaRepository<SubGrade, Long> {

	@Query(value = "SELECT * FROM SubGrade WHERE Sub_Grade_NAME like %?1", nativeQuery = true)
	List<SubGrade> findBySubGradeName(String SubGradeName);
	@Query(value = "SELECT * FROM SubGrade WHERE grade_code like %?1", nativeQuery = true)
	List<SubGrade> findBySubGradeCode(String SubGradeCode);
	@Query(value = "SELECT * FROM SubGrade WHERE grade_code like %?1", nativeQuery = true)
	List<SubGrade> findBySubGradeCodeAndName(String SubGradeCode, String SubGradeName);
	@Query(value = "SELECT * FROM Employee WHERE Employee_CODE like %?1 AND Employee_NAME like %?2", nativeQuery = true)
	List<String> generateSubGrade(String divisionCode, String departementCode, String gradeCode);
	//alternatif search sub grade : start
	@Query(value = "SELECT * FROM Employee WHERE grade_code = '%?1'", nativeQuery = true)
	List<String> findSubGradeByGradeCode(String gradeCode);
	//alternatif search sub grade : stop

	
}
