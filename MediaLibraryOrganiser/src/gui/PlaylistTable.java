package gui;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pathchecker.FolderContents;

public class PlaylistTable {
	private static FolderContents folderContent = new FolderContents();
	private JTable table;
//	private List<Path> foldersPathValue; 
	
	public JScrollPane createPlaylistTable(Vector<Vector<String>> playlistDetails) {

		String[] column = { "Playlist Name" };

		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(column, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Vector<String> playlist : playlistDetails) {
			tableModel.addRow(playlist);
		}

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	public int[] getSelectedRows() {
		if (table == null || table.getSelectedRows().length < 1) {
			return null;
		}
		return table.getSelectedRows();
	}
	
	public int getSelectedRow() {
		if (table == null || table.getSelectedRow() < 0) {
			return -1;
		}
		return table.getSelectedRow();
	}
	
	protected List<Path> getFolderValuesOfRowsSelected() {
		List<Path> selectedFolders = new ArrayList<Path>();

		for (int row : table.getSelectedRows()) {
			Path file = folderContent.getFolderPaths().get(row); // abs path of the file selected
			selectedFolders.add(file);
		}
		return selectedFolders;
	}

}
