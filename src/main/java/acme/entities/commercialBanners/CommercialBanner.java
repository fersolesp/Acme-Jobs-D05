
package acme.entities.commercialBanners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.banners.Banner;
import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {
	//Serializacion identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	@CreditCardNumber
	@NotBlank
	private String				creditCard;

	// Relationships ------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
