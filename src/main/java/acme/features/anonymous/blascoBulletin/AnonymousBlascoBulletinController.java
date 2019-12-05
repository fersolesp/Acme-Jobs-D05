
package acme.features.anonymous.blascoBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.blascoBulletins.BlascoBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/blascoBulletin/")
public class AnonymousBlascoBulletinController extends AbstractController<Anonymous, BlascoBulletin> {

	@Autowired
	private AnonymousBlascoBulletinListService		listService;

	@Autowired
	private AnonymousBlascoBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
