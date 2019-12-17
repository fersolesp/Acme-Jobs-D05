
package acme.features.auditor.auditRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");

		Job job = this.repository.findJobById(jobId);
		Calendar calendar;
		calendar = new GregorianCalendar();
		Date time = calendar.getTime();
		return job.getStatus() == Status.PUBLISHED && job.getDeadline().after(time);
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "job.id", "job", "auditor", "status");
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		Principal principal;
		int auditorId;
		int jobId;
		Job job;
		Auditor auditor;

		result = new AuditRecord();

		principal = request.getPrincipal();
		auditorId = principal.getActiveRoleId();
		auditor = this.repository.findAuditorById(auditorId);

		jobId = request.getModel().getInteger("id");
		job = this.repository.findJobById(jobId);

		result.setAuditor(auditor);
		result.setJob(job);
		result.setStatus(Status.DRAFT);
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
