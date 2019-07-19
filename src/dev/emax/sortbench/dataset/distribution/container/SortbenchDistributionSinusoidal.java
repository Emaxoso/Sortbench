package dev.emax.sortbench.dataset.distribution.container;

import dev.emax.sortbench.dataset.distribution.SortbenchDistribution;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionConfig;
import dev.emax.sortbench.utils.SortbenchUtils;

public class SortbenchDistributionSinusoidal extends SortbenchDistribution<SortbenchDistributionConfig> {

	public SortbenchDistributionSinusoidal() {
		super(SortbenchDistributionConfig.class);
	}

	@Override
	public void distributionGenerate(int[] distributionData, SortbenchDistributionConfig distributionConfig) {
		SortbenchUtils.arrayFill(distributionData, (distributionDataIndex) -> {
			return (int) ((Math.sin(distributionDataIndex * 10D) * distributionConfig.getDistributionRange()) + distributionConfig.getDistributionMax());
		});
	}

	@Override
	public String getDistributionName() {
		return "Distribuzione Sinusoidale";
	}

}
