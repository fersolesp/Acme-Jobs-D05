
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.referenceNumber = ?1")
	Application findOneApplicationByReferenceNumber(String reference);

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerId);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

	@Query("select count(a) from Application a where a.worker.id = ?1 and a.job.id = ?2")
	int findApplicationByIds(int workerId, int jobId);

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select w from Worker w where w.id = ?1")
	Worker findOneWorkerById(int id);
}
