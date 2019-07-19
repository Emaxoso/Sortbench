package dev.emax.sortbench.dataset.distribution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SortbenchDistributionChart extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * I dati di esempio della distribuzione
	 */
	private int[] distributionData;

	/**
	 * Il range assoluto minimo e massimo della distribuzione
	 */
	private int distributionAbsoluteMin, distributionAbsoluteMax;

	/**
	 * Il range relativo minimo e massimo della distribuzione
	 */
	private int distributionRelativeMin, distributionRelativeMax;

	/**
	 * Il modo di visualizzare la distribuzione
	 * true 	=> Assoluto, Verde
	 * false 	=> Relativo, Rosso
	 */
	private boolean distributionMode;

	public SortbenchDistributionChart() {
		// Imposto il colore del pannello
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);

		// Aggiungo un listener che cambia la modalità di visualizzazione
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (SwingUtilities.isLeftMouseButton(event)) {
					distributionMode = !distributionMode;
					repaint();
				}
			}
		});
	}

	/**
	 * Imposta la distribuzione da visualizzare
	 * @param distribution
	 * @param distributionGeneratorConfig
	 */
	public void disitributionPlot(SortbenchDistribution<?> distribution, SortbenchDistributionConfig distributionGeneratorConfig) {

		// Inizializzo l'array che conterrà la distribuzione
		this.distributionData = new int[getWidth()];

		distribution.distributionGenerateRaw(distributionData, distributionGeneratorConfig);
		distributionAbsoluteMin = distributionGeneratorConfig.getDistributionMin();
		distributionAbsoluteMax = distributionGeneratorConfig.getDistributionMax();

		distributionRelativeMin = Integer.MAX_VALUE;
		distributionRelativeMax = Integer.MIN_VALUE;

		for (int distributionIndex = 0; distributionIndex < distributionData.length; distributionIndex++) {
			distributionRelativeMax = Math.max(distributionData[distributionIndex], distributionRelativeMax);
			distributionRelativeMin = Math.min(distributionData[distributionIndex], distributionRelativeMin);
		}

		//System.out.println("Distribuzione Assoluta : " + distributionAbsoluteMin + " | " + distributionAbsoluteMax);
		//System.out.println("Distribuzione Relativa : " + distributionRelativeMin + " | " + distributionRelativeMax);

		this.revalidate();
		this.repaint();
	}

	/**
	 * Resetta il grafico della distribuzione
	 */
	public void distributionReset() {
		this.distributionData = null;
		this.revalidate();
		this.repaint();
	}


	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		// Disegno la distribuzione se presente
		if (distributionData != null) {

			// Calcolo il range in base alla modalità (assoluta/relativa)
			int distributionRange = distributionMode ?
					(distributionAbsoluteMax - distributionAbsoluteMin) :
					(distributionRelativeMax - distributionRelativeMin);

			// Itero i dati della distribuzione e li renderizzo
			for (int distributionIndex = 0; distributionIndex < distributionData.length; distributionIndex++) {
				int distributionLineheight = distributionData[distributionIndex];
				distributionLineheight *= getHeight();
				distributionLineheight /= distributionRange == 0 ? 1 : distributionRange;

				graphics.setColor(distributionMode ? Color.GREEN : Color.RED);
				graphics.drawLine(distributionIndex, getHeight(), distributionIndex, getHeight() - distributionLineheight);
			}
		}
	}



}
