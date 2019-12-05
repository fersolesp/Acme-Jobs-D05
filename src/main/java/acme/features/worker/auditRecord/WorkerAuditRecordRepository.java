
package acme.features.worker.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id=?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.job.id=?1 and a.status=1")
	Collection<AuditRecord> findManyAuditRecordByJobId(int idJob);

	@Query("select count(a) from Application a where a.job.id=?1 and a.worker.id=?2")
	int findApplicationsOfAJob(int idJob, int idWorker);

	@Query("select count(a) from Application a where (a.worker.id=?1 and a.job.id=(select ar.job.id from AuditRecord ar where ar.id=?2 and ar.status=1))")
	int findApplicationsOfAJobAppliedByAuditRecordId(int idWorker, int auditRecordId);

}
