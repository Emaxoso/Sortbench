package dev.emax.sortbench.dataset;

import lombok.Getter;

public class SortbenchDatasetOperationSet extends SortbenchDatasetOperation {

	/**
	 * Il valore impostato alla posizione
	 */
	@Getter
	private final int operationValue;

	public SortbenchDatasetOperationSet(int operationIndex, int operationValue) {
		super(operationIndex);
		this.operationValue = operationValue;
	}

	@Override
	public void operationRun(int[] operationDataset) {
		operationDataset[operationIndex] = operationValue;
	}

}
