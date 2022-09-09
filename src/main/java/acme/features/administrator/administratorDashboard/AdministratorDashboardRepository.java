package acme.features.administrator.administratorDashboard;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.delor.Delor;
import acme.entities.fineDish.StatusType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Artifact a where a.type = :type")
	Integer countArtifactByType(ArtifactType type);

	@Query("select s.acceptedCurrencies from SystemSettings s")
	String findAcceptedCurrencies();

	@Query("select avg(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcAverageArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);
	
	@Query("select stddev(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcDeviationArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);

	@Query("select min(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcMinimumArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);
	
	@Query("select max(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcMaximumArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);

	@Query("select count(f) from FineDish f where f.status = :status")
	Integer countFineDishByStatus(StatusType status);

	@Query("select avg(f.budget.amount) from FineDish f where f.status = :status")
	Double calcAverageFineDishBudgetByStatus(StatusType status);

	@Query("select stddev(f.budget.amount) from FineDish f where f.status = :status")
	Double calcDeviationFineDishBudgetByStatus(StatusType status);

	@Query("select max(f.budget.amount) from FineDish f where f.status = :status")
	Double calcMaximumFineDishBudgetByStatus(StatusType status);

	@Query("select min(f.budget.amount) from FineDish f where f.status = :status")
	Double calcMinimumFineDishBudgetByStatus(StatusType status);
	
	//PIMPAM
	
	@Query("select avg(f.income.amount) from Delor f where f.income.currency = :currency")
	Double calcAverageDelorIncometByCurrency(String currency);

	@Query("select stddev(f.income.amount) from Delor f where f.income.currency = :currency")
	Double calcDeviationDelorIncometByCurrency(String currency);

	@Query("select max(f.income.amount) from Delor f where f.income.currency = :currency")
	Double calcMaximumDelorIncometByCurrency(String currency);

	@Query("select min(f.income.amount) from Delor f where f.income.currency = :currency")
	Double calcMinimumDelorIncomeByCurrency(String currency);
	
	@Query("select c from Delor c")
	Collection<Delor> findAllDelor();
	
	@Query("select a from Artifact a where a.type = :artifactType")
	List<Artifact> findAllIngredients(ArtifactType artifactType);

}
