
package acme.features.anonymous.solaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.solaBulletins.SolaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousSolaBulletinListService implements AbstractListService<Anonymous, SolaBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousSolaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<SolaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<SolaBulletin> findMany(final Request<SolaBulletin> request) {

		assert request != null;

		Collection<SolaBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<SolaBulletin> request, final SolaBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "cybernaut", "contribution", "moment");

	}

}
