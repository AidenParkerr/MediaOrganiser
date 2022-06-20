package gui;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pathchecker.FolderContents;

public class MediaFileTable {
	private static FolderContents folderContent = new FolderContents();
	private static JTable table;
	private Vector<Vector<String>> mediaFiles;

	/**
	 * Creates and populates a {@code JTable} with a list of {@code Vector} values.
	 * 
	 * @param files a 2D {@code Vector} containing the values to be added to the
	 *              {@code JTable}. The values to be added are the details of the
	 *              file i.e File Name, Type, Size.
	 * @return scrollPane the {@code ScrollPane} containing the created and
	 *         populated {@code JTable}
	 */
	public JScrollPane createMediaTable(Vector<Vector<String>> files) {
		this.mediaFiles = files;

		String column[] = { "Name", "Type", "Size" };

		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(column, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Vector<String> fileDetails : files)
			tableModel.addRow(fileDetails);

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	/**
	 * Checks if the {@code JTable} has been initialised and if there are any rows
	 * selected. Returns an {@code int} array of the number id of the rows selected,
	 * otherwise, returns null if the {@code JTable} is not created or there are no
	 * rows selected.
	 * 
	 * @return selectedRows the selected rows of the {@code JTable}
	 */

	public int[] getSelectedRows() {
		if (table == null || table.getSelectedRows().length < 1) {
			return null;
		}
		return table.getSelectedRows();
	}

	/**
	 * 
	 * @return selectedFiles
	 */
	protected List<Path> getPathValuesOfRowsSelected() {
		List<Path> selectedFiles = new ArrayList<Path>();

		for (int row : table.getSelectedRows()) {
			Path file = folderContent.getMediaFilePaths().get(row); // abs path of the file selected
			selectedFiles.add(file);
		}
		return selectedFiles;
	}

	public void createMusicTable() {
		for (Vector<String> file : mediaFiles)
			;
//			System.out.println(file);
	}

	public void createVideoTable() {

	}

	public void createImageTable() {

	}
}
