
package acme.features.worker.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	@Autowired
	WorkerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("id");
		int workerId = request.getPrincipal().getActiveRoleId();
		int numberApplications = this.repository.findApplicationsOfAJob(jobId, workerId);

		Job job = this.repository.findOneJobById(jobId);
		Calendar calendar = new GregorianCalendar();
		Date minimumDeadLine = calendar.getTime();

		return numberApplications > 0 || job.getStatus() == Status.PUBLISHED && job.getDeadline().after(minimumDeadLine);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int numberOfApplicationsByIds = this.repository.findApplicationByIds(request.getPrincipal().getActiveRoleId(), request.getModel().getInteger("id"));

		model.setAttribute("botonVisible", numberOfApplicationsByIds);

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "status", "descriptor.description", "descriptor", "id");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}
}
