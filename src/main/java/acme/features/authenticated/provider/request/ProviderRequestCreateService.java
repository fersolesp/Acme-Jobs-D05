
package acme.features.authenticated.provider.request;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "reward");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Request result;
		result = new Request();

		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted, isDuplicated, isEuro, positiveReward;
		Calendar calendar;
		Date minimumDeadLine;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadLine = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadLine), "deadline", "provider.request.error.deadline");
		}

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneRequestByTicker(entity.getTicker()) != null;
			errors.state(request, !isDuplicated, "ticker", "provider.request.error.duplicated");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

		if (!errors.hasErrors("reward")) {
			isEuro = entity.getReward().getCurrency().equals("EUR") || entity.getReward().getCurrency().equals("€");
			errors.state(request, isEuro, "reward", "provider.request.error.reward-currency");

			positiveReward = entity.getReward().getAmount() >= 0;
			errors.state(request, positiveReward, "reward", "provider.request.error.reward-amount");
		}
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		// TODO Auto-generated method stub
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
