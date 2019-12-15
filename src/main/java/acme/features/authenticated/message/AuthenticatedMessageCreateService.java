
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Participant participant;

		participant = this.repository.findParticipantInThread(request.getModel().getInteger("messageThread.id"), request.getPrincipal().getActiveRoleId());
		return participant != null;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "authenticated.userAccount.username", "messageThread.id", "messageThread.title");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;

		result = new Message();
		Authenticated authenticated;
		MessageThread messageThread;

		authenticated = this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result.setAuthenticated(authenticated);
		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomisationParameter cp = this.repository.findCustomisationParameter();
		String[] listaCustomisationParameter;
		Integer cuenta = 0;
		Double limitePalabrasSpamPermitidas = Double.valueOf(entity.getBody().split(" ").length) * cp.getSpamThreshold() / 100.0;

		listaCustomisationParameter = cp.getSpamWordList().split(",");

		for (String s : listaCustomisationParameter) {
			String mensajeParcial = entity.getBody();
			int indice = mensajeParcial.indexOf(s);
			while (indice != -1) {
				cuenta++;
				mensajeParcial = mensajeParcial.substring(indice + 1);
				indice = mensajeParcial.indexOf(s);
			}
			errors.state(request, cuenta <= limitePalabrasSpamPermitidas, "body", "authenticated.message.error.limiteSpam");
			if (cuenta > limitePalabrasSpamPermitidas) {
				break;
			}
		}

		boolean isConfirmed;
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.message.error.label.confirm");
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert entity != null;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
