
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		boolean result;
		int ncbId;
		CommercialBanner ncb;
		Sponsor sponsor;
		Principal principal;

		ncbId = request.getModel().getInteger("id");
		ncb = this.repository.findOneCommercialBannerById(ncbId);
		sponsor = ncb.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL", "creditCard");

	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialBannerById(id);
		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomisationParameter cp = this.repository.findCustomisationParameters();
		String[] listaCustomisationParameter;
		Integer cuenta = 0;
		Double limitePalabrasSpamPermitidas = Double.valueOf(entity.getSlogan().split(" ").length) * cp.getSpamThreshold() / 100.0;

		if (!errors.hasErrors("slogan")) {

			listaCustomisationParameter = cp.getSpamWordList().split(",");

			for (String s : listaCustomisationParameter) {
				String mensajeParcial = entity.getSlogan().toLowerCase();
				int indice = mensajeParcial.indexOf(s);
				while (indice != -1) {
					cuenta++;
					mensajeParcial = mensajeParcial.substring(indice + 1);
					indice = mensajeParcial.indexOf(s);
				}
				errors.state(request, cuenta <= limitePalabrasSpamPermitidas, "slogan", "sponsor.commercial-banner.error.spam");

				if (cuenta > limitePalabrasSpamPermitidas) {
					break;
				}
			}

		}
	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
