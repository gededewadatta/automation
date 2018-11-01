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

import led.automation.admin.model.Employee;

/**
 * @author gederanadewadatta
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query(value = "SELECT * FROM Employee WHERE UPPER(Employee_NAME) like %?1", nativeQuery = true)
	List<Employee> findByEmployeeName(String EmployeeName);
	@Query(value = "SELECT * FROM Employee WHERE UPPER(Employee_CODE) like %?1", nativeQuery = true)
	List<Employee> findByEmployeeCode(String EmployeeCode);
	@Query(value = "SELECT * FROM Employee WHERE UPPER(Employee_CODE) like %?1 AND UPPER(Employee_NAME) like %?2", nativeQuery = true)
	List<Employee> findByEmployeeCodeAndName(String EmployeeCode, String EmployeeName);
	@Query(value = "SELECT div.division_name,dept.departement_name,emp.employee_code,emp.employee_name"
			+ " FROM Employee emp join Departement dept on emp.departement_code = dept.departement_code"
			+ "join Division div on emp.division_code = div.division_code"
			+ "WHERE dept.departement_code like %?2 AND div.division_code like %?3 and emp.employee_code like %?1", nativeQuery = true)
	List<String> generateEmployee(String divisionCode, String employeeCode, String departementCode);
	@Query(value = "SELECT employee_name FROM Employee e join subgrade g on e.grade_code = g.grade_code WHERE e.grade_code = ?1 and g.sub_grade_code = ?2", nativeQuery = true)
	List<String> searchEmployeeByGradeAndSubGrade(String grade, String subGrade);

	@Modifying
	@Query(value = "insert into employee (id,created_by, created_date, departement_code, division_code,employee_code,employee_name,grade_code,subgrade_code,user_name) \n" +
			"  select distinct * from (select (SELECT next_val FROM employee_seq),?1,?2,?3,?4,?5,?6,?7,?8,?9) as  emp  \n" +
			"  where not exists(select employee_code from employee where employee_code = ?5)LIMIT 1", nativeQuery = true)
	int insertEmployee(String createdBy, Date createdDate, String departementCode, String divisionCode, String employeeCode, String employeeName, String gradeCode, String subgradeCode, String userName);

	@Modifying
	@Query(value = "update employee_seq set next_val=next_val + 1", nativeQuery = true)
	int updateEmployeeSeq();


}
