
package acme.features.authenticated.announcement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("select a from Announcement a where a.id= ?1")
	Announcement findOneById(int id);

	@Query("select a from Announcement a where 365*YEAR(current_time())+30*MONTH(current_time())+DAY(current_time())-365*YEAR(a.moment)-30*MONTH(a.moment)-DAY(a.moment)<30")
	Collection<Announcement> findManyAll();
}
