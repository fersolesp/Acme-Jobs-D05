
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where(select j.descriptor.id from Job j where j.id=?1 and j.status=1)=d.descriptor.id")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select d from Duty d where d.id=?1")
	Duty findOneDutyById(int id);

	@Query("select j.status from Job j where j.id=?1")
	int findIfJobIsPublished(int jobId);

	@Query("select j.status from Job j where j.descriptor.id=(select d.descriptor.id from Duty d where d.id=?1)")
	int findIfJobIsPublishedByDutyId(int dutyId);

}
