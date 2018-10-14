/**
 * 
 */
package led.automation.admin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import led.automation.admin.model.Departement;

/**
 * @author gederanadewadatta
 *
 */
public interface DepartementRepository extends JpaRepository<Departement, Long>{

	@Query(value = "SELECT * FROM Departement WHERE UPPER(division_code) like %?1", nativeQuery = true)
	List<Departement> findByDivisionCode(String DivisionCode);
	@Query(value = "SELECT * FROM Departement WHERE UPPER(Departement_CODE) like %?1", nativeQuery = true)
	List<Departement> findByDepartementCode(String DepartementCode);
	@Query(value = "SELECT * FROM Departement WHERE UPPER(Departement_NAME) like %?1", nativeQuery = true)
	List<Departement> findByDepartementName(String DepartementName);
	@Query(value = "SELECT * FROM Departement WHERE UPPER(Departement_CODE) like %?1 AND UPPER(division_code) like %?2", nativeQuery = true)
	List<Departement> findByDepartementCodeAndDivisionCode(String DepartementCode, String division_code);
	@Query(value = "SELECT * FROM Departement WHERE UPPER(Departement_NAME) like %?1 AND UPPER(division_code) like %?2", nativeQuery = true)
	List<Departement> findByDepartementNameAndDivisionCode(String DepartementName, String division_code);
	@Query(value = "SELECT * FROM Departement WHERE UPPER(Departement_CODE) like %?1 AND UPPER(division_code) like %?2 AND UPPER(Departement_NAME) like %?3", nativeQuery = true)
	List<Departement> findByDepartementCodeAndDivisionCodeAndDepartementName(String DepartementCode, String division_code,String DepartementName);
	@Query(value = "SELECT ?2,div.division_name,dept.departement_name"
			+ " FROM Division div join Departement dept on div.division_name = dept.division_name"
			+ "WHERE UPPER(div.division_code) like %?1", nativeQuery = true)
	List<String> generateDepartement(String divisionCode, String company);
//	List<String> findDepartementByDivCode(String gradeCode);

	@Modifying
	@Query(value = "insert into departement (id,departement_code, departement_name, division_code, created_by, created_date) \n" +
			"    select distinct * from (select (SELECT next_val FROM departement_seq),?1,?2,?3,?4,?5)  as dep  \n" +
			"    where not exists(select departement_code from departement where departement_code = ?1)LIMIT 1", nativeQuery = true)
	int insertDepartement(String departementCode, String departementName, String divisionCode, String createdBy, Date createdDate);

	@Modifying
	@Query(value = "update departement_seq set next_val=next_val + 1", nativeQuery = true)
	int updateDepartementSeq();

}
