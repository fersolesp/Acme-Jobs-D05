
package acme.entities.commercialBanners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

}
