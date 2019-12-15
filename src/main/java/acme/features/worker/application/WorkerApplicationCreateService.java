
package acme.features.worker.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.applications.Application;
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

public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Worker worker;
		int workerId;
		Job job;
		int jobId;
		int numberOfApplicationsByIds;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		worker = application.getWorker();
		workerId = worker.getId();
		job = application.getJob();
		jobId = job.getId();
		numberOfApplicationsByIds = this.repository.findApplicationByIds(workerId, jobId);
		result = numberOfApplicationsByIds == 0;

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
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

		return application;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<CustomisationParameter> cp;
		String[] spamWords;
		Double SpamThreshold;

		if (!errors.hasErrors("statement")) {

			cp = new ArrayList<>(this.repository.findCustomisationParameters());
			spamWords = cp.get(0).getSpamWordList().split(",");
			SpamThreshold = cp.get(0).getSpamThreshold();

			String statement = entity.getStatement().toLowerCase();
			Integer numberOfSpamWords = 0;

			for (String word : spamWords) {
				if (statement.contains(word.trim())) {
					numberOfSpamWords++;
				}
			}

			boolean isSpam = numberOfSpamWords * 100 / statement.length() > SpamThreshold;

			errors.state(request, !isSpam, "statement", "worker.application.error.spam");
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
