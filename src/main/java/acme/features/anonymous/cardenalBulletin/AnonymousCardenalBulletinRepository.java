
package acme.features.anonymous.cardenalBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cardenalBulletins.CardenalBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCardenalBulletinRepository extends AbstractRepository {

	@Query("select c from CardenalBulletin c")
	Collection<CardenalBulletin> findMany();
}
