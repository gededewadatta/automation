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

	@Query(value = "SELECT * FROM Departement WHERE Departement_NAME like %?1", nativeQuery = true)
	List<Departement> findByDepartementName(String DepartementName);
	@Query(value = "SELECT * FROM Departement WHERE Departement_CODE like %?1", nativeQuery = true)
	List<Departement> findByDepartementCode(String DepartementCode);
	@Query(value = "SELECT * FROM Departement WHERE Departement_CODE like %?1 AND Departement_NAME like %?2", nativeQuery = true)
	List<Departement> findByDepartementCodeAndName(String DepartementCode, String DepartementName);
	@Query(value = "SELECT ?2,div.division_name,dept.departement_name"
			+ " FROM Division div join Departement dept on div.division_name = dept.division_name"
			+ "WHERE div.division_code like %?1", nativeQuery = true)
	List<String> generateDepartement(String divisionCode, String company);

}
