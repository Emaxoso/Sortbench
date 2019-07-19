package dev.emax.sortbench.dataset;

import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.Getter;

public class SortbenchDatasetOperationSwap extends SortbenchDatasetOperation {

	/**
	 * L'indice dove è stato effettauto lo swap
	 */
	@Getter
	private final int operationIndexSwap;

	public SortbenchDatasetOperationSwap(int operationIndex, int operationIndexSwap) {
		super(operationIndex);
		this.operationIndexSwap = operationIndexSwap;
	}

	@Override
	public void operationRun(int[] operationDataset) {
		SortbenchUtils.arraySwap(
			operationDataset,
			operationIndexSwap,
			operationIndex
		);
	}

}