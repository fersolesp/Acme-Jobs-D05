
package acme.features.anonymous.pradasBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pradasBulletin.PradasBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPradasBulletinCreateService implements AbstractCreateService<Anonymous, PradasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousPradasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PradasBulletin> request) {

		assert request != null;

		return true;
	}

	@Override
	public PradasBulletin instantiate(final Request<PradasBulletin> request) {

		assert request != null;

		PradasBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PradasBulletin();
		result.setPerson("John Doe");
		result.setInformation("Lorem ipsum!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<PradasBulletin> request, final PradasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "person", "information");

	}

	@Override
	public void bind(final Request<PradasBulletin> request, final PradasBulletin entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<PradasBulletin> request, final PradasBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<PradasBulletin> request, final PradasBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
