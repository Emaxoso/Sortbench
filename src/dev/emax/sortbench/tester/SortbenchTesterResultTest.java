package dev.emax.sortbench.tester;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import dev.emax.sortbench.dataset.SortbenchDatasetSnapshot;
import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;

/**
 * Contiene il risultato di un singolo test effettauto
 * su un singolo algoritmo con un singolo dataset
 */
@AllArgsConstructor
public class SortbenchTesterResultTest {

	/**
	 * Lo snapshot del dataset effettuato alla fine del test
	 */
	@Getter
	@Delegate
	private final SortbenchDatasetSnapshot resultDataset;

	/**
	 * Il tempo impiegato per ordinare il dataset
	 */
	@Getter
	private final long resultTime;

	/**
	 * Ritorna il totale delle operazioni effettuate
	 *
	 * @return il totale delle operazioni effettuate
	 */
	public int getResultOperations() {
		return resultDataset.getDatasetOperations();
	}

	/**
	 * Crea un immagine del dataset delle operazioni
	 *
	 * @param 	datasetImageW	La larghezza finale dell'immagine
	 * @param 	datasetImageH	L' altezza finale dell'immagine
	 *
	 * @return					L'immagine del dataset
	 */
	public BufferedImage getResultDatasetImage(int datasetImageW, int datasetImageH) {
		BufferedImage datasetImage = new BufferedImage(resultDataset.getDatasetLength(), getResultOperations(), BufferedImage.TYPE_INT_ARGB);

		int datasetRangeMax = resultDataset.getDatasetRangeMax();
		int datasetRangeMin = resultDataset.getDatasetRangeMin();

		for (int datasetOperationIndex = 0; datasetOperationIndex < datasetImage.getHeight(); datasetOperationIndex++) {
			int[] datasetData = resultDataset.getDatasetAt(datasetOperationIndex);

			for (int datasetDataIndex = 0; datasetDataIndex < datasetImage.getWidth(); datasetDataIndex++) {
				datasetImage.setRGB(datasetDataIndex, datasetOperationIndex,
					Color.HSBtoRGB(
						SortbenchUtils.map(
							datasetData[datasetDataIndex],
							datasetRangeMin,
							datasetRangeMax,
							0F, 1F
						),
						1.0F, 1.0F
					)
				);
			}
		}

		return SortbenchUtils.toBufferedImage(
			datasetImage.getScaledInstance(
				datasetImageW,
				datasetImageH,
				Image.SCALE_SMOOTH
			)
		);
	}

}
