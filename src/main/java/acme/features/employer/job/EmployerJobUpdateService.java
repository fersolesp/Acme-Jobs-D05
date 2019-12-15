
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

		boolean isEuro, positiveSalary, isSpamReference, isSpamTitle, isPublish, isSpamDescription;
		Date minimumDeadLine;
		Calendar calendar;

		int descriptorId = entity.getDescriptor().getId();

		List<CustomisationParameter> cp;
		String[] spamWordsEnglish, spamWordsSpanish;
		Double englishThreshold, spanishThreshold;

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

			positiveSalary = entity.getSalary().getAmount() >= 0;
			errors.state(request, positiveSalary, "salary", "employer.job.error.label.salary-amount");
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

				cp = new ArrayList<>(this.repository.findCustomisationParameters());
				spamWordsEnglish = cp.get(0).getSpamWordList().split(",");
				spamWordsSpanish = cp.get(1).getSpamWordList().split(",");
				englishThreshold = cp.get(0).getSpamThreshold();
				spanishThreshold = cp.get(1).getSpamThreshold();

				String reference = request.getModel().getString("reference").toLowerCase();
				String title = request.getModel().getString("title").toLowerCase();
				String description = request.getModel().getString("descriptor.description").toLowerCase();
				Integer numberOfEnglishSpamWordsReference = 0;
				Integer numberOfSpanishSpamWordsReference = 0;
				Integer numberOfEnglishSpamWordsTitle = 0;
				Integer numberOfSpanishSpamWordsTitle = 0;
				Integer numberOfEnglishSpamWordsDescription = 0;
				Integer numberOfSpanishSpamWordsDescription = 0;

				for (String word : spamWordsEnglish) {
					if (reference.contains(word.trim())) {
						numberOfEnglishSpamWordsReference++;
					}
					if (title.contains(word.trim())) {
						numberOfEnglishSpamWordsTitle++;
					}
					if (description.contains(word.trim())) {
						numberOfEnglishSpamWordsDescription++;
					}
				}

				for (String word : spamWordsSpanish) {
					if (reference.contains(word.trim())) {
						numberOfSpanishSpamWordsReference++;
					}
					if (title.contains(word.trim())) {
						numberOfSpanishSpamWordsTitle++;
					}
					if (description.contains(word.trim())) {
						numberOfSpanishSpamWordsDescription++;
					}
				}
				isSpamReference = numberOfEnglishSpamWordsReference * 100 / reference.length() > englishThreshold || numberOfSpanishSpamWordsReference * 100 / reference.length() > spanishThreshold;
				isSpamTitle = numberOfEnglishSpamWordsTitle * 100 / title.length() > englishThreshold || numberOfSpanishSpamWordsTitle * 100 / title.length() > spanishThreshold;
				isSpamDescription = numberOfEnglishSpamWordsDescription * 100 / description.length() > englishThreshold || numberOfSpanishSpamWordsDescription * 100 / description.length() > spanishThreshold;

				errors.state(request, !isSpamReference, "status", "employer.job.error.referenceSpam");
				errors.state(request, !isSpamTitle, "status", "employer.job.error.titleSpam");
				errors.state(request, !isSpamDescription, "status", "employer.job.error.descriptionSpam");
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
