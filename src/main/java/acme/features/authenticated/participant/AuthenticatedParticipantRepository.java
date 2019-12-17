
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findAuthenticatedByUsername(String username);

	@Query("select p from Participant p where p.messageThread.id = ?1")
	Collection<Participant> findManyByMessageThreadId(int messageThreadId);

	@Query("select p from Participant p where p.id=?1")
	Participant findOneParticipantById(int id);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findOneMessageThreadById(int messageThreadId);

	@Query("select p from Participant p where p.messageThread.id=?1 and p.authenticated.id=?2")
	Participant findParticipantInThread(int messageThreadId, int authenticatedId);

}
