
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id=?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id=?1")
	Employer findEmployerById(int employerId);

	@Query("select d from Duty d where d.descriptor.id=?1")
	Collection<Duty> findDutiesByDescriptorId(int descriptorId);

	@Query("select a from Application a where a.job.id=?1")
	Collection<Application> findApplicationsByJobId(int jobId);

	@Query("select count(a) from Application a where a.job.id=?1")
	int findNumberOfApplicationsByJobId(int jobId);

	@Query("select au from AuditRecord au where au.job.id=?1")
	Collection<AuditRecord> findAuditRecordsByJobId(int jobId);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

	@Query("select sum (d.amountTime) from Duty d where d.descriptor.id=?1")
	int findSumOfAmountTimeByDescriptorId(int descriptorId);

	@Query("select j from Job j where j.reference=?1")
	Job findOneJobByReference(String reference);

}
