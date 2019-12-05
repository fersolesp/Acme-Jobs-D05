
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

public interface WorkerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id=?1")
	Collection<Duty> findManyById(int descriptorId);

	@Query("select d from Duty d where d.id=?1")
	Duty findOneDutyById(int id);

	@Query("select count(a) from Application a where a.job.descriptor.id=?1 and a.worker.id=?2")
	int findApplicationsOfAJob(int descriptorId, int workerId);

	@Query("select count(a) from Application a where a.job.descriptor.id=(select d.descriptor.id from Duty d where d.id=?1) and a.worker.id=?2")
	int findApplicationsOfADuty(int dutyId, int workerId);

}
