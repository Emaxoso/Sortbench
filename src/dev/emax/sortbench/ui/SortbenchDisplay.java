package dev.emax.sortbench.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import dev.emax.sortbench.Sortbench;
import dev.emax.sortbench.algorithm.SortbenchAlgorithm;
import dev.emax.sortbench.tester.SortbenchTesterResultMultitest;
import dev.emax.sortbench.tester.SortbenchTesterResultTests;
import dev.emax.sortbench.utils.WrapLayout;

public class SortbenchDisplay {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void display(SortbenchTesterResultMultitest resultMultitest) {
		SortbenchFrame sortbenchFrame = Sortbench.getSortbenchFrame();

		JDialog displayFrame = new JDialog(sortbenchFrame.getSortbenchFrame(), true);
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setTitle("Sortbench - Display");
		displayFrame.setType(Type.UTILITY);

		JPanel displayContent = new JPanel();
		displayContent.setPreferredSize(new Dimension(800, 600));
		displayContent.setLayout(new BorderLayout(0, 0));
		displayFrame.setContentPane(displayContent);

		JPanel displayOptions = new JPanel();
		displayOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
		displayOptions.setPreferredSize(new Dimension(0, 70));
		displayOptions.setLayout(new BorderLayout(0, 0));
		displayContent.add(displayOptions, BorderLayout.SOUTH);

		JSlider displayOptionOperation = new JSlider();
		displayOptionOperation.setMaximum(resultMultitest.getResultTotalOperations());
		displayOptionOperation.setSnapToTicks(true);
		displayOptionOperation.setPaintLabels(true);
		displayOptionOperation.setPaintTicks(true);
		displayOptionOperation.setOpaque(false);
		displayOptionOperation.setMinimum(0);
		displayOptionOperation.setValue(0);
		displayOptions.add(displayOptionOperation, BorderLayout.CENTER);

		JScrollPane displayViewContentWrapper = new JScrollPane();
		displayContent.add(displayViewContentWrapper, BorderLayout.CENTER);

		JPanel displayViewContent = new JPanel();
		displayViewContent.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		displayViewContentWrapper.setViewportView(displayViewContent);


		Map<SortbenchTesterResultTests, JLabel> displayMap = new HashMap<>();
		for (SortbenchAlgorithm resultAlgorithm : resultMultitest.getResultAlgorithms()) {
			SortbenchTesterResultTests resultAlgorithmTests = resultMultitest.getResultAlgorithm(resultAlgorithm);

			JPanel displayViewPanel = new JPanel();
			displayViewPanel.setBackground(Color.WHITE);
			displayViewPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
			displayViewContent.add(displayViewPanel);
			displayViewPanel.setLayout(new BorderLayout(0, 0));

			JLabel displayViewTitle = new JLabel();
			displayViewTitle.setText(
				String.format(
					"%s (%s)",
					resultAlgorithm.getAlgorithmName(),
					resultAlgorithmTests.getResultTotalOperations()
				)
			);
			Font displayViewTitleFont = displayViewTitle.getFont();
			displayViewTitle.setFont(displayViewTitleFont.deriveFont(Font.BOLD, 12));
			displayViewTitle.setPreferredSize(new Dimension(0, 20));
			displayViewPanel.add(displayViewTitle, BorderLayout.NORTH);

			JLabel displayViewImage = new JLabel("");
			displayViewImage.setPreferredSize(new Dimension(200, 200));
			displayViewPanel.add(displayViewImage, BorderLayout.CENTER);

			displayMap.put(resultAlgorithmTests, displayViewImage);
		}

		displayOptionOperation.addChangeListener(($) -> {
			for (Entry<SortbenchTesterResultTests, JLabel> displayEntry : displayMap.entrySet()) {
				SortbenchTesterResultTests displayTests = displayEntry.getKey();
				JLabel displayView = displayEntry.getValue();

				displayView.setIcon(
					new ImageIcon(
						displayTests
							.getDatasetImage(displayOptionOperation.getValue())
							.getScaledInstance(200, 200, Image.SCALE_SMOOTH)
					)
				);
				displayFrame.revalidate();
				displayFrame.repaint();
			}
		});

		displayOptionOperation.setValue(1);
		displayOptionOperation.setValue(0);

		displayFrame.pack();
		displayFrame.setLocationRelativeTo(sortbenchFrame.getSortbenchFrame());
		displayFrame.setVisible(true);
	}

}
