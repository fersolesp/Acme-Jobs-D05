
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		Participant participant;
		MessageThread messageThread;

		messageThread = this.repository.findOneParticipantById(request.getModel().getInteger("id")).getMessageThread();
		participant = this.repository.findParticipantInThread(messageThread.getId(), request.getPrincipal().getActiveRoleId());

		return participant.isCreator();
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("isItMe", this.repository.findOneParticipantById(request.getModel().getInteger("id")).getAuthenticated().getId() == request.getPrincipal().getActiveRoleId());
		request.unbind(entity, model, "creator", "authenticated.userAccount.username", "messageThread.title");
	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneParticipantById(id);

		return result;
	}

}
