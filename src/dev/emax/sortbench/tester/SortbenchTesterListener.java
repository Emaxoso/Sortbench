package dev.emax.sortbench.tester;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.dataset.SortbenchDataset;

public interface SortbenchTesterListener {

	public void testAlgorithm(SortbenchAlgorithm testAlgorithm, int testAlgorithmIndex, int testAlgorithmCount);

	public void testDataset(SortbenchDataset testDataset, int testDatasetIndex, int testDatasetCount);

}
