/**
 * 
 */
package led.automation.admin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.Grade;

/**
 * @author gederanadewadatta
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {

	@Query(value = "Select * from grade grd WHERE UPPER(grd.departement_code) like %?1", nativeQuery = true)
	List<Grade> findByDepartmentCode(String DepartmentCode);
	@Query(value = "Select * from grade grd WHERE UPPER(grd.division_code) like %?1", nativeQuery = true)
	List<Grade> findByDivisionCode(String DivisionCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.departement_code) like %?1 AND UPPER(grd.division_code) like %?2", nativeQuery = true)
	List<Grade> findByDepartmentCodeAndDivisionCode(String DepartmentCode, String DivisionCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.grade_code) like %?1", nativeQuery = true)
	List<Grade> findByGradeCode(String gradeCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.grade_name) like %?1", nativeQuery = true)
	List<Grade> findByGradeName(String gradeName);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.grade_code) like %?1 AND UPPER(grd.grade_name) like %?2 AND UPPER(grd.departement_code) like %?3", nativeQuery = true)
	List<Grade> findByGradeCodeAndGradeNameAndDepartementCode(String gradeCode, String gradeName, String departementCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.grade_code) like %?1 AND UPPER(grd.grade_name) like %?2 AND UPPER(grd.division_code) like %?3", nativeQuery = true)
	List<Grade> findByGradeCodeAndGradeNameAndDivisionCode(String gradeCode, String gradeName, String divisionCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.division_code) like %?1 AND UPPER(grd.departement_code) like %?2 AND UPPER(grd.grade_code) like %?3", nativeQuery = true)
	List<Grade> findByDepartmentCodeAndDivisionCodeAndGradeCode(String divisionCode, String departementCode, String gradeCode);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.division_code) like %?1 AND UPPER(grd.departement_code) like %?2 AND UPPER(grd.grade_name) like %?3", nativeQuery = true)
	List<Grade> findByDepartmentCodeAndDivisionCodeAndGradeName(String divisionCode, String departementCode, String gradeName);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.division_code) like %?1 AND UPPER(grd.departement_code) like %?2 AND UPPER(grd.grade_code) like %?3 AND UPPER(grd.grade_name) like %?4", nativeQuery = true)
	List<Grade> findByDepartmentCodeAndDivisionCodeAndGradeCodeAndGradeName(String divisionCode, String departementCode, String gradeCode, String gradeName);
	@Query(value = "Select * from grade grd  WHERE UPPER(grd.grade_code) like %?1 AND UPPER(grd.grade_name) like %?2", nativeQuery = true)
	List<Grade> findByGradeCodeAndGradeName(String gradeCode, String gradeName);
	@Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME"
			+ " FROM Grade g join Departement dept on g.departement_code = dept.departement_code"
			+ "join Division div on dept.division_code = div.division_code"
			+ "join SubGrade sub on g.grade_code = sub.grade_code "
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?3", nativeQuery = true)
	List<String> generateGrade(String company, String divisionCode, String departementCode);
//	List<String> findGradeByDeptCode(String gradeCode);
	@Modifying
	@Query(value = "insert into grade (id_grade, grade_code, grade_name, departement_code, division_code, created_by, created_date) \n" +
			"    select distinct * from (select (SELECT next_val FROM grade_seq),?1,?2,?3,?4,?5,?6)  as grade  \n" +
			"    where not exists(select grade_code from grade where grade_code = ?1 and departement_code = ?3)LIMIT 1", nativeQuery = true)
	int insertGrade(String gradeCode, String gradeName, String departementCode, String divisionCode, String createdBy, Date createdDate);

	@Modifying
	@Query(value = "update grade_seq set next_val=next_val + 1", nativeQuery = true)
	int updateGradeSeq();

}
