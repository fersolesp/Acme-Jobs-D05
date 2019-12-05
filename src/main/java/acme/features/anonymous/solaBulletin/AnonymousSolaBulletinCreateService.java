
package acme.features.anonymous.solaBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.solaBulletins.SolaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousSolaBulletinCreateService implements AbstractCreateService<Anonymous, SolaBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousSolaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<SolaBulletin> request) {

		assert request != null;

		return true;
	}

	@Override
	public SolaBulletin instantiate(final Request<SolaBulletin> request) {

		assert request != null;

		SolaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new SolaBulletin();
		result.setCybernaut("Adam Blunt");
		result.setContribution("Here we go again!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<SolaBulletin> request, final SolaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "cybernaut", "contribution");

	}

	@Override
	public void bind(final Request<SolaBulletin> request, final SolaBulletin entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<SolaBulletin> request, final SolaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<SolaBulletin> request, final SolaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
