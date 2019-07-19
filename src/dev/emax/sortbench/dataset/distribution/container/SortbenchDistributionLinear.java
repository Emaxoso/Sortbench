package dev.emax.sortbench.dataset.distribution.container;

import dev.emax.sortbench.dataset.distribution.SortbenchDistribution;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionConfig;
import dev.emax.sortbench.utils.SortbenchUtils;

public class SortbenchDistributionLinear extends SortbenchDistribution<SortbenchDistributionConfig> {

	public SortbenchDistributionLinear() {
		super(SortbenchDistributionConfig.class);
	}

	@Override
	public void distributionGenerate(int[] distributionData, SortbenchDistributionConfig distributionConfig) {
		SortbenchUtils.arrayFill(distributionData, (distributionDataIndex) -> {
			return (int) (SortbenchUtils.map(
				distributionData.length - distributionDataIndex, 0,
				distributionData.length,
				distributionConfig.getDistributionMin(),
				distributionConfig.getDistributionMax()
			));
		});
	}

	@Override
	public String getDistributionName() {
		return "Distribuzione Lineare";
	}

}
