
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		boolean result;
		int messageThreadId;
		Participant participant;
		Principal principal = request.getPrincipal();
		Message messageToSee;

		messageToSee = this.repository.findOneMessageById(request.getModel().getInteger("id"));
		messageThreadId = messageToSee.getMessageThread().getId();
		participant = this.repository.findParticipantInThread(messageThreadId, principal.getActiveRoleId());
		result = participant != null;

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "authenticated.userAccount.username", "messageThread.id", "messageThread.title");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageById(id);

		return result;
	}

}
