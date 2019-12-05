
package acme.features.administrator.investorRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/investor-records/")
public class AdministratorInvestorRecordsController extends AbstractController<Administrator, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorInvestorRecordsListService		listService;

	@Autowired
	private AdministratorInvestorRecordsShowService		showService;

	@Autowired
	private AdministratorInvestorRecordsCreateService	createService;

	@Autowired
	private AdministratorInvestorRecordsUpdateService	updateService;

	@Autowired
	private AdministratorInvestorRecordsDeleteService	deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}
}
