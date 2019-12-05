
package acme.features.anonymous.pradasBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.pradasBulletin.PradasBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPradasBulletinRepository extends AbstractRepository {

	@Query("select a from PradasBulletin a")
	Collection<PradasBulletin> findMany();

}
