package dev.emax.sortbench.ui;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.tester.SortbenchTesterResultMultitest;
import dev.emax.sortbench.tester.SortbenchTesterResultTest;
import dev.emax.sortbench.tester.SortbenchTesterResultTests;

public class SortbenchTableModelTest implements TableModel {

	private LinkedList<TableModelListener> resultListeners;

	private Object[][] resultData;

	public SortbenchTableModelTest() {
		 resultListeners = new LinkedList<TableModelListener>();
	}

	public void setTestResult(SortbenchTesterResultMultitest resultMultitest) {
		SortbenchAlgorithm[] resultAlgorithms = resultMultitest.getResultAlgorithms();
		resultData = new Object[resultAlgorithms.length][];

		for (int resultAlgorithmIndex = 0; resultAlgorithmIndex < resultAlgorithms.length; resultAlgorithmIndex++) {
			SortbenchAlgorithm resultAlgorithm = resultAlgorithms[resultAlgorithmIndex];
			SortbenchTesterResultTests resultTests = resultMultitest.getResultAlgorithm(resultAlgorithm);
			SortbenchTesterResultTest[] resultTestsData = resultTests.getTestsResult();

			resultData[resultAlgorithmIndex] = new Object[(resultTestsData.length * 2) + 3];
			resultData[resultAlgorithmIndex][0] = resultAlgorithm.getAlgorithmName();
			resultData[resultAlgorithmIndex][1] = resultTests.getResultAverageOperations();
			resultData[resultAlgorithmIndex][2] = resultTests.getResultAverageTime();

			for (int resultTestIndex = 0; resultTestIndex < resultTestsData.length; resultTestIndex++) {
				SortbenchTesterResultTest resultTest = resultTestsData[resultTestIndex];

				resultData[resultAlgorithmIndex][3 + (resultTestIndex * 2) + 0] = resultTest.getResultOperations();
				resultData[resultAlgorithmIndex][3 + (resultTestIndex * 2) + 1] = resultTest.getResultTime();
			}
		}

		fireModelChange(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}


	@Override
	public int getRowCount() {
		return resultData == null ? 0
				: resultData.length;
	}

	@Override
	public int getColumnCount() {
		return resultData == null ? 0
				: resultData[0].length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnIndex == 0 ? "Algoritmo"
			 : columnIndex == 1 ? "Avg Ops"
			 : columnIndex == 2 ? "Avg Time"
			 : ("#" + (columnIndex - 3) + (columnIndex % 2 != 0 ? " Ops" : " Time"));
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnIndex == 0 ? String.class
			 : columnIndex == 1 ? Double.class
			 : columnIndex == 2 ? Double.class
		     : Integer.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return resultData[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

	private void fireModelChange(TableModelEvent e) {
		resultListeners.forEach(l -> l.tableChanged(e));
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		resultListeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		resultListeners.remove(l);
	}

}
