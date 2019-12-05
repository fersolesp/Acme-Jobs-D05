
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id=?1")
	Collection<Duty> findManyById(int descriptorId);

	@Query("select d from Duty d where d.id=?1")
	Duty findOneDutyById(int id);

	@Query("select j from Job j where j.descriptor.id=?1")
	Job findJobByDescriptor(int descriptorId);

	@Query("select j from Job j where j.descriptor.id = (select d.descriptor.id from Duty d where d.id=?1)")
	Job findJobByDutyId(int dutyId);
}
