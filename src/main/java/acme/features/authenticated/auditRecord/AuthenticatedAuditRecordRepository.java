
package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id=?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where (a.job.id=?1 and a.status=1)")
	Collection<AuditRecord> findManyAuditRecordByJobId(int idJob);

	@Query("select j from Job j where j.id=?1")
	Job findJob(int jobId);

}
