package dev.emax.sortbench.dataset;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SortbenchDatasetSnapshot {

	/**
	 * L'array contente gli swap effettuati sul dataset
	 */
	private final SortbenchDatasetOperation[] datasetOperations;

	/**
	 * Il dataset originale
	 */
	private final int[] datasetData;

	/**
	 * Ritorna lo stato del dataset all'indice delle operazione specificata
	 *
	 * @param 	operationIndex	L'indice dell'operazione
	 * @return				 	Lo stato del dataset all'indice dell'operazione
	 */
	public int[] getDatasetAt(int operationIndex) {
		int[] datasetDataClone = Arrays.copyOf(datasetData, datasetData.length);
		for (int datasetOperationIndex = 0; datasetOperationIndex < Math.min(operationIndex, datasetOperations.length); datasetOperationIndex++) {
			SortbenchDatasetOperation datasetOperation = datasetOperations[datasetOperationIndex];
			datasetOperation.operationRun(datasetDataClone);
		}
		return datasetDataClone;
	}

	/**
	 * Ritorna il numero di operazioni effettuate sul dataset
	 *
	 * @return il numero di operazioni effettuate sul dataset
	 */
	public int getDatasetOperations() {
		return datasetOperations.length;
	}


	/**
	 * Ritorna il range massimo del dataset
	 *
	 * @return il range massimo del dataset
	 */
	public int getDatasetRangeMax() {
		return Arrays.stream(datasetData)
				.max().getAsInt();
	}

	/**
	 * Ritorna il range minimo del dataset
	 *
	 * @return il range minimo del dataset
	 */
	public int getDatasetRangeMin() {
		return Arrays.stream(datasetData)
				.min().getAsInt();
	}

	/**
	 * Ritorna la lunghezza del dataset
	 *
	 * @return la lunghezza del dataset
	 */
	public int getDatasetLength() {
		return datasetData.length;
	}

}
