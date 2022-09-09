package acme.features.chef.delor;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.delor.Delor;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;


@Controller
public class DelorChefController extends AbstractController<Chef, Delor> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected DelorChefListService	listService;

			@Autowired
			protected DelorChefShowService	showService;
			
			@Autowired
			protected DelorChefCreateService	createService;
			
			@Autowired
			protected DelorChefUpdateService	updateService;
			
			@Autowired
			protected DelorChefDeleteService	deleteService;
			

			
			

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
				super.addCommand("create", this.createService);
				super.addCommand("update", this.updateService);
				super.addCommand("delete", this.deleteService);
			}

}
