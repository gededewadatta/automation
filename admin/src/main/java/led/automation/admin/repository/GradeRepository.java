/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.Grade;

/**
 * @author gederanadewadatta
 *
 */
public interface GradeRepository extends CrudRepository<Grade, Long>{

	@Query(value = "SELECT * FROM Grade WHERE Grade_NAME like %?1", nativeQuery = true)
	Grade findByGradeName(String GradeName);
	@Query(value = "SELECT * FROM Grade WHERE Grade_CODE like %?1", nativeQuery = true)
	Grade findByGradeCode(String GradeCode);
	@Query(value = "SELECT * FROM Grade WHERE Grade_CODE like %?1 AND Grade_NAME like %?2", nativeQuery = true)
	Grade findByGradeCodeAndName(String GradeCode, String GradeName);
	@Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME"
			+ " FROM Grade g join Departement dept on g.departement_code = dept.departement_code"
			+ "join Division div on dept.division_code = div.division_code"
			+ "join SubGrade sub on g.grade_code = sub.grade_code "
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?3", nativeQuery = true)
	List<String> generateGrade(String company, String divisionCode, String departementCode);

}
