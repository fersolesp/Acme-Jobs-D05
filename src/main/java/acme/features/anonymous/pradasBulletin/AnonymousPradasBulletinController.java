
package acme.features.anonymous.pradasBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.pradasBulletin.PradasBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/pradas-bulletin/")
public class AnonymousPradasBulletinController extends AbstractController<Anonymous, PradasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousPradasBulletinListService		listService;

	@Autowired
	private AnonymousPradasBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
