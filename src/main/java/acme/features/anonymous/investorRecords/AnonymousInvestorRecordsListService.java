
package acme.features.anonymous.investorRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousInvestorRecordsListService implements AbstractListService<Anonymous, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousInvestorRecordsRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<InvestorRecords> findMany(final Request<InvestorRecords> request) {

		assert request != null;

		Collection<InvestorRecords> result = null;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<InvestorRecords> request, final InvestorRecords entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investorName", "workSector", "investingStatement", "stars");

	}

}
