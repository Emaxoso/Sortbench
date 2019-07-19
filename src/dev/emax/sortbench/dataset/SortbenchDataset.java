package dev.emax.sortbench.dataset;

import java.util.Arrays;
import java.util.LinkedList;

import dev.emax.sortbench.utils.SortbenchUtils;

public class SortbenchDataset {

    /**
     * La lista che contiene tutte le operazioni effettutate sul dataset
     */
    private final LinkedList<SortbenchDatasetOperation> datasetOperations;

    /**
     * Il dataset originale (non ordinato)
     */
    private final int[] datasetDataOriginal;

    /**
     * Il dataset modificabile
     */
    private int[] datasetData;

    /**
     * Inizializza il dataset
     *
     * @param datasetData	Lo stato iniziale del dataset
     */
    public SortbenchDataset(int[] datasetData) {
        this.datasetOperations = new LinkedList<>();
        this.datasetDataOriginal = datasetData;
        this.datasetReset();
    }

    /**
     * Ritorna la lunghezza del dataset
     *
     * @return la lunghezza del dataset
     */
    public int datasetSize() {
    	return datasetData.length;
    }

    /**
     * Ritorna il dato presente all'indice
     *
     * @param 	dataIndex	L'indice del dato da ottenere
     * @return				Il dato presente all'indice
     */
    public int datasetGet(int dataIndex) {
        return datasetData[dataIndex];
    }

    /**
     * Imposta un valore all'indice
     *
     * @param dataIndex	L'indice del valore da impostare
     * @param dataValue	Il valore da impostare all'indice
     */
    public void datasetSet(int dataIndex, int dataValue) {
    	SortbenchDatasetOperationSet datasetOperation = new SortbenchDatasetOperationSet(dataIndex, dataValue);
    	datasetOperation.operationRun(datasetData);
    	datasetOperations.add(datasetOperation);
    }

    /**
     * Esegue uno swap sul dataset
     *
     * @param dataIndexA	L'indice del dato A da scambiare con il dato all'indice B
     * @param dataIndexB	L'indice del dato B da scambiare con il dato all'indice A
     */
    public void datasetSwap(int dataIndexA, int dataIndexB) {
    	SortbenchDatasetOperationSwap datasetOperation = new SortbenchDatasetOperationSwap(dataIndexA, dataIndexB);
    	datasetOperation.operationRun(datasetData);
        datasetOperations.add(datasetOperation);
    }

    /**
     * Resetta il dataset allo stato originale
     */
    public void datasetReset() {
    	datasetData = SortbenchUtils.arrayClone(datasetDataOriginal);
    	datasetOperations.clear();
    }

    /**
     * Crea uno snapshot dello stato stato del dataset.
     * Contiene le operazioni effettuate e lo stato originale del dataset
     *
     * @return	Lo snapshot dello stato del dataset
     */
    public SortbenchDatasetSnapshot datasetSnapshot() {
        if (SortbenchUtils.arraySortTest(datasetData)) {
            return new SortbenchDatasetSnapshot(
				SortbenchUtils.arrayList(SortbenchDatasetOperation.class, datasetOperations),
				SortbenchUtils.arrayClone(datasetDataOriginal)
            );
        } else throw new IllegalStateException("Oh botta guarda che l'array non è ordinato! -> " + Arrays.toString(datasetData));
    }

}
