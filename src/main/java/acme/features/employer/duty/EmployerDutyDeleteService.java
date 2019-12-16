
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerDutyDeleteService implements AbstractDeleteService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Integer dutyId = request.getModel().getInteger("id");

		Principal principal;
		principal = request.getPrincipal();
		Integer EmployerId = this.repository.findJobByDutyId(dutyId);

		return principal.getActiveRoleId() == EmployerId;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "amountTime", "descriptor.id");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int descriptorId = entity.getDescriptor().getId();
		Boolean condition = this.repository.findJobStatusByDescriptorId(descriptorId) == Status.PUBLISHED;
		errors.state(request, !condition, "amountTime", "employer.duty.error.label.jobPublished");
	}

	@Override
	public void delete(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
