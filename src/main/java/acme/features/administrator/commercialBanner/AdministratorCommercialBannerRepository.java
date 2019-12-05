
package acme.features.administrator.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.commercialBanners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCommercialBannerRepository extends AbstractRepository {

	@Query("select a from CommercialBanner a where a.id = ?1")
	CommercialBanner findOneById(int id);

	@Query("select a from CommercialBanner a")
	Collection<CommercialBanner> findManyAll();
}
