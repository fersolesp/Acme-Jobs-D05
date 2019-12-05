
package acme.features.authenticated.auditRecord;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditRecordListService implements AbstractListService<Authenticated, AuditRecord> {

	// Internal state -----------------------------

	@Autowired
	AuthenticatedAuditRecordRepository repository;


	// AbstractListService<Auditor, Job> interface -----------------------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findJob(jobId);

		Calendar calendar = new GregorianCalendar();
		Date minimumDeadLine = calendar.getTime();

		return job.getStatus() == Status.PUBLISHED && job.getDeadline().after(minimumDeadLine);
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
