
package acme.features.authenticated.investorRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestorRecordsShowService implements AbstractShowService<Authenticated, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInvestorRecordsRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecords> request) {

		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestorRecords> request, final InvestorRecords entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investorName", "workSector", "investingStatement", "stars");
	}

	@Override
	public InvestorRecords findOne(final Request<InvestorRecords> request) {
		// TODO Auto-generated method stub
		assert request != null;

		InvestorRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
