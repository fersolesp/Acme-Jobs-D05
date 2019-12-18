
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerApplicationListStatusService implements AbstractListService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "statement");
	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;

		Collection<Application> result;
		Principal principal;
		ApplicationStatus stat = null;
		principal = request.getPrincipal();

		if (request.getModel().getString("stat").equals("REJECTED")) {
			stat = ApplicationStatus.REJECTED;
		} else if (request.getModel().getString("stat").equals("ACCEPTED")) {
			stat = ApplicationStatus.ACCEPTED;
		} else if (request.getModel().getString("stat").equals("PENDING")) {
			stat = ApplicationStatus.PENDING;
		}

		result = this.repository.findManyByEmployerIdAndStatus(principal.getActiveRoleId(), stat);

		return result;
	}

}
