
package acme.features.auditor.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorDutyShowService implements AbstractShowService<Auditor, Duty> {

	@Autowired
	AuditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		int dutyId = request.getModel().getInteger("id");
		return this.repository.findIfJobIsPublishedByDutyId(dutyId) > 0;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "amountTime");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int jobId = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(jobId);

		return result;
	}

}
