
package acme.features.authenticated.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "minReward", "maxReward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed, isUnique, isEuroMin, isEuroMax, isInRange, positiveMinReward, positiveMaxReward;
		Calendar calendar;
		Date minimumDeadLine;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadLine = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadLine), "deadline", "consumer.offer.error.label.deadline");
		}

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "consumer.offer.error.label.confirm");

		if (!errors.hasErrors("ticker")) {
			isUnique = this.repository.findOneOfferByTicker(entity.getTicker()) != null;
			errors.state(request, !isUnique, "ticker", "consumer.offer.error.label.ticker");
		}

		if (!errors.hasErrors("minReward")) {
			isEuroMin = entity.getMinReward().getCurrency().equals("EUR") || entity.getMinReward().getCurrency().equals("€");
			errors.state(request, isEuroMin, "minReward", "consumer.offer.error.label.reward-currency");
		}
		if (!errors.hasErrors("maxReward")) {
			isEuroMax = entity.getMaxReward().getCurrency().equals("EUR") || entity.getMinReward().getCurrency().equals("€");
			errors.state(request, isEuroMax, "maxReward", "consumer.offer.error.label.reward-currency");
		}

		if (!errors.hasErrors("minReward") && !errors.hasErrors("maxReward")) {
			isInRange = entity.getMinReward().getAmount() <= entity.getMaxReward().getAmount();
			errors.state(request, isInRange, "maxReward", "consumer.offer.error.label.range-reward");
		}

		if (!errors.hasErrors("minReward")) {
			positiveMinReward = entity.getMinReward().getAmount() >= 0;
			errors.state(request, positiveMinReward, "minReward", "consumer.offer.error.label.reward-amount");
		}
		if (!errors.hasErrors("maxReward")) {
			positiveMaxReward = entity.getMaxReward().getAmount() >= 0;
			errors.state(request, positiveMaxReward, "maxReward", "consumer.offer.error.label.reward-amount");
		}

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
