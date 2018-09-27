package led.automation.admin.repository;

/*
 * @Author FikriAsandhita
 *
 */

import led.automation.admin.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "SELECT * FROM Report WHERE UPPER(Employee_Code) like %?1", nativeQuery = true)
    List<Report> findByEmployee(String employeeCode);
    @Query(value = "SELECT * FROM Report WHERE UPPER(Grade) like %?1", nativeQuery = true)
    List<Report> findByGrade(String grade);
    @Query(value = "SELECT * FROM Report WHERE UPPER(Employee_Code) like %?1 AND UPPER(Grade) like %?2", nativeQuery = true)
    List<Report> findByEmployeeAndGrade(String employeeCode,String grade);
}
