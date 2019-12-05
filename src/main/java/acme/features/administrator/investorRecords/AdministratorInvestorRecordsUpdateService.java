
package acme.features.administrator.investorRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInvestorRecordsUpdateService implements AbstractUpdateService<Administrator, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInvestorRecordsRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestorRecords> request, final InvestorRecords entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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
		assert request != null;

		InvestorRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestorRecords> request, final InvestorRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<InvestorRecords> request, final InvestorRecords entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
