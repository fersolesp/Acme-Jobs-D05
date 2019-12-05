
package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.nonCommercialBanners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select a from NonCommercialBanner a where a.id = ?1")
	NonCommercialBanner findOneNonCommercialBannerById(int id);

	@Query("select a from NonCommercialBanner a where a.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int sponsorId);
}
