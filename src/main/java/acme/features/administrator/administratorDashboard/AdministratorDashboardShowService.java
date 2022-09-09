
package acme.features.administrator.administratorDashboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.administratorDashboard.AdministratorDashboard;
import acme.entities.artifact.ArtifactType;
import acme.entities.fineDish.StatusType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface --------------------------
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		final AdministratorDashboard result = new AdministratorDashboard();
		final String[] arrayCurrencies = this.repository.findAcceptedCurrencies().split(",");
		final List<String> acceptedCurrencies = Arrays.asList(arrayCurrencies);
		
		final Map<ArtifactType,Integer> totalArtifact = new EnumMap<>(ArtifactType.class);
		for(final ArtifactType type : ArtifactType.values()) {
			totalArtifact.put(type, this.repository.countArtifactByType(type));
		}
		
		final Map<Pair<ArtifactType, String>, Double>	averageArtifactRetailPrice = new HashMap<>();
		for(final ArtifactType type : ArtifactType.values()) {
			for(final String currency : acceptedCurrencies) {
				final Double averagePrice = this.repository.calcAverageArtifactRetailPriceByTypeAndCurrency(type, currency);
				final Double averagePriceFormat = this.formatDouble(averagePrice);
				averageArtifactRetailPrice.put(Pair.of(type, currency),  averagePriceFormat);
			}
		}
		
		final Map<Pair<ArtifactType, String>, Double>	deviationArtifactRetailPrice = new HashMap<>();
		for(final ArtifactType type : ArtifactType.values()) {
			for(final String currency : acceptedCurrencies) {
				final Double deviationPrice = this.repository.calcDeviationArtifactRetailPriceByTypeAndCurrency(type, currency);
				final Double deviationPriceFormat = this.formatDouble(deviationPrice);
				deviationArtifactRetailPrice.put(Pair.of(type, currency),  deviationPriceFormat);
			}
		}
		
		final Map<Pair<ArtifactType, String>, Double>	minimumArtifactRetailPrice = new HashMap<>();
		for(final ArtifactType type : ArtifactType.values()) {
			for(final String currency : acceptedCurrencies) {
				final Double minimumPrice = this.repository.calcMinimumArtifactRetailPriceByTypeAndCurrency(type, currency);
				final Double minimumPriceFormat = this.formatDouble(minimumPrice);
				minimumArtifactRetailPrice.put(Pair.of(type, currency),  minimumPriceFormat);
			}
		}
		
		final Map<Pair<ArtifactType, String>, Double>	maximumArtifactRetailPrice = new HashMap<>();
		for(final ArtifactType type : ArtifactType.values()) {
			for(final String currency : acceptedCurrencies) {
				final Double maximumPrice = this.repository.calcMaximumArtifactRetailPriceByTypeAndCurrency(type, currency);
				final Double maximumPriceFormat = this.formatDouble(maximumPrice);
				maximumArtifactRetailPrice.put(Pair.of(type, currency),  maximumPriceFormat);
			}
		}
		
		final Map<StatusType,Integer> totalFineDish = new EnumMap<>(StatusType.class);
		for(final StatusType status : StatusType.values()) {
			final Integer total = this.repository.countFineDishByStatus(status);
			totalFineDish.put(status, total != null ? total : 0);
		}
		
		final Map<StatusType,Double> averageFineDishBudget = new EnumMap<>(StatusType.class);
		for(final StatusType status : StatusType.values()) {
			final Double averageBudget = this.repository.calcAverageFineDishBudgetByStatus(status);
			final Double averageBudgetFormat = this.formatDouble(averageBudget);
			averageFineDishBudget.put(status, averageBudgetFormat != null ? averageBudgetFormat : 0);
		}
		
		final Map<StatusType,Double> deviationFineDishBudget = new EnumMap<>(StatusType.class);
		for(final StatusType status : StatusType.values()) {
			final Double deviationBudget = this.repository.calcDeviationFineDishBudgetByStatus(status);
			final Double deviationBudgetFormat = this.formatDouble(deviationBudget);
			deviationFineDishBudget.put(status, deviationBudgetFormat != null ? deviationBudgetFormat : 0);
		}
		
		final Map<StatusType,Double> maximumFineDishBudget = new EnumMap<>(StatusType.class);
		for(final StatusType status : StatusType.values()) {
			final Double maximumBudget = this.repository.calcMaximumFineDishBudgetByStatus(status);
			final Double maximumBudgetFormat = this.formatDouble(maximumBudget);
			maximumFineDishBudget.put(status, maximumBudgetFormat != null ? maximumBudgetFormat : 0);
		}
		
		final Map<StatusType,Double> minimumFineDishBudget = new EnumMap<>(StatusType.class);
		for(final StatusType status : StatusType.values()) {
			final Double minimumBudget = this.repository.calcMinimumFineDishBudgetByStatus(status);
			final Double minimumBudgetFormat = this.formatDouble(minimumBudget);
			minimumFineDishBudget.put(status, minimumBudgetFormat != null ? minimumBudgetFormat : 0);
		}
		
		//PIMPAM--------------------------------------------
		
		final Map<String,Double> averageDelorIncome = new HashMap<String, Double>();
		for(final String currencies : acceptedCurrencies) {
			final Double averageIncome = this.repository.calcAverageDelorIncometByCurrency(currencies);
			final Double averageIncomeFormat = this.formatDouble(averageIncome);
			averageDelorIncome.put(currencies, averageIncomeFormat != null ? averageIncomeFormat : 0);
		}
		
		final Map<String,Double> deviationDelorIncome = new HashMap<String, Double>();
		for(final String currencies : acceptedCurrencies) {
			final Double deviationIncome = this.repository.calcDeviationDelorIncometByCurrency(currencies);
			final Double deviationIncomeFormat = this.formatDouble(deviationIncome);
			deviationDelorIncome.put(currencies, deviationIncomeFormat != null ? deviationIncomeFormat : 0);
		}
		
		final Map<String,Double> maximumDelorIncome = new HashMap<String, Double>();
		for(final String currencies : acceptedCurrencies) {
			final Double maximumIncome = this.repository.calcMaximumDelorIncometByCurrency(currencies);
			final Double maximumIncomeFormat = this.formatDouble(maximumIncome);
			maximumDelorIncome.put(currencies, maximumIncomeFormat != null ? maximumIncomeFormat : 0);
		}
		
		final Map<String,Double> minimumDelorIncome = new HashMap<String, Double>();
		for(final String currencies : acceptedCurrencies) {
			final Double minimumBudget = this.repository.calcMinimumDelorIncomeByCurrency(currencies);
			final Double minimumBudgetFormat = this.formatDouble(minimumBudget);
			minimumDelorIncome.put(currencies, minimumBudgetFormat != null ? minimumBudgetFormat : 0);
		}
		
		final Integer nIngredients = this.repository.findAllIngredients(ArtifactType.INGREDIENT).size();
		final Integer nDelor = this.repository.findAllDelor().size();
		final Double ratioOfIngredientsWithDelor = (double) nDelor / nIngredients;
		
		
		
		result.setRatioOfIngredientsWithDelor(nIngredients>0? ratioOfIngredientsWithDelor:0.);		
		result.setTotalArtifact(totalArtifact);
		result.setAverageArtifactRetailPrice(averageArtifactRetailPrice);
		result.setDeviationArtifactRetailPrice(deviationArtifactRetailPrice);
		result.setMinimumArtifactRetailPrice(minimumArtifactRetailPrice);
		result.setMaximumArtifactRetailPrice(maximumArtifactRetailPrice);
		result.setTotalFineDish(totalFineDish);
		result.setAverageFineDishBudget(averageFineDishBudget);
		result.setDeviationFineDishBudget(deviationFineDishBudget);
		result.setMaximumFineDishBudget(maximumFineDishBudget);
		result.setMinimumFineDishBudget(minimumFineDishBudget);
		result.setAverageDelorIncome(averageDelorIncome);
		result.setDeviationDelorIncome(deviationDelorIncome);
		result.setMaximumDelorIncome(maximumDelorIncome);
		result.setMinimumDelorIncome(minimumDelorIncome);
		return result;
	}
	
	protected Double formatDouble(final Double number) {
		return number != null ? BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP).doubleValue() : 0;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalArtifact", "averageArtifactRetailPrice", "deviationArtifactRetailPrice"
			, "minimumArtifactRetailPrice", "maximumArtifactRetailPrice", "totalFineDish", "averageFineDishBudget",
			"deviationFineDishBudget", "maximumFineDishBudget", "minimumFineDishBudget", "ratioOfArtifactsWithPimpam", 
			"averagePimpamBudget",
			"deviationPimpamBudget", "maximumPimpamBudget", "minimumPimpamBudget");
		
		model.setAttribute("acceptedCurrencies", this.repository.findAcceptedCurrencies().split(","));
		model.setAttribute("artifactTypes", ArtifactType.values());
		model.setAttribute("fineDishStatuses", StatusType.values());
	}
}
