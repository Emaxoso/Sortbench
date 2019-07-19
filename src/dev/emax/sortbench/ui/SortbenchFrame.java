package dev.emax.sortbench.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.dataset.SortbenchDataset;
import dev.emax.sortbench.dataset.distribution.SortbenchDistribution;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionChart;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionConfig;
import dev.emax.sortbench.dataset.distribution.SortbenchDistributionRegistry;
import dev.emax.sortbench.tester.SortbenchTester;
import dev.emax.sortbench.tester.SortbenchTesterListener;
import dev.emax.sortbench.tester.SortbenchTesterResultMultitest;
import lombok.Getter;

public class SortbenchFrame {

	@Getter
	private JFrame sortbenchFrame;

	/**
	 * UI - ALGORITMI
	 */

	private JTable sortbenchAlgorithmsTable;
	private SortbenchTableModelAlgorithms sortbenchAlgorithmsModel;

	/**
	 * UI - DATASET
	 */

	private JTable sortbenchDatasetTable;
	private SortbenchTableModelDataset sortbenchDatasetModel;

	private JSpinner sortbenchDatasetsLength;
	private JSpinner sortbenchDatasetsCount;

	private SortbenchDistributionChart sortbenchDatasetDistributionChart;

	private JComboBox<Object> sortbenchDatasetDistributions;

	private JButton sortbenchDatasetDistributionConfig;
	private JButton sortbenchDatasetDistributionCompute;


	/**
	 * UI - TEST
	 */

	private JTable sortbenchTestTable;
	private SortbenchTableModelTest sortbenchTestModel;

	private JButton sortbenchTestCompute;
	private JButton sortbenchTestView;

	private JPanel sortbenchTestProgress;

	private JProgressBar sortbenchTestAlgorithmProgress;
	private JLabel sortbenchTestAlgorithmLabel;

	private JProgressBar sortbenchTestDatasetProgress;
	private JLabel sortbenchTestDatasetLabel;


	/**
	 * FIELD
	 */

	private SortbenchDistributionConfig sortbenchDistributionConfig;
	private SortbenchDistribution<?> sortbenchDistribution;

	private SortbenchTesterResultMultitest sortbenchTestResults;

	public SortbenchFrame() {
		initializeUI();

		// Se la lunghezza o il numero di dataset cambiano aggiorno il modello della tabella
		sortbenchDatasetsLength.addChangeListener(($) -> sortbenchDatasetModel.setDatasetsLength((int) sortbenchDatasetsLength.getValue()));
		sortbenchDatasetsCount.addChangeListener(($) -> sortbenchDatasetModel.setDatasetsCount((int) sortbenchDatasetsCount.getValue()));

		// Se l'algoritmo di distribuzione cambia aggiorno i campi
		sortbenchDatasetDistributions.addItemListener(($) -> {
			sortbenchDistribution = (SortbenchDistribution<?>) sortbenchDatasetDistributions.getSelectedItem();
			sortbenchDatasetDistributionChart.distributionReset();
			sortbenchDatasetDistributionCompute.setEnabled(false);
		});

		// Aggiungo gli algoritmi di distribuzione al menu
		for (SortbenchDistributionRegistry sortbenchDistribution : SortbenchDistributionRegistry.values()) {
			sortbenchDatasetDistributions.addItem(sortbenchDistribution.getSortbenchDistribution());
		}

		// Imposto il renderer del menu (in modo da visualizzare il nome della distribuzione corretto)
		sortbenchDatasetDistributions.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				super.setText(((SortbenchDistribution<?>) value).getDistributionName());
				return this;
			}
		});

		// Imposto l'azione per configurare l'algoritmo di distribuzione
		sortbenchDatasetDistributionConfig.addActionListener(($) -> {
			sortbenchDistribution.createDistributionConfig((distributionConfig) -> {
				if (distributionConfig != null) {
					sortbenchDatasetDistributionChart.disitributionPlot(sortbenchDistribution, distributionConfig);
					sortbenchDatasetDistributionCompute.setEnabled(true);
					sortbenchDistributionConfig = distributionConfig;
				}
			});
		});

		// Imposto l'azione per distribuire i dati sul modello del dataset
		sortbenchDatasetDistributionCompute.addActionListener(($) -> {
			sortbenchDatasetDistributionChart.disitributionPlot(sortbenchDistribution, sortbenchDistributionConfig);

			// Itero tutti i dataset presenti nel modello
			for (int datasetIndex = 0; datasetIndex < sortbenchDatasetModel.getDatasetsCount(); datasetIndex++) {
				// Creo una distribuzione per il dataset e la imposto nel modello
				int[] datasetData = new int[sortbenchDatasetModel.getColumnCount()];
				sortbenchDistribution.distributionGenerateRaw(datasetData, sortbenchDistributionConfig);
				sortbenchDatasetModel.setDatasetData(datasetIndex, datasetData);
			}
		});


		sortbenchTestCompute.addActionListener(($) -> {
			sortbenchTestProgress.setVisible(true);
			sortbenchTestCompute.setEnabled(false);
			sortbenchTestView.setEnabled(false);

			Thread sortbenchTestThread = new Thread(() -> {
				sortbenchTestResults = SortbenchTester.runTest(
					new SortbenchTesterListener() {

						@Override
						public void testDataset(SortbenchDataset testDataset, int testDatasetIndex, int testDatasetCount) {
							sortbenchTestDatasetProgress.setValue((int) ((100D * (testDatasetIndex + 1)) /  testDatasetCount));
							sortbenchTestDatasetLabel.setText("Dataset #" + testDatasetIndex);
						}

						@Override
						public void testAlgorithm(SortbenchAlgorithm testAlgorithm, int testAlgorithmIndex, int testAlgorithmCount) {
							sortbenchTestAlgorithmProgress.setValue((int) ((100D * (testAlgorithmIndex + 1)) / testAlgorithmCount));
							sortbenchTestAlgorithmLabel.setText(testAlgorithm.getAlgorithmName());
						}
					},
					sortbenchAlgorithmsModel.getAlgorithms(),
					sortbenchDatasetModel.getDatasets()
				);



				sortbenchTestModel.setTestResult(sortbenchTestResults);

				sortbenchTestProgress.setVisible(false);
				sortbenchTestCompute.setEnabled(true);
				sortbenchTestView.setEnabled(true);

			});
			sortbenchTestThread.setDaemon(true);
			sortbenchTestThread.start();
		});

		sortbenchTestView.addActionListener(($) -> {
			SortbenchDisplay.display(sortbenchTestResults);
		});

		sortbenchAlgorithmsTable.setModel(sortbenchAlgorithmsModel = new SortbenchTableModelAlgorithms());
		sortbenchDatasetTable.setModel(sortbenchDatasetModel = new SortbenchTableModelDataset());
		sortbenchTestTable.setModel(sortbenchTestModel = new SortbenchTableModelTest());
	}

	public void initializeUI() {
		sortbenchFrame = new JFrame();
		sortbenchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sortbenchFrame.setTitle("Sortbench");

		JPanel sortbenchContent = new JPanel();
		sortbenchContent.setPreferredSize(new Dimension(1400, 745));
		sortbenchContent.setLayout(new BorderLayout(0, 0));
		sortbenchFrame.setContentPane(sortbenchContent);

		JTabbedPane sortbenchTabs = new JTabbedPane(JTabbedPane.TOP);
		sortbenchContent.add(sortbenchTabs, BorderLayout.CENTER);

		JPanel sortbenchAlgorithmsTab = new JPanel();
		sortbenchAlgorithmsTab.setLayout(new BorderLayout(0, 0));
		sortbenchTabs.addTab("Algoritmi", null, sortbenchAlgorithmsTab, null);

		sortbenchAlgorithmsTable = new JTable();
		sortbenchAlgorithmsTab.add(new JScrollPane(sortbenchAlgorithmsTable), BorderLayout.CENTER);

		JPanel sortbenchDatasetTab = new JPanel();
		sortbenchTabs.addTab("Dataset", null, sortbenchDatasetTab, null);
		sortbenchDatasetTab.setLayout(new BorderLayout(0, 0));

		sortbenchDatasetTable = new JTable();
		sortbenchDatasetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sortbenchDatasetTable.setTableHeader(null);
		sortbenchDatasetTab.add(new JScrollPane(sortbenchDatasetTable), BorderLayout.CENTER);

		JPanel sortbenchDatasetsOptions = new JPanel();
		sortbenchDatasetsOptions.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		sortbenchDatasetTab.add(sortbenchDatasetsOptions, BorderLayout.SOUTH);

		JPanel sortbenchDatasetsOptionSize = new JPanel();
		sortbenchDatasetsOptionSize.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sortbenchDatasetsOptionSize.setBackground(Color.WHITE);
		sortbenchDatasetsOptionSize.setPreferredSize(new Dimension(180, 95));
		sortbenchDatasetsOptions.add(sortbenchDatasetsOptionSize);
		sortbenchDatasetsOptionSize.setLayout(null);

		JLabel sortbenchDatasetsOptionSizeLabelA = new JLabel("Numero Dataset");
		sortbenchDatasetsOptionSizeLabelA.setBounds(25, 10, 130, 15);
		sortbenchDatasetsOptionSize.add(sortbenchDatasetsOptionSizeLabelA);

		sortbenchDatasetsCount = new JSpinner();
		sortbenchDatasetsCount.setBounds(25, 25, 130, 20);
		sortbenchDatasetsCount.setModel(new SpinnerNumberModel(10, 1, null, 5));
		sortbenchDatasetsOptionSize.add(sortbenchDatasetsCount);

		JLabel sortbenchDatasetsOptionSizeLabelB = new JLabel("Lunghezza Dataset");
		sortbenchDatasetsOptionSizeLabelB.setBounds(25, 50, 130, 15);
		sortbenchDatasetsOptionSize.add(sortbenchDatasetsOptionSizeLabelB);

		sortbenchDatasetsLength = new JSpinner();
		sortbenchDatasetsLength.setBounds(25, 65, 130, 20);
		sortbenchDatasetsLength.setModel(new SpinnerNumberModel(10, 10, null, 5));
		sortbenchDatasetsOptionSize.add(sortbenchDatasetsLength);

		JPanel sortbenchDatasetOptionDistribution = new JPanel();
		sortbenchDatasetOptionDistribution.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sortbenchDatasetOptionDistribution.setBackground(Color.WHITE);
		sortbenchDatasetOptionDistribution.setPreferredSize(new Dimension(400, 95));
		sortbenchDatasetOptionDistribution.setLayout(null);
		sortbenchDatasetsOptions.add(sortbenchDatasetOptionDistribution);

		sortbenchDatasetDistributions = new JComboBox<>();
		sortbenchDatasetDistributions.setBounds(10, 10, 160, 25);
		sortbenchDatasetOptionDistribution.add(sortbenchDatasetDistributions);

		sortbenchDatasetDistributionConfig = new JButton("Configura");
		sortbenchDatasetDistributionConfig.setBounds(10, 40, 160, 20);
		sortbenchDatasetOptionDistribution.add(sortbenchDatasetDistributionConfig);

		sortbenchDatasetDistributionCompute = new JButton("Distribuisci");
		sortbenchDatasetDistributionCompute.setBounds(10, 65, 160, 20);
		sortbenchDatasetOptionDistribution.add(sortbenchDatasetDistributionCompute);

		sortbenchDatasetDistributionChart = new SortbenchDistributionChart();
		sortbenchDatasetDistributionChart.setBounds(180, 10, 210, 75);
		sortbenchDatasetOptionDistribution.add(sortbenchDatasetDistributionChart);

		JPanel sortbenchTestTab = new JPanel();
		sortbenchTabs.addTab("Test", null, sortbenchTestTab, null);
		sortbenchTestTab.setLayout(new BorderLayout(0, 0));

		JPanel sortbenchTestFooter = new JPanel();
		sortbenchTestFooter.setPreferredSize(new Dimension(0, 95));
		sortbenchTestTab.add(sortbenchTestFooter, BorderLayout.SOUTH);
		sortbenchTestFooter.setLayout(new BorderLayout(0, 0));

		JPanel sortbenchTestFooterProgress = new JPanel();
		sortbenchTestFooter.add(sortbenchTestFooterProgress, BorderLayout.CENTER);
		sortbenchTestFooterProgress.setLayout(null);

		sortbenchTestProgress = new JPanel();
		sortbenchTestProgress.setLayout(null);
		sortbenchTestProgress.setVisible(false);
		sortbenchTestProgress.setBounds(10, 10, 250, 75);
		sortbenchTestFooterProgress.add(sortbenchTestProgress);

		JLabel sortbenchTestFooterProgressLabelA = new JLabel("Algoritmo:");
		sortbenchTestFooterProgressLabelA.setBounds(0, 0, 60, 15);
		sortbenchTestProgress.add(sortbenchTestFooterProgressLabelA);
		sortbenchTestFooterProgressLabelA.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel sortbenchTestFooterProgressLabelB = new JLabel("Dataset:");
		sortbenchTestFooterProgressLabelB.setBounds(0, 40, 50, 15);
		sortbenchTestProgress.add(sortbenchTestFooterProgressLabelB);
		sortbenchTestFooterProgressLabelB.setFont(new Font("Tahoma", Font.BOLD, 11));

		sortbenchTestAlgorithmLabel = new JLabel("");
		sortbenchTestAlgorithmLabel.setBounds(65, 0, 135, 14);
		sortbenchTestProgress.add(sortbenchTestAlgorithmLabel);

		sortbenchTestAlgorithmProgress = new JProgressBar();
		sortbenchTestAlgorithmProgress.setBounds(0, 15, 250, 20);
		sortbenchTestProgress.add(sortbenchTestAlgorithmProgress);

		sortbenchTestDatasetLabel = new JLabel("");
		sortbenchTestDatasetLabel.setBounds(55, 40, 145, 14);
		sortbenchTestProgress.add(sortbenchTestDatasetLabel);

		sortbenchTestDatasetProgress = new JProgressBar();
		sortbenchTestDatasetProgress.setBounds(0, 55, 250, 20);
		sortbenchTestProgress.add(sortbenchTestDatasetProgress);

		JPanel sortbenchTestFooterActions = new JPanel();
		sortbenchTestFooterActions.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sortbenchTestFooterActions.setBackground(Color.WHITE);
		sortbenchTestFooterActions.setPreferredSize(new Dimension(140, 0));
		sortbenchTestFooterActions.setLayout(null);
		sortbenchTestFooter.add(sortbenchTestFooterActions, BorderLayout.EAST);

		sortbenchTestView = new JButton("Visualizza");
		sortbenchTestView.setEnabled(false);
		sortbenchTestView.setLocation(10, 20);
		sortbenchTestView.setSize(120, 25);
		sortbenchTestFooterActions.add(sortbenchTestView);

		sortbenchTestCompute = new JButton("Computa");
		sortbenchTestCompute.setLocation(10, 50);
		sortbenchTestCompute.setSize(120, 25);
		sortbenchTestFooterActions.add(sortbenchTestCompute);

		sortbenchTestTable = new JTable();
		sortbenchTestTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sortbenchTestTab.add(new JScrollPane(sortbenchTestTable), BorderLayout.CENTER);

		sortbenchFrame.pack();
		sortbenchFrame.setLocationRelativeTo(null);
		sortbenchFrame.setVisible(true);
	}


}
