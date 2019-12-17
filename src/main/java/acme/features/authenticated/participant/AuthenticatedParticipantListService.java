
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		Participant participant;
		participant = this.repository.findParticipantInThread(request.getModel().getInteger("id"), request.getPrincipal().getActiveRoleId());

		return participant.isCreator();
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creator", "authenticated", "authenticated.userAccount.username", "messageThread", "messageThread.title", "messageThread.id");
	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;

		Collection<Participant> result;

		result = this.repository.findManyByMessageThreadId(request.getModel().getInteger("id"));

		return result;
	}

}
