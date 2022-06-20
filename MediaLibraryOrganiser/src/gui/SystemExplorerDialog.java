package gui;

import java.nio.file.Path;

import javax.swing.JFileChooser;

public class SystemExplorerDialog {

	/*
	 * @TODO
	 * 
	 * When a Question Mark or Asterisks is entered into the search field of the
	 * File Chooser Dialog, it adds/modifies existing entries with the text entered.
	 * 
	 */

	private JFileChooser fc = new JFileChooser();
	private String fileSelected; // The absolute path of the file/directory selected.
	private int showDialog;
	private int fileSelectionMode;

	private void showSystemExplorerDialog() {
		// If any altercation to the start of the entry field is made, it will stay the
		// same even after dialog closes and reopens.
		fc.setCurrentDirectory(null);
		fc.setFileSelectionMode(fileSelectionMode);

		int opt = showDialog == JFileChooser.OPEN_DIALOG ? fc.showOpenDialog(null) : fc.showSaveDialog(null);

		if (opt == JFileChooser.APPROVE_OPTION) {
			fileSelected = fc.getSelectedFile().toString();
		} else {
			fileSelected = null;
		}
	}

	public void showOpenDialog() {
		fileSelectionMode = JFileChooser.DIRECTORIES_ONLY;
		showDialog = JFileChooser.OPEN_DIALOG;
		showSystemExplorerDialog();
	}

	public void showSaveDialog() {
		fileSelectionMode = JFileChooser.FILES_ONLY;
		showDialog = JFileChooser.SAVE_DIALOG;
		showSystemExplorerDialog();
	}

	/**
	 * Returns either null or the absolute path of the file selected using the
	 * system explorer.
	 * 
	 * @return absolute path of the folder/file selected if valid
	 */
	public String getSelectedValue() {
		return fileSelected;
	}

}
