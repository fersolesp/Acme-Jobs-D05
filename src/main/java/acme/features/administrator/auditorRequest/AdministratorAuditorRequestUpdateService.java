
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.auditorRequests.AuditorRequestStatus;
import acme.features.administrator.auditor.AdministratorAuditorCreateService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestUpdateService implements AbstractUpdateService<Administrator, AuditorRequest> {

	@Autowired
	private AdministratorAuditorRequestRepository	repository;

	@Autowired
	private AdministratorAuditorCreateService		auditorService;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement", "status");
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals(AuditorRequestStatus.ACCEPTED)) {
			this.auditorService.createFromRequest(entity);
		}

		this.repository.save(entity);
	}

}
