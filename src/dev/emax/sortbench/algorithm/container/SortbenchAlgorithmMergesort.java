package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmMergesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		sort(algorithmDataset, 0, algorithmDataset.datasetSize() - 1); 
	}
	
	void sort(SortbenchDataset algorithmDataset, int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l + r)/2; 
  
            // Sort first and second halves 
            sort(algorithmDataset, l, m); 
            sort(algorithmDataset , m + 1, r); 
  
            // Merge the sorted halves 
            merge(algorithmDataset, l, m, r); 
        } 
    } 
	
	void merge(SortbenchDataset algorithmDataset, int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = algorithmDataset.datasetGet(l + i); 
        for (int j=0; j<n2; ++j) 
            R[j] = algorithmDataset.datasetGet(m + 1+ j); 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
            	algorithmDataset.datasetSet(k, L[i]); 
                i++; 
            } 
            else
            { 
            	algorithmDataset.datasetSet(k, R[j]); 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
        	algorithmDataset.datasetSet(k, L[i]); 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
        	algorithmDataset.datasetSet(k, R[j]); 
            j++; 
            k++; 
        } 
    } 
  

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Merge Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
