/**
 * 
 */
package led.automation.admin.model;

import java.util.Date;

import javax.persistence.*; 

/**
 * @author gederanadewadatta
 *
 */
@Entity
public class Division {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQ")
    @SequenceGenerator(sequenceName = "division_seq", allocationSize = 1, name = "DIVISION_SEQ")
	private Long id;
	
	@Column(name = "DIVISION_CODE")
	private String divisonCode;
    
    @Column(name = "DIVISION_NAME")
    private String divisionName;
      
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
 
    
    
    
}
