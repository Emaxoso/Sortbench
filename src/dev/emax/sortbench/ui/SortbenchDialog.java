package dev.emax.sortbench.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class SortbenchDialog {

	/**
	 * Il frame della finestra di dialogo
	 */
	private JDialog dialogFrame;

	/**
	 * Il corpo della finestra di dialogo
	 */
	private JPanel dialogBody;

	/**
	 * La label del titolo del dialogo
	 */
	private JLabel dialogTitle;

	/**
	 * Il pulsante cancella del dialogo
	 */
	private JButton dialogCancel;

	/**
	 * Il pulsante conferma del dialogo
	 */
	private JButton dialogConfirm;

	/**
	 * Posizione dei componenti nel body
	 *
	 * @see #dialogComponentAdd(String, String, JComponent)
	 */
	private int sortbenchComponentY;


	public SortbenchDialog(SortbenchFrame dialogParent) {
		dialogFrame = new JDialog(dialogParent.getSortbenchFrame(), true);
		dialogFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialogFrame.setTitle("Sortbench");
		dialogFrame.setType(Type.UTILITY);
		dialogFrame.setResizable(false);

		JPanel dialogContent = new JPanel();
		dialogContent.setPreferredSize(new Dimension(400, 500));
		dialogContent.setLayout(new BorderLayout(0, 0));
		dialogFrame.setContentPane(dialogContent);

		JPanel dialogTitlePanel = new JPanel();
		dialogTitlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		dialogTitlePanel.setBackground(Color.WHITE);
		dialogTitlePanel.setPreferredSize(new Dimension(0, 70));
		dialogContent.add(dialogTitlePanel, BorderLayout.NORTH);
		dialogTitlePanel.setLayout(null);

		Font sortbenchDialogFont = dialogContent.getFont();

		dialogTitle = new JLabel("Titolo");
		dialogTitle.setFont(sortbenchDialogFont.deriveFont(Font.BOLD, 25));
		dialogTitle.setBounds(10, 30, 380, 35);
		dialogTitlePanel.add(dialogTitle);

		JPanel dialogFooter = new JPanel();
		dialogFooter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		dialogFooter.setBackground(Color.WHITE);
		dialogFooter.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		dialogContent.add(dialogFooter, BorderLayout.SOUTH);

		dialogCancel = new JButton("Cancella");
		dialogCancel.setPreferredSize(new Dimension(90, 25));
		dialogFooter.add(dialogCancel);

		dialogConfirm = new JButton("Ok");
		dialogConfirm.setPreferredSize(new Dimension(90, 25));
		dialogFooter.add(dialogConfirm);

		dialogBody = new JPanel();
		dialogContent.add(dialogBody, BorderLayout.CENTER);
		dialogBody.setLayout(null);

		dialogFrame.pack();
		dialogFrame.setLocationRelativeTo(dialogParent.getSortbenchFrame());
	}

	/**
	 * Imposta il titolo del dialogo
	 *
	 * @param dialogTitleText	Il titolo del dialogo
	 */
	public void setDialogTitle(String dialogTitleText) {
		dialogTitle.setText(dialogTitleText);
	}

	/**
	 * Aggiunge un azione da effettuare alla conferma
	 *
	 * @param dialogConfirmAction	L'azione da effettuare alla conferma
	 */
	public void addDialogConfirm(Runnable dialogConfirmAction) {
		dialogConfirm.addActionListener(($) -> {
			dialogConfirmAction.run();
			dialogFrame.dispose();
		});

	}

	/**
	 * Aggiunge un azione da effettuare alla cancellazione
	 *
	 * @param dialogConfirmAction	L'azione da effettuare alla cancellazione
	 */
	public void addDialogCancel(Runnable dialogCancelAction) {
		dialogCancel.addActionListener(($) -> {
			dialogCancelAction.run();
			dialogFrame.dispose();
		});

	}

	/**
	 * Aggiunge un componente al dialogo
	 *
	 * @param componentTitle		Il titolo del componente
	 * @param componentInfo			Le informazioni del componente
	 * @param componentElement		Il componente da aggiungere
	 */
	public void dialogComponentAdd(String componentTitle, String componentInfo, JComponent componentElement) {
		// Creo la label del titolo del componente e l'aggiungo al body
		JLabel sortbenchComponentTitle = new JLabel(componentTitle);
		Font sortbenchComponentTitleFont = sortbenchComponentTitle.getFont();
		sortbenchComponentTitle.setFont(sortbenchComponentTitleFont.deriveFont(Font.BOLD, 12));
		sortbenchComponentTitle.setBounds(10, sortbenchComponentY + 10, 380, 15);
		dialogBody.add(sortbenchComponentTitle);

		// Creo la label di informazioni del componente e l'aggiungo al body
		JLabel sortbenchComponentInfo = new JLabel(componentInfo);
		sortbenchComponentInfo.setBounds(10, sortbenchComponentY + 25, 380, 14);
		dialogBody.add(sortbenchComponentInfo);

		// Imposto la dimensione del componente e l'aggiungo al body
		componentElement.setBounds(10, sortbenchComponentY + 45, 380, componentElement.getHeight());
		dialogBody.add(componentElement);

		// Ridisegno il dialogo
		dialogBody.repaint();
		dialogBody.revalidate();

		// Incremento il contatore della posizione per l'aggiunta di nuovi componenti
		sortbenchComponentY += 50 + componentElement.getHeight();
	}

	/**
	 * Termina il setup della finestra di dialogo e la mostra
	 */
	public void dialogComplete() {
		dialogFrame.setVisible(true);
	}

}
