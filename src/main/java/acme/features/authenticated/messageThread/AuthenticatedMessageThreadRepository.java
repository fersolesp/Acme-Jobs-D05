
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select p.messageThread from Participant p where p.authenticated.id=?1")
	Collection<MessageThread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select p from Participant p where p.messageThread.id=?1 and p.authenticated.id=?2")
	Participant findParticipantInThread(int messageThreadId, int authenticatedId);
}
