
package acme.features.authenticated.auditorRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select count(a) from AuditorRequest a where a.authenticated.userAccount.id = ?1")
	Integer numberOfAuditorRequestByUserAccountId(int id);

	@Query("select a from AuditorRequest a where a.authenticated.userAccount.id = ?1")
	AuditorRequest findOneAuditorRequestByUserAccountId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByUserAccountId(int id);

	@Query("select count(a) from Auditor a where a.userAccount.id = ?1")
	int findNumberOfAuditorByUserAccountId(int id);

	@Query("select count(a) from AuditorRequest a where a.authenticated.userAccount.id = ?1 and a.status=2")
	int numberOfAuditorRequestPendingByUserAccountId(int userAccountId);
}
