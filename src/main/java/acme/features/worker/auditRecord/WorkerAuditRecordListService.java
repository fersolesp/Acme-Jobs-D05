
package acme.features.worker.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
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

		int idWorker = request.getPrincipal().getActiveRoleId();
		int idJob = request.getModel().getInteger("id");

		int ar = this.repository.findApplicationsOfAJob(idJob, idWorker);
		return ar > 0;
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
