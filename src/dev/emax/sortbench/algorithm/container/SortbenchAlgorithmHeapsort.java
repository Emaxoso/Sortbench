package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmHeapsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int n = algorithmDataset.datasetSize();

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(algorithmDataset, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			algorithmDataset.datasetSwap(0, i);

			// call max heapify on the reduced heap
			heapify(algorithmDataset, i, 0);
		}
	}

	private void heapify(SortbenchDataset algorithmDataset, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && algorithmDataset.datasetGet(l) > algorithmDataset.datasetGet(largest)) largest = l;

		// If right child is larger than largest so far
		if (r < n && algorithmDataset.datasetGet(r) > algorithmDataset.datasetGet(largest)) largest = r;

		// If largest is not root
		if (largest != i) {
			algorithmDataset.datasetSwap(i, largest);

			// Recursively heapify the affected sub-tree
			heapify(algorithmDataset, n, largest);
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
