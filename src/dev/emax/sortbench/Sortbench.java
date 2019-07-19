package dev.emax.sortbench;

import javax.swing.UIManager;

import dev.emax.sortbench.ui.SortbenchFrame;
import lombok.Getter;

public class Sortbench {

	@Getter
	private static SortbenchFrame sortbenchFrame;

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		sortbenchFrame = new SortbenchFrame();
	}


}
