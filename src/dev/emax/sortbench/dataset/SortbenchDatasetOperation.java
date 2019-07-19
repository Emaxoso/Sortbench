package dev.emax.sortbench.dataset;

import lombok.Getter;

public abstract class SortbenchDatasetOperation {

	/**
	 * L'indice dove è stata effettuata l'operazione
	 */
	@Getter
	protected final int operationIndex;

	public SortbenchDatasetOperation(int operationIndex) {
		this.operationIndex = operationIndex;
	}

	/**
	 * Effettua l'operazione sul dataset
	 *
	 * @param operationDataset 	Il dataset su cui effettuare l'operazione
	 */
	public abstract void operationRun(int[] operationDataset);

}
