package dev.emax.sortbench.tester;

import java.util.Arrays;
import java.util.LinkedHashMap;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.dataset.SortbenchDataset;
import dev.emax.sortbench.profiler.SortbenchProfiler;
import dev.emax.sortbench.utils.SortbenchUtils;

public class SortbenchTester {

	private static SortbenchProfiler testProfiler;

	static {
		testProfiler = new SortbenchProfiler();
	}

	/**
	 * Esegue un singolo test sul singolo algoritmo con un singolo dataset
	 *
	 * @param testAlgorithm		L'algoritmo da testare
	 * @param testDataset		Il dataset da ordinare con l'algoritmo
	 * @return					Il risultato del test
	 */
	public static SortbenchTesterResultTest runTest(SortbenchAlgorithm testAlgorithm, SortbenchDataset testDataset) {
		// Resetto il profiler
		testProfiler.profilerReset();

		// Ordino il dataset monitorando il tempo
		testProfiler.profilerStart();
		testAlgorithm.algorithmCompute(testDataset);
		testProfiler.profilerPause();

		// Ritorno il risultato del test
		return new SortbenchTesterResultTest(
			testDataset.datasetSnapshot(),
			testProfiler.profilerElapsed()
		);
	}

	/**
	 * Esegue molteplici test sul singolo algoritmo con molteplici dataset
	 *
	 * @param testListener		Il listner dello stato dei test
	 * @param testAlgorithm		L'algoritmo da testare
	 * @param testDatasets		I dataset con cui testare l'algoritmo
	 *
	 * @return					Il risultato dei test
	 */
	public static SortbenchTesterResultTests runTest(SortbenchTesterListener testListener, SortbenchAlgorithm testAlgorithm, SortbenchDataset[] testDatasets) {
		// Ritorno il risultato del test
		return new SortbenchTesterResultTests(
			SortbenchUtils.arrayFill(new SortbenchTesterResultTest[testDatasets.length], (testDatasetIndex) -> {
				testListener.testDataset(testDatasets[testDatasetIndex], testDatasetIndex, testDatasets.length);
				return runTest(testAlgorithm, testDatasets[testDatasetIndex]);
			})
		);
	}

	/**
	 * Esegue molteplici test su molteplici algoritmi con molteplici dataset
	 *
	 * @param testListener		Il listener del test
	 * @param testAlgorithms	Gli algoritmi da testare
	 * @param testDatasets		I dataset con cui testare gli algoritmi
	 */
	public static SortbenchTesterResultMultitest runTest(SortbenchTesterListener testListener, SortbenchAlgorithm[] testAlgorithms, SortbenchDataset[] testDatasets) {
		// La mappa che conterrà  il risultato del multitest
		LinkedHashMap<SortbenchAlgorithm, SortbenchTesterResultTests> testResults = new LinkedHashMap<>();

		// Itero tutti gli algoritmi da testare
		for (int testAlgorithmIndex = 0; testAlgorithmIndex < testAlgorithms.length; testAlgorithmIndex++) {
			SortbenchAlgorithm testAlgorithm = testAlgorithms[testAlgorithmIndex];

			// Richiamo il listener per questo algoritmo
			testListener.testAlgorithm(testAlgorithm, testAlgorithmIndex, testAlgorithms.length);

			// Eseguo i test e aggiungo il risultato alla mappa dei risultati
			testResults.put(testAlgorithm, runTest(testListener, testAlgorithm,	testDatasets));

			// Resetto tutti i dataset allo stato originale
			Arrays.stream(testDatasets)
				.forEach(SortbenchDataset::datasetReset);
		}

		// Ritorno il risultato del multitest
		return new SortbenchTesterResultMultitest(testResults);
	}


}
