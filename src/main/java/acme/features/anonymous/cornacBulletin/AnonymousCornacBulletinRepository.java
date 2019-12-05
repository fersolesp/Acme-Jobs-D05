
package acme.features.anonymous.cornacBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cornacBulletin.CornacBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCornacBulletinRepository extends AbstractRepository {

	@Query("select s from CornacBulletin s")
	Collection<CornacBulletin> findMany();

}
