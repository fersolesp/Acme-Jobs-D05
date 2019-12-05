
package acme.features.anonymous.blascoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.blascoBulletins.BlascoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBlascoBulletinRepository extends AbstractRepository {

	@Query("select s from BlascoBulletin s")
	Collection<BlascoBulletin> findMany();
}
