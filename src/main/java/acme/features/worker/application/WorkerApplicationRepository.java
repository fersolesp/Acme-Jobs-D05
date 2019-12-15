
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerId);

	@Query("select cp from CustomisationParameter cp")
	Collection<CustomisationParameter> findCustomisationParameters();

	@Query("select count (a) from Application a where a.worker.id = ?1 && a.job.id = ?2")
	int findApplicationByIds(int workerId, int jobId);
}
