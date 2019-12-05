
package acme.entities.pradasBulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PradasBulletin extends DomainEntity {

	//Serializacion identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes  --------------------------------------------

	@NotBlank
	private String				person;

	@NotBlank
	private String				information;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;
}
