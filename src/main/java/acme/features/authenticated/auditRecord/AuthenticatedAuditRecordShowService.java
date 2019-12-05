
package acme.features.authenticated.auditRecord;

import java.util.Calendar;
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
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditRecordShowService implements AbstractShowService<Authenticated, AuditRecord> {

	@Autowired
	private AuthenticatedAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		int auditRecordId = request.getModel().getInteger("id");
		AuditRecord auditRecord = this.repository.findOneAuditRecordById(auditRecordId);
		Job job = auditRecord.getJob();
		Calendar calendar = new GregorianCalendar();
		Date minimumDeadLine = calendar.getTime();

		return job.getStatus() == Status.PUBLISHED && job.getDeadline().after(minimumDeadLine) && auditRecord.getStatus() == Status.PUBLISHED;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "moment", "body", "auditor.userAccount.username");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		AuditRecord result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findOneAuditRecordById(id);

		return result;
	}

}
