/**
 * 
 */
package led.automation.employee.dao;
 
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gederanadewadatta
 *
 */
@Transactional(rollbackFor=Exception.class)
public interface EmployeeDAO {
	 

}
