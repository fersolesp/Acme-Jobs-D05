
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	// Internal state --------------------------------------

	@Autowired
	EmployerDutyRepository repository;


	// AbstractCreateService<Employer, Duty> interface --------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Integer descriptorId = request.getModel().getInteger("id");

		Principal principal;
		principal = request.getPrincipal();
		Integer EmployerId = this.repository.findJobByDescriptorId(descriptorId);

		return principal.getActiveRoleId() == EmployerId && this.repository.findJobStatusByDescriptorId(descriptorId) == Status.DRAFT;
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

		request.unbind(entity, model, "title", "description", "amountTime", "descriptor.id", "descriptor");
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result = new Duty();
		int descriptorId = request.getModel().getInteger("id");
		Descriptor descriptor = this.repository.findDescriptorById(descriptorId);
		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int descriptorId = request.getModel().getInteger("id");

		if (!errors.hasErrors("amountTime")) {
			int modelAmountTime = request.getModel().getInteger("amountTime");
			int sumOfAmountTimes;
			if (this.repository.findDutiesByDescriptorId(descriptorId).size() > 0) {
				sumOfAmountTimes = this.repository.findSumOfAmountTimeByDescriptorId(descriptorId);
				Boolean condition = modelAmountTime + sumOfAmountTimes <= 100;
				errors.state(request, condition, "amountTime", "employer.duty.error.label.amountTime");
			}
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		int descriptorId = request.getModel().getInteger("id");
		Descriptor descriptor = this.repository.findDescriptorById(descriptorId);
		entity.setDescriptor(descriptor);

		this.repository.save(entity);
	}

}
