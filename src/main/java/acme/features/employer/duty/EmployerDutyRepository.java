
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id=?1")
	Collection<Duty> findManyById(int descriptorId);

	@Query("select d from Duty d where d.id=?1")
	Duty findOneDutyById(int id);

	@Query("select e.id from Employer e where e.id IN (select j.employer.id from Job j where j.descriptor.id=?1)")
	Integer findEmployerByDescriptor(int descriptorId);

	@Query("select j.employer.id from Job j where j.descriptor.id = (select d.descriptor.id from Duty d where d.id=?1)")
	Integer findJobByDutyId(int dutyId);

	@Query("select d from Descriptor d where d.id=?1")
	Descriptor findDescriptorById(int descriptorId);

	@Query("select d from Duty d where d.descriptor.id=?1")
	Collection<Duty> findDutiesByDescriptorId(int descriptorId);

	@Query("select sum (d.amountTime) from Duty d where d.descriptor.id=?1")
	int findSumOfAmountTimeByDescriptorId(int descriptorId);

	@Query("select j.status from Job j where j.descriptor.id=?1")
	Status findJobStatusByDescriptorId(int descriptorId);

	@Query("select j.employer.id from Job j where j.descriptor.id=?1")
	Integer findJobByDescriptorId(Integer descriptorId);
}
