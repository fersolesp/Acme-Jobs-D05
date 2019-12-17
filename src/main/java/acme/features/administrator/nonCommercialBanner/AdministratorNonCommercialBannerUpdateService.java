
package acme.features.administrator.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.nonCommercialBanners.NonCommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorNonCommercialBannerUpdateService implements AbstractUpdateService<Administrator, NonCommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL", "jingle");

	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;

		NonCommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
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
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
