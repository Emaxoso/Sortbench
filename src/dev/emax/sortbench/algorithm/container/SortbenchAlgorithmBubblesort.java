package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmBubblesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		for (int datasetIndexI = 0; datasetIndexI < algorithmDataset.datasetSize(); datasetIndexI++) {
			for (int datasetIndexJ = 1; datasetIndexJ < (algorithmDataset.datasetSize() - datasetIndexI); datasetIndexJ++) {
				if (algorithmDataset.datasetGet(datasetIndexJ - 1) > algorithmDataset.datasetGet(datasetIndexJ)) {
					algorithmDataset.datasetSwap(datasetIndexJ - 1, datasetIndexJ);
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
