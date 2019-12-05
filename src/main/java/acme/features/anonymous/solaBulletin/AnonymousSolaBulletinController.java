
package acme.features.anonymous.solaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.solaBulletins.SolaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/sola-bulletin/")
public class AnonymousSolaBulletinController extends AbstractController<Anonymous, SolaBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousSolaBulletinListService	listService;

	@Autowired
	private AnonymousSolaBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
