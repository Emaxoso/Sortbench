package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmPigeonholesort extends SortbenchAlgorithm {
	
	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int datasetMin = algorithmDataset.datasetGet(0);
		int datasetMax = algorithmDataset.datasetGet(0);
		int datasetRange, datasetIndexI, datasetIndexJ, datasetCursor;

		for (int datasetIndex = 0; datasetIndex < algorithmDataset.datasetSize(); datasetIndex++) {
			if (algorithmDataset.datasetGet(datasetIndex) > datasetMax) {
				datasetMax = algorithmDataset.datasetGet(datasetIndex);				
			}
			if (algorithmDataset.datasetGet(datasetIndex) < datasetMin) {
				datasetMin = algorithmDataset.datasetGet(datasetIndex);
			}
		}

		datasetRange = datasetMax - datasetMin + 1;		
		
		int[] datasetPigeonhole = new int[datasetRange];			
		
		for (datasetIndexI = 0; datasetIndexI < algorithmDataset.datasetSize(); datasetIndexI++) {
			datasetPigeonhole[algorithmDataset.datasetGet(datasetIndexI) - datasetMin]++;
		}

		datasetCursor = 0;
		for (datasetIndexJ = 0; datasetIndexJ < datasetRange; datasetIndexJ++) {
			while (datasetPigeonhole[datasetIndexJ]-- > 0) {
				algorithmDataset.datasetSet(datasetCursor++, datasetIndexJ + datasetMin);
			}
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Pigeonhole Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(N + n)")
				.build();
	}

}
