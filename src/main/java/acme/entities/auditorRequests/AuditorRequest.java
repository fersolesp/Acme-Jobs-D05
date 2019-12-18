
package acme.entities.auditorRequests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status")
})
public class AuditorRequest extends DomainEntity {
	// Serialization identifier -----------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String					firm;

	@NotBlank
	@Column(length = 1024)
	private String					responsabilityStatement;

	@NotNull
	private AuditorRequestStatus	status;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated			authenticated;
}
