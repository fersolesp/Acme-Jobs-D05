
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state --------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractListService<Employer, Job> interface --------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title");
		request.unbind(entity, model, "deadline", "salary", "moreInfo", "descriptor", "descriptor.description", "descriptor.id");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		int id;
		Job result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		if (request.getModel().getString("status") == "DRAFT") {
			result.setStatus(Status.DRAFT);
		} else if (request.getModel().getString("status") == "PUBLISHED") {
			result.setStatus(Status.PUBLISHED);
		}

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isUnique, isEuro, isPublish;
		Date minimumDeadLine;
		Calendar calendar;

		int descriptorId = entity.getDescriptor().getId();

		CustomisationParameter cp;

		//--------REFERENCE--------------

		if (!errors.hasErrors("reference")) {
			if (!this.repository.findOneJobById(entity.getId()).getReference().equals(entity.getReference())) {
				isUnique = this.repository.findOneJobByReference(request.getModel().getString("reference")) != null;
				errors.state(request, !isUnique, "reference", "employer.job.error.label.unique");
			}
		}

		//---------DEADLINE---------------

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadLine = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadLine), "deadline", "employer.job.error.label.deadline");
		}

		//---------SALARY---------------

		if (!errors.hasErrors("salary")) {
			isEuro = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, isEuro, "salary", "employer.job.error.label.salary-currency");

		}

		//-----------STATUS------------//

		//-----------duties---------

		if (!errors.hasErrors("status")) {
			isPublish = request.getModel().getString("status").equals("PUBLISHED");
			if (this.repository.findDutiesByDescriptorId(descriptorId).size() > 0) {
				if (this.repository.findSumOfAmountTimeByDescriptorId(descriptorId) < 100) {
					entity.setStatus(Status.DRAFT);
					errors.state(request, !isPublish, "status", "employer.job.error.label.amountTime");
				}
			} else {
				entity.setStatus(Status.DRAFT);
				errors.state(request, !isPublish, "status", "employer.job.error.label.amountTime");
			}

			//-----------spam---------

			if (!errors.hasErrors("reference") && !errors.hasErrors("title") && !errors.hasErrors("descriptor.description") && isPublish) {

				cp = this.repository.findCustomisationParameters();
				String[] listaCustomisationParameter;
				Integer cuentaT = 0, cuentaD = 0;
				Double limitePalabrasSpamPermitidasTitle = Double.valueOf(entity.getTitle().split(" ").length) * cp.getSpamThreshold() / 100.0;
				Double limitePalabrasSpamPermitidasDescription = Double.valueOf(entity.getDescriptor().getDescription().split(" ").length) * cp.getSpamThreshold() / 100.0;

				listaCustomisationParameter = cp.getSpamWordList().split(",");

				for (String s : listaCustomisationParameter) {
					String mensajeParcialTitle = entity.getTitle().toLowerCase();
					String mensajeParcialDescription = entity.getDescriptor().getDescription().toLowerCase();

					int indiceT = mensajeParcialTitle.indexOf(s);
					int indiceD = mensajeParcialDescription.indexOf(s);

					while (indiceT != -1) {
						cuentaT++;
						mensajeParcialTitle = mensajeParcialTitle.substring(indiceT + 1);
						indiceT = mensajeParcialTitle.indexOf(s);
					}
					while (indiceD != -1) {
						cuentaD++;
						mensajeParcialDescription = mensajeParcialDescription.substring(indiceD + 1);
						indiceD = mensajeParcialDescription.indexOf(s);
					}
					errors.state(request, cuentaT <= limitePalabrasSpamPermitidasTitle, "title", "employer.job.error.spam");
					errors.state(request, cuentaD <= limitePalabrasSpamPermitidasDescription, "descriptor.description", "employer.job.error.spam");

					if (cuentaT > limitePalabrasSpamPermitidasTitle || cuentaD > limitePalabrasSpamPermitidasDescription) {
						break;
					}
				}
			}
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor descriptor = entity.getDescriptor();
		descriptor.setDescription(request.getModel().getString("descriptor.description"));
		entity.setDescriptor(descriptor);

		this.repository.save(entity);
	}
}
