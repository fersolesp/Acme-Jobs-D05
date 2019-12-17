
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.participants.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select m from Message m where m.id=?1")
	Message findOneMessageById(int id);

	@Query("select m from Message m where m.messageThread.id=?1")
	Collection<Message> findManyMessagesByThreadId(Integer messageThreadId);

	@Query("select p from Participant p where p.messageThread.id=?1 and p.authenticated.id=?2")
	Participant findParticipantInThread(int messageThreadId, int authenticatedId);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findOneMessageThreadById(int messageThreadId);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameter();

}
