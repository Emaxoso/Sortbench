package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmSelectionsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		for (int i = 0; i < algorithmDataset.datasetSize() - 1; i++) {
			int minimo = i; // Partiamo dall' i-esimo elemento
			for (int j = i + 1; j < algorithmDataset.datasetSize(); j++) {

				// Qui avviene la selezione, ogni volta
				// che nell' iterazione troviamo un elemento piú piccolo di
				// minimo
				// facciamo puntare minimo all' elemento trovato
				if (algorithmDataset.datasetGet(minimo) > algorithmDataset.datasetGet(j)) {
					minimo = j;
				}
			}

			// Se minimo e diverso dall' elemento di partenza
			// allora avviene lo scambio
			if (minimo != i) {
				algorithmDataset.datasetSwap(minimo, i);
			}
		}
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Selection Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n^2)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
