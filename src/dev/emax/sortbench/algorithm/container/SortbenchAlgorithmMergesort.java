package dev.emax.sortbench.algorithm.container;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.dataset.SortbenchDataset;

public class SortbenchAlgorithmMergesort extends SortbenchAlgorithm {

	@Override
	public void algorithmCompute(SortbenchDataset algorithmDataset) {
		int n = algorithmDataset.datasetSize();

		
		
		
		
	}
	
	 public void mergeSort(int [] array, int min, int max) {

	        if((max-min)<20) { 
	            insertionSort(array,min,max);
	        } else {
	            int medio = (min+max)/2;    // Trovo l' indice per suddividere il vettore
	            mergeSort(array,min,medio); // Primo sotto-vettore
	            mergeSort(array,medio+1,max); // Secondo sotto-vettore
	            merge(array,min,medio,max); // Fondo i due sotto-vettori
	        }
	    }

	    // Questo metodo fonde i due sotto-vettori ordinati, in un unico vettore ordinato
	    public void merge(int[] array, int min, int med, int max) {}
	        int [] a = new int[max-min+1];
	        int i1 = min;
	        int i2 = med+1;
	        int i3 = 1;
	        for(; ( i1 <= med) && (i2 < max); i3++) {
	            if(array[i2]>array[i1])) {
	                a[i3] = array[i1]; i1++;
	            }
	            else {
	                a[i3] = array[i2]; i2++;
	            }
	        }
	        for(;i1 <= med; i1++, i3++) a[i3] = array[i1];
	        for(;i2 < max; i2++, i3++) a[i3] = array[i2];
	        for(i3=1, i1=min; i1 < max; i1++, i3++)
	            array[i1] = a[i3];
	    }

	    /** Questo é l' Insertion Sort, che abbiamo giá visto, con uan sola differenza
	        ci permette di ordinare una porzione di vettore che va da min a max **/
	    public void insertionSort(int [] array, int min, int max) {}
	        for(int i = min+1; i < max; i++) {
	           int x = i;
	           int j = i-1;
	           for(; j >= min; j--) {
	               if(array[j]>array[x]) {
	                   int k = array[x];
	                   array[x] = array[j];
	                   array[j] = k;
	                   x = j;
	               } else break;
	           }
	    }

	

	@Override
	public SortbenchAlgorithmInfo algorithmInfo() {
		return SortbenchAlgorithmInfo.builder()
				.algorithmName("Merge Sort")
				.algorithmAuthor("-")
				.algorithmYear("-")
				.algorithmCaseBest("O(n^2)")
				.algorithmCaseWorst("O(n log n)")
				.build();
	}


}
