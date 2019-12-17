
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		//		Integer id = request.getModel().getInteger("id");
		//		Job job = this.repository.findOneJobById(id);
		//		Calendar calendar = new GregorianCalendar();
		//		Date minimumDeadLine = calendar.getTime();

		//		return job.getStatus().equals(Status.PUBLISHED) && job.getDeadline().after(minimumDeadLine);

		return true;

	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "status", "statement", "skills", "qualifications");
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application application;
		application = new Application();
		application.setStatus(ApplicationStatus.PENDING);

		Integer id = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(id);
		application.setJob(job);

		application.setWorker(this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId()));

		return application;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("qualifications")) {

			int numberOfApplicationsByIds;
			numberOfApplicationsByIds = this.repository.findApplicationByIds(request.getPrincipal().getActiveRoleId(), request.getModel().getInteger("id"));

			errors.state(request, numberOfApplicationsByIds == 0, "qualifications", "worker.application.error.applicationRepetida");

		}

		CustomisationParameter cp = this.repository.findCustomisationParameters();
		String[] listaCustomisationParameter;
		Integer cuenta = 0;
		Double limitePalabrasSpamPermitidas = Double.valueOf(entity.getStatement().split(" ").length) * cp.getSpamThreshold() / 100.0;

		if (!errors.hasErrors("statement")) {

			listaCustomisationParameter = cp.getSpamWordList().split(",");

			for (String s : listaCustomisationParameter) {
				String mensajeParcial = entity.getStatement().toLowerCase();
				int indice = mensajeParcial.indexOf(s);
				while (indice != -1) {
					cuenta++;
					mensajeParcial = mensajeParcial.substring(indice + 1);
					indice = mensajeParcial.indexOf(s);
				}
				errors.state(request, cuenta <= limitePalabrasSpamPermitidas, "statement", "worker.application.error.spam");

				if (cuenta > limitePalabrasSpamPermitidas) {
					break;
				}
			}

		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Application> request, final Response<Application> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
