package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmCyclesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		
		for (int datasetIndexS = 0; datasetIndexS <= algorithmDataset.datasetSize() - 2; datasetIndexS++) {

			int datasetItem = algorithmDataset.datasetGet(datasetIndexS);

			int datasetIndex = datasetIndexS;
			for (int datasetIndexI = datasetIndexS + 1; datasetIndexI < algorithmDataset.datasetSize(); datasetIndexI++) {
				if (algorithmDataset.datasetGet(datasetIndexI) < datasetItem) {
					datasetIndex++;
				}
			}

			if (datasetIndex == datasetIndexS) {
				continue;
			}

			while (datasetItem == algorithmDataset.datasetGet(datasetIndex)) {
				datasetIndex += 1;
			}

			if (datasetIndex != datasetIndexS) {
				int datasetItemTemp = datasetItem;
				datasetItem = algorithmDataset.datasetGet(datasetIndex);
				algorithmDataset.datasetSet(datasetIndex, datasetItemTemp);
			}

			while (datasetIndex != datasetIndexS) {
				datasetIndex = datasetIndexS;

				for (int datasetIndexI = datasetIndexS + 1; datasetIndexI < algorithmDataset.datasetSize(); datasetIndexI++) {
					if (algorithmDataset.datasetGet(datasetIndexI) < datasetItem) {
						datasetIndex += 1;
					}
				}

				while (datasetItem == algorithmDataset.datasetGet(datasetIndex)) {
					datasetIndex += 1;
				}

				if (datasetItem != algorithmDataset.datasetGet(datasetIndex)) {
					int datasetItemTemp = datasetItem;
					datasetItem = algorithmDataset.datasetGet(datasetIndex);
					algorithmDataset.datasetSet(datasetIndex, datasetItemTemp);
				}
			}
		}
	}


	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Cycle Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(kN)")
				.build();
	}


}
