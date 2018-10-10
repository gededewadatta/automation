/**
 * 
 */
package led.automation.admin.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import led.automation.admin.model.Division;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gederanadewadatta
 *
 */
public interface DivisionRepository extends CrudRepository<Division, Long> {
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_NAME) like %?1", nativeQuery = true)
	List<Division> findByDivisionName(String DivisionName);
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_CODE) like %?1", nativeQuery = true)
	List<Division> findByDivisionCode(String DivisionCode);
	@Query(value = "SELECT * FROM Division WHERE UPPER(Division_CODE) like %?1 AND UPPER(Division_NAME) like %?2", nativeQuery = true)
	List<Division> findByDivisionCodeAndName(String DivisionCode, String DivisionName);
	@Query(value = "SELECT ?1,div.division_name"
			+ " FROM Division div", nativeQuery = true)
	List<String> generateDivision(String company);

	@Modifying
	@Query(value = "insert into division (id,created_by, created_date, division_code, division_name) select distinct (SELECT next_val FROM division_seq),?1,?2,?3,?4 from division	where not exists(select 1 from division where division_code = ?3)", nativeQuery = true)
	int insertDivision(String createdBy, Date createdDate, String divisionCode, String divisionName);

	@Modifying
	@Query(value = "update division set division_name = ?2 where division_code = ?1", nativeQuery = true)
	int updateDivision(String divisionCode, String divisionName);

	@Modifying
	@Query(value = "update division_seq set next_val=next_val + 1", nativeQuery = true)
	int updateDivisionSeq();
}
