
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select count(a) from Application a where a.job.id=?1 and a.worker.id=?2")
	int findApplicationsOfAJob(int jobId, int workerId);

	@Query("select j from Job j where j.deadline> current_date() AND j.status=1")
	Collection<Job> findMany();

	@Query("select count(a) from Application a where a.worker.id = ?1 and a.job.id = ?2")
	int findApplicationByIds(int workerId, int jobId);

}
