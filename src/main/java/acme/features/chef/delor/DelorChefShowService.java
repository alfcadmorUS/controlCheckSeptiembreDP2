package acme.features.chef.delor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class DelorChefShowService implements AbstractShowService<Chef, Delor>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected DelorChefRepository repository;

			// AbstractShowService<Anonymous, Announcement> interface --------------------------

			@Override
			public boolean authorise(final Request<Delor> request) {
				assert request != null;

				
				return true;
			}

			@Override
			public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

			
				
				request.unbind(entity, model, "keylet", "instantiationMoment", "subject", "explanation", "startPeriod", "finishPeriod", "income", "moreInfo");
				model.setAttribute("ingredientId", entity.getIngredient().getId());
				model.setAttribute("ingredientPublish", entity.getIngredient().isPublished());
				model.setAttribute("isNew", false);
				
				
			}

			@Override
			public Delor findOne(final Request<Delor> request) { 
				assert request != null;

				Delor result;
				int id;

				id = request.getModel().getInteger("id");
				result = this.repository.findOneDelorById(id);

				return result;
			}
			
			

}
