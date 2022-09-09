package acme.entities.administratorDashboard;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.artifact.ArtifactType;
import acme.entities.fineDish.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected Map<ArtifactType,Integer>					totalArtifact;
	
	protected Map<Pair<ArtifactType, String>, Double>	averageArtifactRetailPrice;
	
	protected Map<Pair<ArtifactType, String>, Double>	deviationArtifactRetailPrice;
	
	protected Map<Pair<ArtifactType, String>, Double>	minimumArtifactRetailPrice;

	protected Map<Pair<ArtifactType, String>, Double>	maximumArtifactRetailPrice;
	
	protected Map<StatusType, Integer> 					totalFineDish;
	
	protected Map<StatusType, Double>					averageFineDishBudget;
	
	protected Map<StatusType, Double>					deviationFineDishBudget;
	
	protected Map<StatusType, Double>					maximumFineDishBudget;
	
	protected Map<StatusType, Double>					minimumFineDishBudget;
	
	//PIMPAM
	protected Double								ratioOfIngredientsWithDelor;
	
	protected Map<String, Double>					averageDelorIncome;
	
	protected Map<String, Double>					deviationDelorIncome;
	
	protected Map<String, Double>					maximumDelorIncome;
	
	protected Map<String, Double>					minimumDelorIncome;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
		
}
