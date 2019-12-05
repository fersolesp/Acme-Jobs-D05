
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedListService implements AbstractListService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {

		assert request != null;
		boolean result;
		int messageThreadId;
		Integer messagesOfUserInThread;
		Principal principal = request.getPrincipal();

		messageThreadId = request.getModel().getInteger("id");
		messagesOfUserInThread = this.repository.findNumberOfMessagesOfUserInThread(messageThreadId, principal.getActiveRoleId());
		result = messagesOfUserInThread > 0;

		return result;
	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "userAccount.identity.name", "userAccount.identity.surname");
	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;

		Collection<Authenticated> result;
		Model model;

		model = request.getModel();
		result = this.repository.findManyAuthenticatedByThreadId(model.getInteger("id"));

		return result;
	}

}
