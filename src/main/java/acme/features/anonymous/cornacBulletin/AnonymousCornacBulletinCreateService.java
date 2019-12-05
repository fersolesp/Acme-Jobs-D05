
package acme.features.anonymous.cornacBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cornacBulletin.CornacBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCornacBulletinCreateService implements AbstractCreateService<Anonymous, CornacBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCornacBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CornacBulletin> request) {

		assert request != null;

		return true;
	}

	@Override
	public CornacBulletin instantiate(final Request<CornacBulletin> request) {

		assert request != null;

		CornacBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new CornacBulletin();
		result.setPublisher("Rolan");
		result.setPublication("Hi!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<CornacBulletin> request, final CornacBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "publisher", "publication");

	}

	@Override
	public void bind(final Request<CornacBulletin> request, final CornacBulletin entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CornacBulletin> request, final CornacBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CornacBulletin> request, final CornacBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
