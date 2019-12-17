
package acme.features.administrator.chart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	@Autowired
	AdministratorChartRepository repository;


	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "CommonSectorsOfCompanies", "CommonSectorsOfInvestors", "investorSector", "companySector", "ratioPublishedJobs", "ratioDraftJobs", "ratioPendingApplications", "ratioAcceptedApplications", "ratioRejectedApplications",
			"pendingApplicationsInFourWeeks", "acceptedApplicationsInFourWeeks", "rejectedApplicationsInFourWeeks", "zerosToActualDate");
	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		assert request != null;
		Chart result = new Chart();

		result.setCommonSectorsOfCompanies(this.repository.findCommonSectorsOfCompanies());
		result.setCommonSectorsOfInvestors(this.repository.findCommonSectorsOfInvestors());
		result.setInvestorSector(this.repository.findInvestorSector());
		result.setCompanySector(this.repository.findCompanySector());

		result.setRatioAcceptedApplications(this.repository.findRatioAcceptedApplications());
		result.setRatioPendingApplications(this.repository.findRatioPendingApplications());
		result.setRatioRejectedApplications(this.repository.findRatioRejectedApplications());
		result.setRatioDraftJobs(this.repository.findRatioDraftJob());
		result.setRatioPublishedJobs(this.repository.findRatioPublishedJob());

		result.setAcceptedApplicationsInFourWeeks(this.repository.findAcceptedAppsInFourWeeks());
		result.setPendingApplicationsInFourWeeks(this.repository.findPendingAppsInFourWeeks());
		result.setRejectedApplicationsInFourWeeks(this.repository.findRejectedAppsInFourWeeks());

		Calendar calendar = new GregorianCalendar();
		String[] dates = new String[28];
		for (int i = 0; i < 28; i++) {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
			dates[i] = format.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}

		result.setZerosToActualDate(dates);

		return result;
	}

}
