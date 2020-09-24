package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmQuicksort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		sort(algorithmDataset, 0, algorithmDataset.datasetSize() - 1);
	}

	public void sort(SortbenchDataset algorithmDataset, int low, int high) {
        if (low < high) {			
            int pi = partition(algorithmDataset, low, high);
            sort(algorithmDataset, low, pi-1);
            sort(algorithmDataset, pi+1, high);
        }
	}

	int partition(SortbenchDataset arr, int low, int high) {
		int pivot = arr.datasetGet(high);
		int i = (low - 1); 
		for (int j = low; j < high; j++) {
			if (arr.datasetGet(j) <= pivot) {
				i++;
				
				arr.datasetSwap(i, j);
			}
		}

		arr.datasetSwap(i + 1, high);

		return i + 1;
	}



	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Quick Sort")
				.algorithmAuthor("Tony Hoare")
				.algorithmYear("1959")
				.algorithmCaseBest("O(n log n)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
