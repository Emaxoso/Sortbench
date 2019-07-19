package dev.emax.sortbench.algorithm;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

import dev.emax.sortbench.ui.SortbenchName;
import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.Builder;
import lombok.Getter;

@Builder
public class SortbenchAlgorithmInfo {

	/**
	 * Il nome dei campi delle informazioni
	 */
	@Getter
	private static final String[] infoRegistry;

	static {
		infoRegistry = Arrays.stream(SortbenchAlgorithmInfo.class.getDeclaredFields())
			.filter(f -> f.isAnnotationPresent(SortbenchName.class))
			.map(f -> f.getAnnotation(SortbenchName.class))
			.map(SortbenchName::value)
			.toArray(String[]::new);
	}

	/**
	 * Il nome dell'algoritmo
	 */
	@Getter
	@SortbenchName("Algoritmo")
	private final String algorithmName;

	/**
	 * L'autore dell'algoritmo
	 */
	@Getter
	@SortbenchName("Autore")
	private final String algorithmAuthor;

	/**
	 * L'anno di creazione dell'algoritmo
	 */
	@Getter
	@SortbenchName("Anno")
	private final String algorithmYear;

	/**
	 * Il caso milgiore dell'algoritmo
	 */
	@Getter
	@SortbenchName("Caso Migliore")
	private final String algorithmCaseBest;

	/**
	 * Il caso peggiore dell'algoritmo
	 */
	@Getter
	@SortbenchName("Caso Peggiore")
	private final String algorithmCaseWorst;

	/**
	 * Ritorna le informazioni dell'algorimo in formato lineare (array)
	 *
	 * @return le informazioni dell'algorimo in formato lineare (array)
	 */
	public String[] getAlgorithmInfos() {
		try {
			LinkedList<String> infoValues = new LinkedList<String>();
			for (Field infoField : SortbenchAlgorithmInfo.class.getDeclaredFields()) {
				if (infoField.isAnnotationPresent(SortbenchName.class)) {
					infoValues.add((String) infoField.get(this));
				}
			}
			return SortbenchUtils.arrayList(String.class, infoValues);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Ritorna il numero di informazioni presenti
	 *
	 * @return il numero di informazioni presenti
	 */
	public static int getInfoCount() {
		return infoRegistry.length;
	}

}
