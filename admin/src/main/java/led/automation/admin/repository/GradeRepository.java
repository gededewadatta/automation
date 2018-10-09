/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.Grade;

/**
 * @author gederanadewadatta
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {

	@Query(value = "Select * from grade grd WHERE grd.departement_code like %?1", nativeQuery = true)
	List<Grade> findByDepartmentCode(String DepartmentCode);
	@Query(value = "Select * from grade grd WHERE grd.division_code like %?1", nativeQuery = true)
	List<Grade> findByDivisionCode(String DivisionCode);
	@Query(value = "Select * from grade grd  WHERE grd.departement_code like %?1 AND grd.division_code like %?2", nativeQuery = true)
	List<Grade> findByDepartmentCodeAndDivisionCode(String DepartmentCode, String DivisionCode);
	@Query(value = "Select * from grade grd  WHERE grd.grade_code like %?1", nativeQuery = true)
	List<Grade> findByGradeCode(String gradeCode);
	@Query(value = "Select * from grade grd  WHERE grd.grade_name like %?1", nativeQuery = true)
	List<Grade> findByGradeName(String gradeName);
	@Query(value = "Select * from grade grd  WHERE grd.grade_code like %?1 AND grd.grade_name like %?2", nativeQuery = true)
	List<Grade> findByGradeCodeAndGradeName(String gradeCode, String gradeName);
	@Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME"
			+ " FROM Grade g join Departement dept on g.departement_code = dept.departement_code"
			+ "join Division div on dept.division_code = div.division_code"
			+ "join SubGrade sub on g.grade_code = sub.grade_code "
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?3", nativeQuery = true)
	List<String> generateGrade(String company, String divisionCode, String departementCode);
//	List<String> findGradeByDeptCode(String gradeCode);

}
