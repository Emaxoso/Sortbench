package dev.emax.sortbench.ui;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmInfo;
import dev.emax.sortbench.algorithm.SortbenchAlgorithmRegistry;

public class SortbenchTableModelAlgorithms implements TableModel {

	/**
	 * Lo stato di abilitazione degli algoritmi
	 */
	private boolean[] algorithmsEnabled;

	public SortbenchTableModelAlgorithms() {
		algorithmsEnabled = new boolean[getRowCount()];
		Arrays.fill(algorithmsEnabled, true);
	}

	/**
	 * Ritorna gli algoritmi abilitati
	 *
	 * @return gli algoritmi abilitati
	 */
	public SortbenchAlgorithm[] getAlgorithms() {
		return IntStream.range(0, algorithmsEnabled.length)
			.filter(i -> algorithmsEnabled[i])
			.mapToObj(i -> SortbenchAlgorithmRegistry.getAlgorithm(i))
			.toArray(SortbenchAlgorithm[]::new);
	}

	@Override
	public int getRowCount() {
		return SortbenchAlgorithmRegistry.getAlgorithmsCount();
	}

	@Override
	public int getColumnCount() {
		return SortbenchAlgorithmInfo.getInfoCount() + 1;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnIndex > 0 ? SortbenchAlgorithmInfo.getInfoRegistry()[columnIndex - 1] : "Abilitato";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnIndex == 0 ? Boolean.class : String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return algorithmsEnabled[rowIndex];
		}
		return SortbenchAlgorithmRegistry
				.getAlgorithm(rowIndex)
				.algorithmInfo()
				.getAlgorithmInfos()[columnIndex - 1];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		algorithmsEnabled[rowIndex] = (boolean) aValue;
	}

	public void addTableModelListener(TableModelListener l) {}
	public void removeTableModelListener(TableModelListener l) {}

}
