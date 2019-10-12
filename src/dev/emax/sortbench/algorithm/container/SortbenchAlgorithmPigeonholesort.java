package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmPigeonholesort extends SortbenchAlgorithm {
	
	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int datasetMin = algorithmDataset.datasetGet(0);
		int datasetMax = algorithmDataset.datasetGet(0);
		int range, i, j, index;

		for (int a = 0; a < algorithmDataset.datasetSize(); a++) {
			if (algorithmDataset.datasetGet(a) > datasetMax) {
				datasetMax = algorithmDataset.datasetGet(a);				
			}
			if (algorithmDataset.datasetGet(a) < datasetMin)
				datasetMin = algorithmDataset.datasetGet(a);
		}

		range = datasetMax - datasetMin + 1;		
		
		int[] phole = new int[range];			
		
		for (i = 0; i < algorithmDataset.datasetSize(); i++)
			phole[algorithmDataset.datasetGet(i) - datasetMin]++;

		index = 0;

		for (j = 0; j < range; j++)
			while (phole[j]-- > 0)
				algorithmDataset.datasetSet(index++, j + datasetMin);
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Pigeonhole Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(kN)")
				.build();
	}

}
