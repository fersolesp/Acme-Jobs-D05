
package acme.features.anonymous.cardenalBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cardenalBulletins.CardenalBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCardenalBulletinListService implements AbstractListService<Anonymous, CardenalBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCardenalBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CardenalBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<CardenalBulletin> findMany(final Request<CardenalBulletin> request) {

		assert request != null;

		Collection<CardenalBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<CardenalBulletin> request, final CardenalBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "developer", "skills", "moment");

	}

}
