
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Banner extends DomainEntity {
	// Serialization identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	@URL
	@NotBlank
	private String				picture;

	@NotBlank
	private String				slogan;

	@URL
	@NotBlank
	private String				targetURL;

}
