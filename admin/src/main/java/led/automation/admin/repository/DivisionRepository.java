/**
 * 
 */
package led.automation.admin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import led.automation.admin.model.Division;

/**
 * @author gederanadewadatta
 *
 */
public interface DivisionRepository extends JpaRepository<Division, Long>{
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_NAME) like %?1", nativeQuery = true)
	List<Division> findByDivisionName(String DivisionName);
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_CODE) like %?1", nativeQuery = true)
	List<Division> findByDivisionCode(String DivisionCode);
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_CODE) like %?1 AND UPPER(Division_NAME) like %?2", nativeQuery = true)
	List<Division> findByDivisionCodeAndName(String DivisionCode, String DivisionName);
	@Query(value = "SELECT ?1,div.division_name"
			+ " FROM Division div", nativeQuery = true)
	List<String> generateDivision(String company);

}
