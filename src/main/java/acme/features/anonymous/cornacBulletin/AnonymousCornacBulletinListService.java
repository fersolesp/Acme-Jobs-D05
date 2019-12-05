
package acme.features.anonymous.cornacBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cornacBulletin.CornacBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCornacBulletinListService implements AbstractListService<Anonymous, CornacBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCornacBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CornacBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<CornacBulletin> findMany(final Request<CornacBulletin> request) {

		assert request != null;

		Collection<CornacBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<CornacBulletin> request, final CornacBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "publisher", "publication", "moment");

	}

}
