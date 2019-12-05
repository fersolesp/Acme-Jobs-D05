
package acme.features.authenticated.investorRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investor-records/")
public class AuthenticatedInvestorRecordsController extends AbstractController<Authenticated, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInvestorRecordsListService	listService;

	@Autowired
	private AuthenticatedInvestorRecordsShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
