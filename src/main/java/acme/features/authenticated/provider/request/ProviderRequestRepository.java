
package acme.features.authenticated.provider.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestRepository extends AbstractRepository {

	@Query("select a from Request a where a.deadline>current_time()")
	Collection<Request> findManyAll();

	@Query("select a from Request a where a.ticker= ?1")
	Request findOneRequestByTicker(String ticker);
}
