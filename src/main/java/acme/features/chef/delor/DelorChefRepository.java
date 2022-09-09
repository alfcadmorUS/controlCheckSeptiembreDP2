package acme.features.chef.delor;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.delor.Delor;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface DelorChefRepository extends AbstractRepository{
	
	@Query("select a from Delor a where a.id = :id")
	Delor findOneDelorById(int id);
	
	 
	
	@Query("select a from Artifact a where a.type = :artifactType")
	List<Artifact> findAllIngredients(ArtifactType artifactType);
	
	@Query("select d from Delor d")
	List<Delor> findAllDelor();
	
	@Query("select i from Artifact i where i.id = :id")
	Artifact findIngredientById(int id);
	
//	@Query("select a from Artifact a LEFT JOIN Pimpam c ON c.artifact=a WHERE c IS NULL")
//	List<Artifact> findArtifactList(ArtifactType artifactType);
	
	@Query("select a from Delor a where a.keylet = :keylet")
	Delor findOneDelorByCode(String keylet);

	@Query("select a from Delor a")
	Collection<Delor> findManyDelor();
	
	@Query("select a from Delor a where a.ingredient.id = :i")
	Collection<Delor> findManyDelorByIngredient(int i);

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();

}
