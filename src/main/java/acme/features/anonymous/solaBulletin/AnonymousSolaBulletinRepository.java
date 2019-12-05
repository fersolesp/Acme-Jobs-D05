
package acme.features.anonymous.solaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.solaBulletins.SolaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousSolaBulletinRepository extends AbstractRepository {

	@Query("select s from SolaBulletin s")
	Collection<SolaBulletin> findMany();

}
