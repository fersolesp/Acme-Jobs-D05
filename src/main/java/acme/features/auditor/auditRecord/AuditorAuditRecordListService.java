
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListService implements AbstractListService<Auditor, AuditRecord> {

	// Internal state -----------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	// AbstractListService<Auditor, Job> interface -----------------------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");
		return this.repository.findIsJobPublished(jobId) > 0;
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
		int idAuditor = request.getPrincipal().getActiveRoleId();

		result = this.repository.findManyAuditRecordByJobId(idJob, idAuditor);

		return result;
	}

}
