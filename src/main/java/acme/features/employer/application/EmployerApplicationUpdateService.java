
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Job job;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		job = application.getJob();
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId() && application.getStatus().equals(ApplicationStatus.PENDING);

		return result;
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

		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "statement");
		request.unbind(entity, model, "skills", "qualifications", "job", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("status")) {
			if (request.getModel().getString("status").equals("REJECTED")) {
				boolean condition = request.getModel().getString("justification").isEmpty();
				errors.state(request, !condition, "justification", "employer.application.error.label.justification");
			}
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		String justification = request.getModel().getString("justification");

		if (request.getModel().getString("status").equals("PENDING")) {
			entity.setStatus(ApplicationStatus.PENDING);
			entity.setJustification("");
		} else if (request.getModel().getString("status").equals("ACCEPTED")) {
			entity.setStatus(ApplicationStatus.ACCEPTED);
			entity.setJustification(justification);
		} else if (request.getModel().getString("status").equals("REJECTED")) {
			entity.setStatus(ApplicationStatus.REJECTED);
			entity.setJustification(justification);
		}
		this.repository.save(entity);
	}

}
