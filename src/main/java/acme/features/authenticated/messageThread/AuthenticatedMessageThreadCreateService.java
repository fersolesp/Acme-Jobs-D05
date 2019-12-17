
package acme.features.authenticated.messageThread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.features.authenticated.participant.AuthenticatedParticipantCreateService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository	repository;

	@Autowired
	AuthenticatedParticipantCreateService	participantService;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		Authenticated authenticated;

		authenticated = this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId());

		return authenticated != null;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "moment");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		MessageThread result;

		result = new MessageThread();

		if (request.getModel().hasAttribute("title")) {
			result.setTitle(request.getModel().getString("title"));
		}

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

		Principal principal;

		principal = request.getPrincipal();

		this.participantService.createFromMessageThread(principal.getActiveRoleId(), entity);

	}

}
