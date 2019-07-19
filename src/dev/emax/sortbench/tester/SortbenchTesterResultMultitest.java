package dev.emax.sortbench.tester;

import java.util.LinkedHashMap;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SortbenchTesterResultMultitest {

	/**
	 * La mappa che contiene il risultato di ogni algoritmo
	 */
	private final LinkedHashMap<SortbenchAlgorithm, SortbenchTesterResultTests> resultMap;

	/**
	 * Ritorna il risultato di un algoritmo
	 *
	 * @param resultAlgorithm	L'algoritmo di cui ottenere il risultato
	 * @return					Il risultato dell'algoritmo
	 */
	public SortbenchTesterResultTests getResultAlgorithm(SortbenchAlgorithm resultAlgorithm) {
		return resultMap.get(resultAlgorithm);
	}

	/**
	 * Ritorna la lista di algoritmi presenti nel risultato
	 *
	 * @return la lista di algoritmi presenti nel risultato
	 */
	public SortbenchAlgorithm[] getResultAlgorithms() {
		return SortbenchUtils.arrayList(
			SortbenchAlgorithm.class,
			resultMap.keySet()
		);
	}

	public int getResultTotalOperations() {
		return resultMap.values().stream()
			.mapToInt(SortbenchTesterResultTests::getResultTotalOperations)
			.max().getAsInt();
	}

}
