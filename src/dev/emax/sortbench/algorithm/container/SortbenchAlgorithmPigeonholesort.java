package dev.emax.sortbench.algorithm.container;

import java.util.Arrays;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmPigeonholesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int min = algorithmDataset.datasetGet(0); 
        int max = algorithmDataset.datasetGet(0); 
        int range, i, j, index;  
  
        for(int a=0; a<algorithmDataset.datasetSize(); a++) 
        { 
            if(algorithmDataset.datasetGet(a) > max) 
                max = algorithmDataset.datasetGet(a); 
            if(algorithmDataset.datasetGet(a) < min) 
                min = algorithmDataset.datasetGet(a); 
        } 
  
        range = max - min + 1; 
        int[] phole = new int[range]; 
        Arrays.fill(phole, 0); 
  
        for(i = 0; i<algorithmDataset.datasetSize(); i++) 
            phole[algorithmDataset.datasetGet(i) - min]++; 
  
          
        index = 0; 
  
        for(j = 0; j<range; j++) 
            while(phole[j]-->0) 
            	algorithmDataset.datasetSet(index++, j+min); 
	}


	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Pigeonhole Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(kN)")
				.build();
	}


}
