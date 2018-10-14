/**
 * 
 */
package led.automation.admin.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	@Modifying
	@Query(value = "insert into subgrade (id_sub_grade, sub_grade_code, sub_grade_name, grade_code, departement_code, created_by, created_date) \n" +
			"    select distinct * from (select (SELECT next_val FROM subgrade_seq),?1,?2,?3,?4,?5,?6)  as subgrade  \n" +
			"    where not exists(select sub_grade_code from subgrade where sub_grade_code = ?1 and grade_code = ?3 and departement_code = ?4)LIMIT 1", nativeQuery = true)
	int insertSubGrade(String SubGradeCode, String SubGradeName, String gradeCode, String departementCode, String createdBy, Date createdDate);

	@Modifying
	@Query(value = "update subgrade_seq set next_val=next_val + 1", nativeQuery = true)
	int updateSubGradeSeq();

	
}
