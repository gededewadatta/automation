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

import led.automation.admin.model.Competency;

/**
 * @author gederanadewadatta
 *
 */
public interface CompetencyRepository extends JpaRepository<Competency, Long>{
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_NAME) like %?1", nativeQuery = true)
	List<Competency> findByCompetencyName(String competencyName);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_CODE) like %?1", nativeQuery = true)
	List<Competency> findByCompetencyCode(String competencyCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_CODE) like %?1 AND UPPER(COMPETENCY_NAME) like %?2", nativeQuery = true)
	List<Competency> findByCompetencyCodeAndName(String competencyCode, String competencyName);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(DEPARTEMENT_CODE) like %?1", nativeQuery = true)
	List<Competency> findByDepartementCode(String departementCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(GRADE_CODE) like %?1", nativeQuery = true)
	List<Competency> findByGradeCode(String gradeCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(SUB_GRADE_CODE) like %?1", nativeQuery = true)
	List<Competency> findBySubGradeCode(String subGradeCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(DEPARTEMENT_CODE) like %?1 AND UPPER(GRADE_CODE) like %?2 AND UPPER(SUB_GRADE_CODE) like %?3", nativeQuery = true)
	List<Competency> findByDepartementCodeGradeCodeSubGradeCode(String departementCode, String gradeCode, String subGradeCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_NAME) like %?1 AND UPPER(DEPARTEMENT_CODE) like %?2 AND UPPER(GRADE_CODE) like %?3 AND UPPER(SUB_GRADE_CODE) like %?4", nativeQuery = true)
	List<Competency> findByCompetencyNameDepartementCodeGradeCodeSubGradeCode(String competencyName, String departementCode, String gradeCode, String subGradeCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_CODE) like %?1 AND UPPER(DEPARTEMENT_CODE) like %?2 AND UPPER(GRADE_CODE) like %?3 AND UPPER(SUB_GRADE_CODE) like %?4", nativeQuery = true)
	List<Competency> findByCompetenencyCodeDepartementCodeGradeCodeSubGradeCode(String competencyCode, String departementCode, String gradeCode, String subGradeCode);
	@Query(value = "SELECT * FROM COMPETENCY WHERE UPPER(COMPETENCY_CODE) like %?1 AND UPPER(COMPETENCY_NAME) like %?2 AND UPPER(DEPARTEMENT_CODE) like %?3 AND UPPER(GRADE_CODE) like %?4 AND UPPER(SUB_GRADE_CODE) like %?5", nativeQuery = true)
	List<Competency> findByAllParams(String competencyCode, String competencyName, String departementCode, String gradeCode, String subGradeCode);
	@Query(value = "SELECT dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME,com.competency_name"
			+ " FROM Competency com join Departement dept on com.departement_code = dept.departement_code"
			+ "join Grade g on com.grade_code = g.grade_code"
			+ "join SubGrade sub on g.grade_code = sub.grade_code "
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?1 and g.grade_code like %?3", nativeQuery = true)
	List<String> generateCompetency(String divisionCode, String departementCode, String gradeCode);

	@Modifying
	@Query(value = "insert into competency (id,created_by, created_date, departement_code, grade_code,sub_grade_code,competency_code,competency_name) \n" +
			"  select distinct * from (select (SELECT next_val FROM competency_seq),?1,?2,?3,?4,?5,?6,?7) as  comp  \n" +
			"  where not exists(select competency_code from competency where competency_code = ?5)LIMIT 1", nativeQuery = true)
	int insertCompetency(String createdBy, Date createdDate, String departementCode, String gradeCode, String subGradeCode, String competencyCode, String competencyName);

	@Modifying
	@Query(value = "update competency_seq set next_val=next_val + 1", nativeQuery = true)
	int updateCompetencySeq();

}
