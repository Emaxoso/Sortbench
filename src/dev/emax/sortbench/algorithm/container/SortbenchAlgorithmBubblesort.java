package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmBubblesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		for (int i = 0; i < algorithmDataset.datasetSize(); i++) {
			for (int j = 1; j < (algorithmDataset.datasetSize() - i); j++) {
				if (algorithmDataset.datasetGet(j - 1) > algorithmDataset.datasetGet(j)) {
					algorithmDataset.datasetSwap(j - 1, j);
				}
			}
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
			.algorithmName("Bubble Sort")
			.algorithmAuthor("-")
			.algorithmYear("-")
			.algorithmCaseBest("O(n^2)")
			.algorithmCaseWorst("O(n^2)")
			.build();
	}

}
