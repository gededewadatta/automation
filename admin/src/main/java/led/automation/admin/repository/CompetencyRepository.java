/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.Competency;

/**
 * @author gederanadewadatta
 *
 */
public interface CompetencyRepository extends JpaRepository<Competency, Long>{
	@Query(value = "SELECT * FROM COMPETENCY WHERE COMPETENCY_NAME like %?1", nativeQuery = true)
	Competency findByCompetencyName(String competencyName);
	@Query(value = "SELECT * FROM COMPETENCY WHERE COMPETENCY_CODE like %?1", nativeQuery = true)
	Competency findByCompetencyCode(String competencyCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE COMPETENCY_CODE like %?1 AND COMPETENCY_NAME like %?2", nativeQuery = true)
	Competency findByCompetencyCodeAndName(String competencyCode, String competencyName);
	@Query(value = "SELECT dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME,com.competency_name"
			+ " FROM Competency com join Departement dept on com.departement_code = dept.departement_code"
			+ "join Grade g on com.grade_code = g.grade_code"
			+ "join SubGrade sub on g.grade_code = sub.grade_code "
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?1 and g.grade_code like %?3", nativeQuery = true)
	List<String> generateCompetency(String divisionCode, String departementCode, String gradeCode);

}
