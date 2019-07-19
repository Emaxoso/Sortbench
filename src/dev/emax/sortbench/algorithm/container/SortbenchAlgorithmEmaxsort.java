package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmEmaxsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		for (int datasetIndexA = 0; datasetIndexA < (algorithmDataset.datasetSize() / 2) + 2; datasetIndexA++) {

			int datasetIndexMin = datasetIndexA;
			int datasetIndexMax = datasetIndexA;

			for (int datasetIndexB = datasetIndexA; datasetIndexB < algorithmDataset.datasetSize() - datasetIndexA; datasetIndexB++) {
				if (algorithmDataset.datasetGet(datasetIndexB) < algorithmDataset.datasetGet(datasetIndexMin)) datasetIndexMin = datasetIndexB;
				if (algorithmDataset.datasetGet(datasetIndexB) > algorithmDataset.datasetGet(datasetIndexMax)) datasetIndexMax = datasetIndexB;
			}

			algorithmDataset.datasetSwap(algorithmDataset.datasetSize() - datasetIndexA - 1, datasetIndexMax);
			algorithmDataset.datasetSwap(datasetIndexA, datasetIndexMin);
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("EmaxSort")
				.build();
	}


}
