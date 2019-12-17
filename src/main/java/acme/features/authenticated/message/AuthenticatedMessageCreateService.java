
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
		Integer cuentaSpamTitle = 0;
		Integer cuentaSpamTag = 0;
		Integer cuentaSpamBody = 0;
		Double limitePalabrasSpamPermitidas = Double.valueOf(entity.getBody().split(" ").length) * cp.getSpamThreshold() / 100.0;

		listaCustomisationParameter = cp.getSpamWordList().split(",");

		if (!errors.hasErrors("title") || !errors.hasErrors("tags") || !errors.hasErrors("body")) {
			for (String s : listaCustomisationParameter) {

				String mensajeParcialTitle = entity.getTitle().toLowerCase();
				int indiceTitle = mensajeParcialTitle.indexOf(s);

				String mensajeParcialTag = entity.getTags().toLowerCase();
				int indiceTag = mensajeParcialTag.indexOf(s);

				String mensajeParcialBody = entity.getBody().toLowerCase();
				int indice = mensajeParcialBody.indexOf(s);

				while (indiceTitle != -1) {
					cuentaSpamTitle++;
					mensajeParcialTitle = mensajeParcialTitle.substring(indiceTitle + 1);
					indiceTitle = mensajeParcialTitle.indexOf(s);
				}

				while (indiceTag != -1) {
					cuentaSpamTag++;
					mensajeParcialTag = mensajeParcialTag.substring(indiceTag + 1);
					indiceTag = mensajeParcialTag.indexOf(s);
				}

				while (indice != -1) {
					cuentaSpamBody++;
					mensajeParcialBody = mensajeParcialBody.substring(indice + 1);
					indice = mensajeParcialBody.indexOf(s);
				}

				errors.state(request, cuentaSpamTitle <= limitePalabrasSpamPermitidas, "title", "authenticated.message.error.limiteSpamTitle");
				errors.state(request, cuentaSpamTag <= limitePalabrasSpamPermitidas, "tags", "authenticated.message.error.limiteSpamTag");
				errors.state(request, cuentaSpamBody <= limitePalabrasSpamPermitidas, "body", "authenticated.message.error.limiteSpamBody");

				if (cuentaSpamBody > limitePalabrasSpamPermitidas || cuentaSpamTag > limitePalabrasSpamPermitidas || cuentaSpamTitle > limitePalabrasSpamPermitidas) {
					break;
				}
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
