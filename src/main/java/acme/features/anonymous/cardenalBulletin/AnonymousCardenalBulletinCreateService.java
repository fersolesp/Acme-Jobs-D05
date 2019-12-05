
package acme.features.anonymous.cardenalBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cardenalBulletins.CardenalBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCardenalBulletinCreateService implements AbstractCreateService<Anonymous, CardenalBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCardenalBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CardenalBulletin> request) {

		assert request != null;

		return true;
	}

	@Override
	public CardenalBulletin instantiate(final Request<CardenalBulletin> request) {

		assert request != null;

		CardenalBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new CardenalBulletin();
		result.setDeveloper("Willy Williams");
		result.setSkills("Python");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<CardenalBulletin> request, final CardenalBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "developer", "skills");

	}

	@Override
	public void bind(final Request<CardenalBulletin> request, final CardenalBulletin entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CardenalBulletin> request, final CardenalBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CardenalBulletin> request, final CardenalBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}
}
