
package acme.features.authenticated.duty;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutyListMineService implements AbstractListService<Authenticated, Duty> {

	@Autowired
	AuthenticatedDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Integer descriptorId;
		descriptorId = request.getModel().getInteger("id");
		Job job = this.repository.findJobByDescriptor(descriptorId);

		Calendar calendar = new GregorianCalendar();
		Date minimumDeadLine = calendar.getTime();

		return job.getStatus() == Status.PUBLISHED && job.getDeadline().after(minimumDeadLine);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> result;
		int descriptorId = request.getModel().getInteger("id");
		result = this.repository.findManyById(descriptorId);

		return result;
	}

}
