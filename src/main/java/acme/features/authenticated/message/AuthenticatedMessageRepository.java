
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id=?1")
	Message findOneMessageById(int id);

	@Query("select m from Message m where m.messageThread.id=?1")
	Collection<Message> findManyMessagesByThreadId(Integer messageThreadId);

	@Query("select count(m) from Message m where m.messageThread.id=(select m.messageThread.id from Message m where m.id=?1) and m.authenticated.id=?2")
	Integer findNumberOfMessagesOfUserInThreadByMessageGiven(int messageId, int authenticatedId);

	@Query("select count(m) from Message m where m.messageThread.id=?1 and m.authenticated.id=?2")
	Integer findNumberOfMessagesOfUserInThread(int messageThreadId, int authenticatedId);

}
