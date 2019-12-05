
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRepository extends AbstractRepository {

	@Query("select distinct m.authenticated from Message m where m.messageThread.id=?1")
	Collection<Authenticated> findManyAuthenticatedByThreadId(Integer messageThreadId);

	@Query("select count(m) from Message m where m.messageThread.id=?1 and m.authenticated.id=?2")
	Integer findNumberOfMessagesOfUserInThread(int messageThreadId, int authenticatedId);

}
