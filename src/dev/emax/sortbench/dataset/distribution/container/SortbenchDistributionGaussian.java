package dev.emax.sortbench.dataset.distribution.container;

import java.util.Random;

import dev.emax.sortbench.dataset.distribution.SortbenchDistribution;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionConfig;
import dev.emax.sortbench.utils.SortbenchUtils;

public class SortbenchDistributionGaussian extends SortbenchDistribution<SortbenchDistributionConfig> {

	public SortbenchDistributionGaussian() {
		super(SortbenchDistributionConfig.class);
	}

	@Override
	public void distributionGenerate(int[] distributionData, SortbenchDistributionConfig distributionConfig) {
		Random distributionRandom = new Random();
		SortbenchUtils.arrayFill(distributionData, () -> {
			return (int) ((((distributionRandom.nextGaussian() + 10D) / 20D) * distributionConfig.getDistributionMax() - distributionConfig.getDistributionMin()) + distributionConfig.getDistributionMin());
		});
	}

	@Override
	public String getDistributionName() {
		return "Distribuzione Gaussiana";
	}

}

