package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmBubblesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		for (int algorithmDatasetIndexI = 0; algorithmDatasetIndexI < algorithmDataset.datasetSize(); algorithmDatasetIndexI++) {
			for (int algorithmDatasetIndexJ = 1; algorithmDatasetIndexJ < (algorithmDataset.datasetSize() - algorithmDatasetIndexI); algorithmDatasetIndexJ++) {
				if (algorithmDataset.datasetGet(algorithmDatasetIndexJ - 1) > algorithmDataset.datasetGet(algorithmDatasetIndexJ)) {
					algorithmDataset.datasetSwap(algorithmDatasetIndexJ - 1, algorithmDatasetIndexJ);
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
