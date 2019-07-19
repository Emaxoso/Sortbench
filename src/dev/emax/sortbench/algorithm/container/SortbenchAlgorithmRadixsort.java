package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmRadixsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		radixsort(algorithmDataset, algorithmDataset.datasetSize());
	}

	// A utility function to get maximum value in arr[]
    static int getMax(SortbenchDataset arr, int n)
    {
        int mx = arr.datasetGet(0);
        for (int i = 1; i < n; i++)
            if (arr.datasetGet(i) > mx)
                mx = arr.datasetGet(i);
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(SortbenchDataset arr, int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];


        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr.datasetGet(i)/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr.datasetGet(i)/exp)%10 ] - 1] = arr.datasetGet(i);
            count[ (arr.datasetGet(i)/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
        	arr.datasetSet(i, output[i]);
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixsort(SortbenchDataset arr, int n)
    {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }



	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Radix Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(kN)")
				.build();
	}


}
