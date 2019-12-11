
package acme.features.sponsor.nonCommercialBanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.nonCommercialBanners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {
	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorNonCommercialBannerRepository repository;


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
	public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
		assert request != null;
		NonCommercialBanner result;

		result = new NonCommercialBanner();
		result.setSponsor(this.repository.getSponsorById(request.getPrincipal().getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isSpam;
		List<CustomisationParameter> cp;
		String[] spamWordsEnglish, spamWordsSpanish;
		Double englishThreshold, spanishThreshold;

		if (!errors.hasErrors("slogan")) {

			cp = new ArrayList<>(this.repository.findCustomisationParameters());
			spamWordsEnglish = cp.get(0).getSpamWordList().split(",");
			spamWordsSpanish = cp.get(1).getSpamWordList().split(",");
			englishThreshold = cp.get(0).getSpamThreshold();
			spanishThreshold = cp.get(1).getSpamThreshold();

			String slogan = entity.getSlogan().toLowerCase();
			Integer numberOfEnglishSpamWords = 0;
			Integer numberOfSpanishSpamWords = 0;

			for (String word : spamWordsEnglish) {
				if (slogan.contains(word.trim())) {
					numberOfEnglishSpamWords++;
				}
			}

			for (String word : spamWordsSpanish) {
				if (slogan.contains(word.trim())) {
					numberOfSpanishSpamWords++;
				}
			}
			isSpam = numberOfEnglishSpamWords * 100 / slogan.length() > englishThreshold || numberOfSpanishSpamWords * 100 / slogan.length() > spanishThreshold;

			errors.state(request, !isSpam, "slogan", "sponsor.non-commercial-banner.error.spam");
		}

	}

	@Override
	public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
