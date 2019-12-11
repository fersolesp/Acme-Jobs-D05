
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		Principal principal;
		principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		AuditRecord auditRecord = this.repository.findOneAuditRecordById(id);
		Auditor auditor = auditRecord.getAuditor();

		return principal.getActiveRoleId() == auditor.getId();
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "job.id");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "job.id", "moment", "auditor", "status");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord auditRecord;
		int auditRecordId;

		auditRecordId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOneAuditRecordById(auditRecordId);
		return auditRecord;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

}