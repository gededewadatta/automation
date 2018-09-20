/**
 * 
 */
package led.automation.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import led.automation.admin.model.Departement;

/**
 * @author gederanadewadatta
 *
 */
public interface DepartementRepository extends JpaRepository<Departement, Long>{

	@Query(value = "SELECT * FROM Departement WHERE division_code like %?1", nativeQuery = true)
	List<Departement> findByDivisionCode(String DivisionCode);
	@Query(value = "SELECT * FROM Departement WHERE Departement_CODE like %?1", nativeQuery = true)
	List<Departement> findByDepartementCode(String DepartementCode);
	@Query(value = "SELECT * FROM Departement WHERE Departement_CODE like %?1 AND division_code like %?2", nativeQuery = true)
	List<Departement> findByDepartementCodeAndDivisionCode(String DepartementCode, String division_code);
	@Query(value = "SELECT ?2,div.division_name,dept.departement_name"
			+ " FROM Division div join Departement dept on div.division_name = dept.division_name"
			+ "WHERE div.division_code like %?1", nativeQuery = true)
	List<String> generateDepartement(String divisionCode, String company);
//	List<String> findDepartementByDivCode(String gradeCode);

}
