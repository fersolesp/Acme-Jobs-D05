
package acme.features.anonymous.pradasBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pradasBulletin.PradasBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPradasBulletinListService implements AbstractListService<Anonymous, PradasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousPradasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PradasBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PradasBulletin> findMany(final Request<PradasBulletin> request) {

		assert request != null;

		Collection<PradasBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<PradasBulletin> request, final PradasBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "person", "information", "moment");

	}

}
