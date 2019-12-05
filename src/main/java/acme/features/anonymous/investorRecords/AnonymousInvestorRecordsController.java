
package acme.features.anonymous.investorRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investorRecords.InvestorRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/investor-records/")
public class AnonymousInvestorRecordsController extends AbstractController<Anonymous, InvestorRecords> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousInvestorRecordsListService		listService;

	@Autowired
	private AnonymousInvestorRecordTopListService	topListService;

	@Autowired
	private AnonymousInvestorRecordsShowService		showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.LIST_TOP, BasicCommand.LIST, this.topListService);

	}
}
