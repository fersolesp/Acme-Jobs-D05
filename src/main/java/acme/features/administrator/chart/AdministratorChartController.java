
package acme.features.administrator.chart;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.forms.Chart;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/chart/")
public class AdministratorChartController extends AbstractController<Administrator, Chart> {

	@Autowired
	AdministratorChartShowService showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.CHART, BasicCommand.SHOW, this.showService);
	}
}
