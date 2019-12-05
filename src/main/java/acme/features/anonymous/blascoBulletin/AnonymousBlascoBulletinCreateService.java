
package acme.features.anonymous.blascoBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blascoBulletins.BlascoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBlascoBulletinCreateService implements AbstractCreateService<Anonymous, BlascoBulletin> {

	@Autowired
	AnonymousBlascoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BlascoBulletin> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public BlascoBulletin instantiate(final Request<BlascoBulletin> request) {
		// TODO Auto-generated method stub
		assert request != null;

		BlascoBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result = new BlascoBulletin();
		result.setUserName("Username");
		result.setDescription("description");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<BlascoBulletin> request, final BlascoBulletin entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userName", "description");

	}

	@Override
	public void bind(final Request<BlascoBulletin> request, final BlascoBulletin entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<BlascoBulletin> request, final BlascoBulletin entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<BlascoBulletin> request, final BlascoBulletin entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
