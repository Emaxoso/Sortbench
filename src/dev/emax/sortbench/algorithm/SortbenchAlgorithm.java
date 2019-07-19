package dev.emax.sortbench.algorithm;

import dev.emax.sortbench.dataset.SortbenchDataset;

public abstract class SortbenchAlgorithm {

	/**
	 * Computa l'algoritmo di ordinamento sul dataset
	 *
	 * @param algorithmDataset	Il dataset su cui computare l'algoritmo
	 */
	public abstract void algorithmCompute(SortbenchDataset algorithmDataset);

	/**
	 * Ritorna le informazioni sull'algoritmo
	 *
	 * @return le informazioni sull'algoritmo
	 */
	public abstract SortbenchAlgorithmInfo algorithmInfo();

	/**
	 * Shortcut per il nome dell'algoritmo
	 *
	 * @return il nome dell'algoritmo
	 *
	 * @see SortbenchAlgorithmInfo#getAlgorithmName()
	 */
	public String getAlgorithmName() {
		return algorithmInfo()
			   .getAlgorithmName();
	}

}
