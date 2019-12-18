
package acme.features.worker.auditRecord;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerAuditRecordListService implements AbstractListService<Worker, AuditRecord> {

	// Internal state -----------------------------

	@Autowired
	WorkerAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("id");
		int workerId = request.getPrincipal().getActiveRoleId();
		int numberApplications = this.repository.findApplicationsOfAJob(jobId, workerId);

		Job job = this.repository.findOneJobById(jobId);
		Calendar calendar = new GregorianCalendar();
		Date minimumDeadLine = calendar.getTime();

		return numberApplications > 0 || job.getStatus() == Status.PUBLISHED && job.getDeadline().after(minimumDeadLine);
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
