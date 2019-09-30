package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmSleepsort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		//sleep(algorithmDataset, 0, algorithmDataset.datasetSize() - 1);
	}

	    public void sleep(){  
	        int[] ints = {1,4,7,3,8,9,2,6,5};  
	        SortThread[] sortThreads = new SortThread[ints.length];  
	        for (int i = 0; i < sortThreads.length; i++) {  
	            sortThreads[i] = new SortThread(ints[i]);  
	        }  
	        for (int i = 0; i < sortThreads.length; i++) {  
	            sortThreads[i].start();  
	        }  
	    } 
	
	
	class SortThread extends Thread{  
	    int ms = 0;  
	    public SortThread(int ms){  
	        this.ms = ms;  
	    }  
	    public void run(){  
	        try {  
	            sleep(ms*10+10);  
	        } catch (InterruptedException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        System.out.println(ms);  
	    }  
	}

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Sleep Sort")
				.algorithmAuthor("Sei un coglione emax")
				.algorithmYear("1997")
				.algorithmCaseBest("O(n log n)")
				.algorithmCaseWorst("O(n^2)")
				.build();
	}


}
