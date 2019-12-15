
package acme.features.administrator.auditorRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor-request/")
public class AdministratorAuditorRequestController extends AbstractController<Administrator, AuditorRequest> {

	@Autowired
	private AdministratorAuditorRequestUpdateService	updateService;

	@Autowired
	private AdministratorAuditorRequestListService		listService;

	@Autowired
	private AdministratorAuditorRequestShowService		showService;


	// Constructors ----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
