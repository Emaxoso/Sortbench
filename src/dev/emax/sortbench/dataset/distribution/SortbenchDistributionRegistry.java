package dev.emax.sortbench.dataset.distribution;

import dev.emax.sortbench.dataset.distribution.container.SortbenchDistributionGaussian;
import dev.emax.sortbench.dataset.distribution.container.SortbenchDistributionLinear;
import dev.emax.sortbench.dataset.distribution.container.SortbenchDistributionRandom;
import dev.emax.sortbench.dataset.distribution.container.SortbenchDistributionSinusoidal;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Il registro degli algoritmi di distribuzione
 */
@AllArgsConstructor
public enum SortbenchDistributionRegistry {

	DISTRIBUTION_RANDOM(new SortbenchDistributionRandom()),
	DISTRIBUTION_LINEAR(new SortbenchDistributionLinear()),
	DISTRIBUTION_GAUSSIAN(new SortbenchDistributionGaussian()),
	DISTRIBUTION_SINUSOIDAL(new SortbenchDistributionSinusoidal());

	/**
	 * L'implementazione dell'algoritmo di distribuzione
	 */
	@Getter
	private final SortbenchDistribution<?> sortbenchDistribution;

}
