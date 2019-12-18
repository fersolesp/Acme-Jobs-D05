
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	// Internal state --------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractDeleteService<Employer, Job> interface --------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		Boolean hasNotApplications = this.repository.findApplicationsByJobId(jobId).size() < 1;

		result = employer.getUserAccount().getId() == principal.getAccountId() && hasNotApplications;

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title");
		request.unbind(entity, model, "deadline", "salary", "moreInfo", "descriptor", "descriptor.description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		int id;
		Job result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		int descriptorId = entity.getDescriptor().getId();
		int jobId = entity.getId();

		Collection<AuditRecord> auditRecords = this.repository.findAuditRecordsByJobId(jobId);
		Collection<Duty> duties = this.repository.findDutiesByDescriptorId(descriptorId);

		this.repository.deleteAll(duties);
		this.repository.deleteAll(auditRecords);
		this.repository.delete(entity);

	}

}
