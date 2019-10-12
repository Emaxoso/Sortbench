package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmCocktailsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		boolean datasetSwapped = true; 
		int datasetStart = 0;
		int datasetEnd = algorithmDataset.datasetSize();

		while (datasetSwapped) {
			datasetSwapped = false;

			for (int datasetIndex = datasetStart; datasetIndex < datasetEnd - 1; ++datasetIndex) {
				if (algorithmDataset.datasetGet(datasetIndex) > algorithmDataset.datasetGet(datasetIndex + 1)) {
					algorithmDataset.datasetSwap(datasetIndex, datasetIndex + 1);
					datasetSwapped = true;
				}
			}

			if (datasetSwapped) {	
				datasetSwapped = false;
				datasetEnd--;
	
				for (int datasetIndex = datasetEnd - 1; datasetIndex >= datasetStart; datasetIndex--) {
					if (algorithmDataset.datasetGet(datasetIndex) > algorithmDataset.datasetGet(datasetIndex + 1)) {
						algorithmDataset.datasetSwap(datasetIndex, datasetIndex + 1);
						datasetSwapped = true;
					}
				}
	
				datasetStart++;
			}
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Cocktail Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
