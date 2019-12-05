
package acme.entities.companyRecords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars"), @Index(columnList = "sector")
})
public class CompanyRecord extends DomainEntity {

	//Serializacion identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes  --------------------------------------------

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotBlank
	@URL
	private String				url;

	@NotBlank
	@Pattern(regexp = "^([+])?[0-9]{0,4}[(]{0,1}[0-9]{0,4}[)]{0,1}[0-9]{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	private boolean				incorporated;

	@Range(min = 0, max = 5)
	private Integer				stars;

}
