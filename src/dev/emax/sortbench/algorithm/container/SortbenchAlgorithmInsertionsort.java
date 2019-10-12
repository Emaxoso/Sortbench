package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmInsertionsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		
		for (int datasetIndexI = 1; datasetIndexI < algorithmDataset.datasetSize(); datasetIndexI++) {
			int datasetItem = algorithmDataset.datasetGet(datasetIndexI);

			int datasetIndexJ = datasetIndexI - 1;
			while ((datasetIndexJ >= 0) && (algorithmDataset.datasetGet(datasetIndexJ) > datasetItem)) {
				algorithmDataset.datasetSet(datasetIndexJ + 1, algorithmDataset.datasetGet(datasetIndexJ));
				datasetIndexJ--;
			}
			algorithmDataset.datasetSet(datasetIndexJ + 1, datasetItem);
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Insertion Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
