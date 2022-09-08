package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefPimpamListService implements AbstractListService<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamRepository repository;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request!=null;
		return true;
	}

	@Override
	public Collection<Pimpam> findMany(final Request<Pimpam> request) {
		assert request !=null;
		Collection<Pimpam> result;
		result= this.repository.findAllPimpams();
		return result;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "code","instantiationMoment","title","description","initialDate","finishDate","budget","link","artifact");
		
	}

}