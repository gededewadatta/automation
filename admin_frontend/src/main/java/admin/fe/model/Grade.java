/**
 * 
 */
package admin.fe.model;

import java.util.Date;

import javax.persistence.*; 

/**
 * @author gederanadewadatta
 *
 */
@Entity
public class Grade {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_SEQ")
    @SequenceGenerator(sequenceName = "grade_seq", allocationSize = 1, name = "GRADE_SEQ")
    Long id;
	
	@Column(name = "NAME")
    String name;
    
    @Column(name = "EMAIL")
    String email;

    @Column(name = "CREATED_DATE")
    Date date;
}
