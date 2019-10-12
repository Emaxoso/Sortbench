package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmHeapsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int datasetSize = algorithmDataset.datasetSize();

		for (int datasetIndex = datasetSize / 2 - 1; datasetIndex >= 0; datasetIndex--) {
			algorithmHeapfy(algorithmDataset, datasetSize, datasetIndex);
		}

		for (int datasetIndex = datasetSize - 1; datasetIndex >= 0; datasetIndex--) {
			algorithmDataset.datasetSwap(0, datasetIndex);
			algorithmHeapfy(algorithmDataset, datasetIndex, 0);
		}
	}

	private void algorithmHeapfy(SortbenchDataset algorithmDataset, int datasetIndexStart, int datasetIndexEnd) {
		int datasetIndexLargest = datasetIndexEnd;
		int datasetIndexLeft = 2 * datasetIndexEnd + 1;
		int datasetIndexRight = 2 * datasetIndexEnd + 2;

		if (datasetIndexLeft < datasetIndexStart && algorithmDataset.datasetGet(datasetIndexLeft) > algorithmDataset.datasetGet(datasetIndexLargest)) {
			datasetIndexLargest = datasetIndexLeft;
		}
		
		if (datasetIndexRight < datasetIndexStart && algorithmDataset.datasetGet(datasetIndexRight) > algorithmDataset.datasetGet(datasetIndexLargest)) {
			datasetIndexLargest = datasetIndexRight;
		}
		
		if (datasetIndexLargest != datasetIndexEnd) {
			algorithmDataset.datasetSwap(datasetIndexEnd, datasetIndexLargest);
			algorithmHeapfy(algorithmDataset, datasetIndexStart, datasetIndexLargest);
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Heap Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n log n)")
				.algorithmCaseWorst("O(n log n)")
				.build();
	}
}
