
package acme.features.employer.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerAuditRecordListService implements AbstractListService<Employer, AuditRecord> {

	// Internal state -----------------------------

	@Autowired
	EmployerAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(jobId);

		Principal principal = request.getPrincipal();

		return principal.getActiveRoleId() == job.getEmployer().getId();
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "auditor.userAccount.username", "moment");
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;
		Collection<AuditRecord> result;
		int idJob = request.getModel().getInteger("id");

		result = this.repository.findManyAuditRecordByJobId(idJob);

		return result;
	}

}
