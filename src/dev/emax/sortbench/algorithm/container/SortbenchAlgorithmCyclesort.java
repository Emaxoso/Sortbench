package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmCyclesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		 // count number of memory writes 
        int writes = 0; 
  
        // traverse array elements and put it to on 
        // the right place 
        for (int cycle_start = 0; cycle_start <= algorithmDataset.datasetSize() - 2; cycle_start++) { 
            // initialize item as starting point 
            int item = algorithmDataset.datasetGet(cycle_start); 
  
            // Find position where we put the item. We basically 
            // count all smaller elements on right side of item. 
            int pos = cycle_start; 
            for (int i = cycle_start + 1; i < algorithmDataset.datasetSize(); i++) 
                if (algorithmDataset.datasetGet(i) < item) 
                    pos++; 
  
            // If item is already in correct position 
            if (pos == cycle_start) 
                continue; 
  
            // ignore all duplicate elements 
            while (item == algorithmDataset.datasetGet(pos)) 
                pos += 1; 
  
            // put the item to it's right position 
            if (pos != cycle_start) { 
                int temp = item; 
                item = algorithmDataset.datasetGet(pos); 
                algorithmDataset.datasetSet(pos, temp); 
                writes++; 
            } 
  
            // Rotate rest of the cycle 
            while (pos != cycle_start) { 
                pos = cycle_start; 
  
                // Find position where we put the element 
                for (int i = cycle_start + 1; i < algorithmDataset.datasetSize(); i++) 
                    if (algorithmDataset.datasetGet(i) < item) 
                        pos += 1; 
  
                // ignore all duplicate elements 
                while (item == algorithmDataset.datasetGet(pos)) 
                    pos += 1; 
  
                // put the item to it's right position 
                if (item != algorithmDataset.datasetGet(pos)) { 
                    int temp = item; 
                    item = algorithmDataset.datasetGet(pos); 
                    algorithmDataset.datasetSet(pos, temp); 
                    writes++; 
                } 
            } 
        } 
	}


	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Cycle Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n)")
				.algorithmCaseWorst("O(kN)")
				.build();
	}


}
