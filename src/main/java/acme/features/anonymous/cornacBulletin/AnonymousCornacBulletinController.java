
package acme.features.anonymous.cornacBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.cornacBulletin.CornacBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/cornac-bulletin/")
public class AnonymousCornacBulletinController extends AbstractController<Anonymous, CornacBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousCornacBulletinListService		listService;

	@Autowired
	private AnonymousCornacBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
