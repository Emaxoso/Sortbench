package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmInsertsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int j; // N.B. dichiaro qui j altrimenti non può essere vista dall'ultima istruzione
		for (int i = 1; i < algorithmDataset.datasetSize(); i++) {
			int tmp = algorithmDataset.datasetGet(i); // l'elemento viene rimosso dalla lista

			for (j = i - 1; (j >= 0) && (algorithmDataset.datasetGet(j) > tmp); j--) {
				algorithmDataset.datasetSet(j + 1, algorithmDataset.datasetGet(j));
			}

			algorithmDataset.datasetSet(j + 1, tmp);
			// l'elemento rimosso viene reinserito nella giusta posizione del sottoinsieme ordinato 0..i
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
