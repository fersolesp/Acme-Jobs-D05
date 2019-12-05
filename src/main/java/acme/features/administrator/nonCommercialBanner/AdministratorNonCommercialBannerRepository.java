
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.nonCommercialBanners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select a from NonCommercialBanner a where a.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select a from NonCommercialBanner a")
	Collection<NonCommercialBanner> findManyAll();
}
