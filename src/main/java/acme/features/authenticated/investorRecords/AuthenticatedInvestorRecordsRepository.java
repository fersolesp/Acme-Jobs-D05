
package acme.features.authenticated.investorRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestorRecordsRepository extends AbstractRepository {

	@Query("select a from InvestorRecords a where a.id = ?1")
	InvestorRecords findOneById(int id);

	@Query("select a from InvestorRecords a")
	Collection<InvestorRecords> findMany();

}
