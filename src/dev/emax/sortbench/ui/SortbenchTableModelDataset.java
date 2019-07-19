package dev.emax.sortbench.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dev.emax.sortbench.dataset.SortbenchDataset;
import dev.emax.sortbench.utils.SortbenchUtils;
import lombok.Getter;

public class SortbenchTableModelDataset implements TableModel {

	/**
	 * La lista che contiene i listener del modello
	 */
	private List<TableModelListener> datasetsModelListeners;

	/**
	 * Il numero di dataset (righe)
	 */
	@Getter
	private int datasetsCount;

	/**
	 * La dimensione dei dataset (colonne)
	 */
	@Getter
	private int datasetsLength;

	/**
	 * Il modello del dataset
	 */
	@Getter
	private int[][] datasetsModel;

	public SortbenchTableModelDataset() {
		// Inizializzo la lista che conterrà i listeners
		this.datasetsModelListeners = new ArrayList<>();

		// Inizializzo il conteggio e la lunghezza dei dataset
		this.datasetsLength = 10;
		this.datasetsCount = 10;

		// Inizializzo il modello dei dataset
		this.datasetsModel = new int[datasetsCount][datasetsLength];
	}

	/**
	 * Imposta i dati di un dataset
	 *
	 * @param datasetIndex	I'indice del dataset
	 * @param datasetData	I dati del dataset
	 */
	public void setDatasetData(int datasetIndex, int[] datasetData) {
		System.arraycopy(datasetData, 0, datasetsModel[datasetIndex], 0, datasetData.length);
		fireModelChange(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}

	/**
	 * Ridimensiona il modello del dataset senza perdere i dati
	 *
	 * @param datasetCount		Il nuovo numero di dataset
	 * @param datasetLength		La nuova lunghezza dei dataset
	 */
	private void resizeDatasetModel(int datasetCount, int datasetLength) {
		int[][] datasetModelNew = new int[datasetCount][datasetLength];
		for (int datasetIndex = 0; datasetIndex < Math.min(this.datasetsCount, datasetCount); datasetIndex++) {
			for (int datasetDataIndex = 0; datasetDataIndex < Math.min(this.datasetsLength, datasetLength); datasetDataIndex++) {
				datasetModelNew[datasetIndex][datasetDataIndex] = datasetsModel[datasetIndex][datasetDataIndex];
			}
		}

		// Aggiorno il modello e le dimensioni
		this.datasetsModel = datasetModelNew;
		this.datasetsLength = datasetLength;
		this.datasetsCount = datasetCount;

		// Richiamo l'evento di modifica del modello
		fireModelChange(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}

	/**
	 * Imposta il numero di dataset
	 *
	 * @param datasetCount	Il numero di dataset
	 */
	public void setDatasetsCount(int datasetCount) {
		resizeDatasetModel(datasetCount, datasetsLength);
	}

	/**
	 * Imposta la lunghezza dei dataset
	 *
	 * @param datasetLength	La lunghezza dei dataset
	 */
	public void setDatasetsLength(int datasetLength) {
		resizeDatasetModel(datasetsCount, datasetLength);
	}

	/**
	 * Converte il modello in un array di dataset
	 *
	 * @return	L'array di dataset presenti
	 */
	public SortbenchDataset[] getDatasets() {
		return SortbenchUtils.arrayFill(new SortbenchDataset[datasetsCount], (datasetIndex) -> {
			return new SortbenchDataset(datasetsModel[datasetIndex]);
		});
	}

	@Override
	public int getRowCount() {
		return datasetsCount;
	}

	@Override
	public int getColumnCount() {
		return datasetsLength;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return String.valueOf(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Integer.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datasetsModel[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		fireModelChange(new TableModelEvent(this, rowIndex, rowIndex));
		datasetsModel[rowIndex][columnIndex] = (int) aValue;
	}

	private void fireModelChange(TableModelEvent datasetModelEvent) {
		datasetsModelListeners.forEach(datasetModelListener -> datasetModelListener.tableChanged(datasetModelEvent));
	}

	@Override
	public void addTableModelListener(TableModelListener datasetModelListener) {
		datasetsModelListeners.add(datasetModelListener);
	}

	@Override
	public void removeTableModelListener(TableModelListener datasetModelListener) {
		datasetsModelListeners.remove(datasetModelListener);
	}

}
