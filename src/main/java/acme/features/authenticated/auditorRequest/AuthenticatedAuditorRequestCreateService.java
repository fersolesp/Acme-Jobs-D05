
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.auditorRequests.AuditorRequestStatus;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		boolean result;
		int numberOfAuthenticated;
		Principal principal = request.getPrincipal();

		numberOfAuthenticated = this.repository.findNumberOfAuditorByUserAccountId(principal.getAccountId());
		result = numberOfAuthenticated < 1;
		return result;
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

		request.unbind(entity, model, "firm", "responsabilityStatement", "status", "authenticated");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Principal principal;
		int userAccountId;
		Authenticated authenticated;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		authenticated = this.repository.findOneAuthenticatedByUserAccountId(userAccountId);

		result = new AuditorRequest();
		result.setAuthenticated(authenticated);
		result.setStatus(AuditorRequestStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		errors.state(request, !(this.repository.numberOfAuditorRequestPendingByUserAccountId(userAccountId) > 0), "responsabilityStatement", "authenticated.auditor-request.error.pending");

		boolean isConfirmed;
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.auditorRequest.error.label.confirm");

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
