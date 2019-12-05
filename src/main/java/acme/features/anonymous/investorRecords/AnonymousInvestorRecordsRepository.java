
package acme.features.anonymous.investorRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousInvestorRecordsRepository extends AbstractRepository {

	@Query("select a from InvestorRecords a where a.id = ?1")
	InvestorRecords findOneById(int id);

	@Query("select a from InvestorRecords a")
	Collection<InvestorRecords> findMany();

	@Query("select a from InvestorRecords a where(a.stars=5)")
	Collection<InvestorRecords> findTopOnes();

}
