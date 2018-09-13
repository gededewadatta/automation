/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import led.automation.admin.model.Employee;

/**
 * @author gederanadewadatta
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query(value = "SELECT * FROM Employee WHERE Employee_NAME like %?1", nativeQuery = true)
	List<Employee> findByEmployeeName(String EmployeeName);
	@Query(value = "SELECT * FROM Employee WHERE Employee_CODE like %?1", nativeQuery = true)
	List<Employee> findByEmployeeCode(String EmployeeCode);
	@Query(value = "SELECT * FROM Employee WHERE Employee_CODE like %?1 AND Employee_NAME like %?2", nativeQuery = true)
	List<Employee> findByEmployeeCodeAndName(String EmployeeCode, String EmployeeName);
	@Query(value = "SELECT div.division_name,dept.departement_name,emp.employee_code,emp.employee_name"
			+ " FROM Employee emp join Departement dept on emp.departement_code = dept.departement_code"
			+ "join Division div on emp.division_code = div.division_code"
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?3 and emp.employee_code like %?1", nativeQuery = true)
	List<String> generateEmployee(String divisionCode, String employeeCode, String departementCode);
	@Query(value = "SELECT user_name FROM Employee WHERE grade = '?1' and sub_grade = '?2' ", nativeQuery = true)
	List<String> searchEmployeeByGradeAndSubGrade(String grade, String subGrade);


}
