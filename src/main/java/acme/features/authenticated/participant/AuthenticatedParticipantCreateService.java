
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		Participant participant;

		participant = this.repository.findParticipantInThread(request.getModel().getInteger("messageThread.id"), request.getPrincipal().getActiveRoleId());
		return participant.isCreator();
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated authenticated;
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		authenticated = this.repository.findAuthenticatedByUsername(request.getModel().getString("authenticated.userAccount.username"));
		entity.setAuthenticated(authenticated);
		entity.setMessageThread(messageThread);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creator", "authenticated", "messageThread", "messageThread.title", "messageThread.id");
	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		Participant result;

		result = new Participant();
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated authenticated;
		authenticated = this.repository.findAuthenticatedByUsername(request.getModel().getString("authenticated.userAccount.username"));

		errors.state(request, authenticated != null, "authenticated.userAccount.username", "authenticated.participant.error.invalidUsername");

		if (authenticated != null) {
			errors.state(request, this.repository.findParticipantInThread(request.getModel().getInteger("messageThread.id"), authenticated.getId()) == null, "authenticated.userAccount.username", "authenticated.participant.error.duplicated");
		}
	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {
		assert entity != null;

		this.repository.save(entity);

	}

	public void createFromMessageThread(final int authenticatedId, final MessageThread messageThread) {
		Participant participant = new Participant();
		participant.setAuthenticated(this.repository.findAuthenticatedById(authenticatedId));
		participant.setCreator(true);
		participant.setMessageThread(messageThread);

		this.repository.save(participant);

	}

}
