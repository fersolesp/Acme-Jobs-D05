
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a.justification from Application a where a.id = ?1")
	String findOneJustificationByApplicationId(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 and a.status=?2")
	Collection<Application> findManyByEmployerIdAndStatus(int activeRoleId, ApplicationStatus rejected);

}
