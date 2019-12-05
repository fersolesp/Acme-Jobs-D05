
package acme.features.anonymous.blascoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.blascoBulletins.BlascoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousBlascoBulletinListService implements AbstractListService<Anonymous, BlascoBulletin> {

	@Autowired
	AnonymousBlascoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BlascoBulletin> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<BlascoBulletin> request, final BlascoBulletin entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userName", "description", "moment");

	}

	@Override
	public Collection<BlascoBulletin> findMany(final Request<BlascoBulletin> request) {
		// TODO Auto-generated method stub
		assert request != null;
		Collection<BlascoBulletin> result;
		result = this.repository.findMany();
		return result;
	}

}
