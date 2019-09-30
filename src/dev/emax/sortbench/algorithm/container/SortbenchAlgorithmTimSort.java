package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmTimSort extends SortbenchAlgorithm {

	 static int RUN = 32; 
	
	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		
		 // Sort individual subarrays of size RUN  
        for (int i = 0; i < algorithmDataset.datasetSize(); i += 32)  { 
            insertionSort(algorithmDataset, i, Math.min((i + 31), (algorithmDataset.datasetSize() - 1))); 
        } 
  
        // start merging from size RUN (or 32). It will merge  
        // to form size 64, then 128, 256 and so on ....  
        for (int size = 32; size < algorithmDataset.datasetSize(); size = 2 * size)  { 
              
            // pick starting point of left sub array. We  
            // are going to merge arr[left..left+size-1]  
            // and arr[left+size, left+2*size-1]  
            // After every merge, we increase left by 2*size  
            for (int left = 0; left < algorithmDataset.datasetSize(); left += 2 * size)  
            { 
                  
                // find ending point of left sub array  
                // mid+1 is starting point of right sub array  
                int mid = left + size - 1; 
                int right = Math.min((left + 2 * size - 1), (algorithmDataset.datasetSize() - 1)); 
  
                // merge sub array arr[left.....mid] &  
                // arr[mid+1....right]  
                merge(algorithmDataset, left, mid, right); 
            } 
        } 
	}

	// this function sorts array from left index to  
    // to right index which is of size atmost RUN  
    public static void insertionSort(SortbenchDataset algorithmDataset, int left, int right)  
    { 
        for (int i = left + 1; i <= right; i++)  
        { 
            int temp = algorithmDataset.datasetGet(i);
            int j = i - 1; 
            while (algorithmDataset.datasetGet(j) > temp && j >= left) { 
            	algorithmDataset.datasetSet(j + 1, algorithmDataset.datasetGet(j));
            	
                j--; 
            } 
            
            algorithmDataset.datasetSet(j + 1, temp);
        } 
    } 
  
    // merge function merges the sorted runs  
    public static void merge(SortbenchDataset algorithmDataset, int l, int m, int r){ 
  
    	// original array is broken in two parts  
        // left and right array  
        int len1 = m - l + 1, len2 = r - m; 
        int[] left = new int[len1]; 
        int[] right = new int[len2]; 
        for (int x = 0; x < len1; x++){ 
        	
            left[x] = algorithmDataset.datasetGet(l + x); 
        } 
        for (int x = 0; x < len2; x++)  { 
            right[x] = algorithmDataset.datasetGet(m + 1 + x); 
        } 
  
        int i = 0; 
        int j = 0; 
        int k = l; 
  
        // after comparing, we merge those two array  
        // in larger sub array  
        while (i < len1 && j < len2)  
        { 
            if (left[i] <= right[j])  { 
            	algorithmDataset.datasetSet(k, left[i]);
                i++; 
            } 
            
            else { 
            	algorithmDataset.datasetSet(k, right[j]);
                j++; 
            } 
            k++; 
        } 
  
        // copy remaining elements of left, if any  
        while (i < len1) { 
        	algorithmDataset.datasetSet(k, left[i]);
            k++; 
            i++; 
        } 
  
        // copy remaining element of right, if any  
        while (j < len2)  { 
        	algorithmDataset.datasetSet(k, right[j]);
            k++; 
            j++; 
        } 
    } 
  
	
	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Selection Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n^2)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
