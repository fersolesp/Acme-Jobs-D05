
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id=?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where (a.job.id=?1 and (a.status=1 or a.auditor.id=?2))")
	Collection<AuditRecord> findManyAuditRecordByJobId(int idJob, int idAuditor);

	@Query("select count(a) from AuditRecord a where a.id=?1 and (a.status=1 or a.auditor.id=?2)")
	int findPublishedOrOwnAuditRecord(int idAuditRecord, int idAuditor);

	@Query("select j.status from Job j where j.id=?1")
	int findIsJobPublished(int jobId);

}
