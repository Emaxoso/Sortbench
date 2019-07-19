package dev.emax.sortbench.tester;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene il risultato di multipli test effettuati su
 * un singolo algoritmo con molteplici dataset
 */
@AllArgsConstructor
public class SortbenchTesterResultTests {

	/**
	 * L'array contente il risultato dei test atomici
	 */
	@Getter
	private final SortbenchTesterResultTest[] testsResult;

	public int getResultTotalOperations() {
		return Arrays.stream(testsResult)
				.mapToInt(SortbenchTesterResultTest::getResultOperations)
				.max().getAsInt();
	}

	public double getResultAverageOperations() {
		return Arrays.stream(testsResult)
				.mapToDouble(SortbenchTesterResultTest::getResultOperations)
				.average().getAsDouble();
	}

	public double getResultAverageTime() {
		return Arrays.stream(testsResult)
				.mapToDouble(SortbenchTesterResultTest::getResultTime)
				.average().getAsDouble();
	}

	public BufferedImage getDatasetImage(int datasetOperation) {
		BufferedImage datasetImage = new BufferedImage(
			testsResult[0].getDatasetLength(),
			testsResult.length,
			BufferedImage.TYPE_INT_ARGB
		);

		for (int datasetIndex = 0; datasetIndex < datasetImage.getHeight(); datasetIndex++) {
			int[] datasetData = testsResult[datasetIndex].getDatasetAt(datasetOperation);
			int datasetRangeMax = testsResult[datasetIndex].getDatasetRangeMax();
			int datasetRangeMin = testsResult[datasetIndex].getDatasetRangeMin();

			for (int datasetDataIndex = 0; datasetDataIndex < datasetImage.getWidth(); datasetDataIndex++) {
				datasetImage.setRGB(
					datasetDataIndex,
					datasetIndex,
					Color.HSBtoRGB(
						SortbenchUtils.map(
							datasetData[datasetDataIndex],
							datasetRangeMin,
							datasetRangeMax,
							0.0F, 1.0F
						),
						1.0F, 1.0F
					)
				);
			}
		}

		return datasetImage;
	}




}
