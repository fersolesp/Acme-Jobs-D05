
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	private AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfAnnouncements", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "maxRewardOfActiveRequests", "minRewardOfActiveRequests", "avgRewardOfActiveRequests",
			"standardDeviationRewardOfActiveRequests", "maxRewardOfActiveOffers", "minRewardOfActiveOffers", "avgRewardOfActiveOffers", "standardDeviationRewardOfActiveOffers", "avgJobsPerEmployer", "avgApplicationsPerEmployer",
			"avgApplicationsPerWorker");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Dashboard result = new Dashboard();

		result.setTotalNumberOfAnnouncements(this.repository.findTotalNumberOfAnnouncements());
		result.setTotalNumberOfCompanyRecords(this.repository.findTotalNumberOfCompanyRecords());
		result.setTotalNumberOfInvestorRecords(this.repository.findTotalNumberOfInvestorRecords());

		result.setMaxRewardOfActiveRequests((Double) this.repository.findMaxRewardOfActiveRequests()[0]);
		result.setMinRewardOfActiveRequests((Double) this.repository.findMinRewardOfActiveRequests()[0]);
		result.setAvgRewardOfActiveRequests((Double) this.repository.findAvgRewardOfActiveRequests()[0]);
		result.setStandardDeviationRewardOfActiveRequests((Double) this.repository.findStandardDeviationRewardOfActiveRequests()[0]);

		result.setMaxRewardOfActiveOffers((Double) this.repository.findMaxRewardOfActiveOffers()[0]);
		result.setMinRewardOfActiveOffers((Double) this.repository.findMinRewardOfActiveOffers()[0]);
		result.setAvgRewardOfActiveOffers((Double) this.repository.findAvgRewardOfActiveOffers()[0]);
		result.setStandardDeviationRewardOfActiveOffers((Double) this.repository.findStandardDeviationRewardOfActiveOffers()[0]);

		result.setAvgApplicationsPerEmployer((Double) this.repository.findAvgApplicationsPerEmployer()[0]);
		result.setAvgJobsPerEmployer((Double) this.repository.findAvgJobsPerEmployer()[0]);
		result.setAvgApplicationsPerWorker((Double) this.repository.findAvgApplicationsPerWorker()[0]);

		return result;
	}

}
