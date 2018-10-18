package led.automation.admin.repository;

import led.automation.admin.model.Grade;
import led.automation.admin.model.GradeJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeJsonRepoSitory extends JpaRepository<GradeJson, Long> {

    @Query(value = "Select * from grade grd  join subgrade subGrd on grd.grade_code = subGrd.grade_code and grd.departement_code = subgrd.departement_code", nativeQuery = true)
    List<GradeJson> findGradeAndSubGrade();
    @Query(value = "Select * from grade grd  join subgrade subGrd on grd.grade_code = subGrd.grade_code and grd.departement_code = subgrd.departement_code WHERE grd.departement_code like ?1%", nativeQuery = true)
    List<GradeJson> findByDepartmentCode(String DepartmentCode);
    @Query(value = "Select * from grade grd  join subgrade subGrd on grd.grade_code = subGrd.grade_code and grd.departement_code = subgrd.departement_code WHERE grd.division_code like ?1%", nativeQuery = true)
    List<GradeJson> findByDivisionCode(String DivisionCode);
    @Query(value = "Select * from grade grd  join subgrade subGrd on grd.grade_code = subGrd.grade_code and grd.departement_code = subgrd.departement_code WHERE grd.departement_code like ?1% AND grd.division_code like %?2", nativeQuery = true)
    List<GradeJson> findByDivisionCodeAndDivisionName(String DepartmentCode, String DivisionCode);
    @Query(value = "SELECT div.division_name,dept.departement_name,g.grade_name,sub.SUB_GRADE_NAME"
            + " FROM Grade g join Departement dept on g.departement_code = dept.departement_code"
            + "join Division div on dept.division_code = div.division_code"
            + "join SubGrade sub on g.grade_code = sub.grade_code "
            + "WHERE dept.departement_code like %?2 AND div.division_code like %?3", nativeQuery = true)
    List<String> generateGrade(String company, String divisionCode, String departementCode);

}
