package dev.emax.sortbench.dataset.distribution;

import java.util.function.Consumer;

import dev.emax.sortbench.Sortbench;
import dev.emax.sortbench.ui.SortbenchDialog;
import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class SortbenchDistribution<C extends SortbenchDistributionConfig> {

	/**
	 * La classe della configurazione di questa distribuzione
	 */
	@Getter
	private final Class<C> distributionConfigClass;

	/**
	 * Genera la distribuzione sull'array passato con la configurazione specificata
	 *
	 * @param distributionData		L'array su cui generare la distribuzione
	 * @param distributionConfig	La configurazione della distribuzione
	 */
	@SuppressWarnings("unchecked")
	public void distributionGenerateRaw(int[] distributionData, SortbenchDistributionConfig distributionConfig) {
		distributionGenerate(distributionData, (C) distributionConfig);
	}

	/**
	 * Genera la distribuzione sull'array passato con la configurazione specificata
	 *
	 * @param distributionData		L'array su cui generare la distribuzione
	 * @param distributionConfig	La configurazione della distribuzione
	 */
	public abstract void distributionGenerate(int[] distributionData, C distributionConfig);

	/**
	 * Ritorna il nome della distribuzione
	 *
	 * @return il nome della distribuzione
	 */
	public abstract String getDistributionName();

	/**
	 * Crea un interfaccia grafica per creare la configurazione della distribuzione
	 *
	 * @param distributionConfigConsumer	Il callback della configurazione (null in caso di annullamento)
	 */
	public void createDistributionConfig(Consumer<C> distributionConfigConsumer) {
		// Creo un dialogo e imposto il titolo con il nome della distribuzione
		SortbenchDialog sortbenchDialog = new SortbenchDialog(Sortbench.getSortbenchFrame());
		sortbenchDialog.setDialogTitle(getDistributionName());

		// Creo la classe che contiene la configurazione e imposto i componenti del dialogo
		C distributionConfig = SortbenchUtils.createClass(distributionConfigClass);
		distributionConfig.configSetupUI(sortbenchDialog);

		// Imposto i callback del dialogo
		sortbenchDialog.addDialogConfirm(() -> distributionConfigConsumer.accept(distributionConfig));
		sortbenchDialog.addDialogCancel(() -> distributionConfigConsumer.accept(null));

		// Imposto il dialogo come completato
		sortbenchDialog.dialogComplete();
	}

}
