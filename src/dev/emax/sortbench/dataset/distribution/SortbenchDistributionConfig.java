package dev.emax.sortbench.dataset.distribution;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import dev.emax.sortbench.ui.SortbenchDialog;
import lombok.Getter;

public class SortbenchDistributionConfig {

	/**
	 * Il range minimo e massimo della distribuzione
	 */
	@Getter
	private int distributionMin = 0,
			    distributionMax = 100;

	public int getDistributionRange() {
		return distributionMax - distributionMin;
	}

	/**
	 * Crea i componenti grafici per configurare la distribuzione
	 *
	 * @param distributionConfigDialog	Il dialogo su cui crare i componenti grafici
	 */
	public void configSetupUI(SortbenchDialog distributionConfigDialog) {
		JSpinner distributionConfigSpinnerMin = new JSpinner();
		distributionConfigSpinnerMin.setModel(new SpinnerNumberModel(distributionMin, null, null, 5));
		distributionConfigSpinnerMin.setBounds(0, 0, 0, 25);
		distributionConfigSpinnerMin.addChangeListener(($) -> {
			distributionMin = (int) distributionConfigSpinnerMin.getValue();
		});

		JSpinner distributionConfigSpinnerMax = new JSpinner();
		distributionConfigSpinnerMax.setModel(new SpinnerNumberModel(distributionMax, null, null, 5));
		distributionConfigSpinnerMax.setBounds(0, 0, 0, 25);
		distributionConfigSpinnerMax.addChangeListener(($) -> {
			distributionMax = (int) distributionConfigSpinnerMax.getValue();
		});

		distributionConfigDialog.dialogComponentAdd("Distribuzione Massima", "Il massimo valore della distribuzione", distributionConfigSpinnerMax);
		distributionConfigDialog.dialogComponentAdd("Distribuzione Minima", "Il minimo valore della distribuzione", distributionConfigSpinnerMin);
	}

}
