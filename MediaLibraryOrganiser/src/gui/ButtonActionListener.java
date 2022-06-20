package gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import file.MediaFile;
import file.FileManipulation;
import pathchecker.FolderContents;
import pathchecker.PathValidation;

/**
 * 
 * 
 * 
 * @author Aiden Parker - 24631698
 * 
 *
 */
public class ButtonActionListener {
	private PathValidation pathVal = new PathValidation();
	private SystemExplorerDialog systemExplorer = new SystemExplorerDialog();
	private FolderContents folderContent = new FolderContents();
	private MediaFileTable fileTable = new MediaFileTable();
	private PlaylistTable playlistTable = new PlaylistTable();
	private MediaFile file = new MediaFile();
	private FileManipulation fileManip = new FileManipulation();
		
	public boolean isFolderSelected() {
		if (MediaLibraryOrganiser.getFolderLocationText().isBlank()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param folderChosen
	 * @return true if the path exists, is valid and contains media files
	 */

	private void updateGUIAndFileComponents() {
		folderContent.updateMediaFiles();
		file.createFileObjects(folderContent.getMediaFilePaths());
		file.createFolderObjects(folderContent.getFolderPaths());
		JScrollPane fileTableSP = fileTable.createMediaTable(file.getFileObjects());
		JScrollPane playlistTableSP = playlistTable.createPlaylistTable(file.getFolderObjects());
		MediaLibraryOrganiser.createTabbedPane(fileTableSP, playlistTableSP);
		MediaLibraryOrganiser.showTabbedPane();
		file.clearVectors();
	}

	/********************************
	 * Side Button Action Listeners *
	 ********************************/
	public void systemExplorer(JButton btn) {
		btn.addActionListener(e -> {
			systemExplorer.showOpenDialog();
			String folderChosen = systemExplorer.getSelectedValue();
			if (folderChosen != null) {
				if (pathVal.isValidAndIsExistingDirectory(folderChosen)) {
					MediaLibraryOrganiser.setExplorerLocationFieldText(folderChosen);
					folderContent.setFolderContent(pathVal.getPathValue());
					if (folderContent.isMediaFiles()) {
						updateGUIAndFileComponents();
					} else {
						MediaLibraryOrganiser.showNoFilesPanel();
					}

				}
			}
		});
	}

	public void fileSearch(JButton btn) {
		btn.addActionListener(e -> {
			System.out.println(MediaLibraryOrganiser.getSearchFieldText());
		});
	}

	public void createFile(JButton btn) {
		btn.addActionListener(e -> {
			if (!isFolderSelected()) {
				JOptionPane.showMessageDialog(null,
						"No source folder has been selected.\nChoose a folder to create a media file in.",
						"No Folder Chosen", JOptionPane.ERROR_MESSAGE);
			} else {
				systemExplorer.showSaveDialog();
				String valueSelected = systemExplorer.getSelectedValue();
				if (valueSelected != null)
					if (fileManip.createMediaFile(valueSelected)) {
						updateGUIAndFileComponents();
					} else {
						JOptionPane.showMessageDialog(null, "The specified file name is invalid or already exists",
								"File Not Created", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
	}

	public void deleteFile(JButton btn) {
		btn.addActionListener(e -> {
			if (fileTable.getSelectedRows() != null) {
				int opt = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the selected file(s)\nThis will be permanently deleted.");
				if (opt == 0) {
					List<Path> filesChosen = fileTable.getPathValuesOfRowsSelected();
					if (fileManip.deleteMediaFiles(filesChosen)) {
						updateGUIAndFileComponents();
						file.clearFileObjectsVector();
					} else {
						JOptionPane.showMessageDialog(null, "Could not delete one or more files!\n"
								+ "This may be because a file has already been deleted or is read-only.\nNo files have been deleted.",
								"Failed to delete one or more files", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Operation Cancelled.");
				}

			}
		});
	}

//// Add File to Play list button ActionListener //////////////
	public void addFileToPlaylist(JButton btn) {
		btn.addActionListener(e -> {
			if (fileTable.getSelectedRows() == null) {
				JOptionPane.showMessageDialog(null, "Select the file(s) you want to add to a playlist.",
						"No Values Selected", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println("add file playlist");
		});
	}

	public void deleteFileFromPlaylist(JButton btn) {
		btn.addActionListener(e -> {
			if (isFolderSelected() == true) {
				int[] playlistRowsSelected = playlistTable.getSelectedRows();
				List<Path> selectedFiles = fileTable.getPathValuesOfRowsSelected();
				List<Path> selectedPlaylists = playlistTable.getFolderValuesOfRowsSelected();
				if (selectedFiles.size() > 0 && selectedPlaylists.size() > 0) {
					System.out.println("ssssss");
				} else {
					JOptionPane.showMessageDialog(null, "Select one or more files from the \"Files\" tab and one playlist from the \"Playlist\" tab.", "Choose at least 1 file and 1 playlist", JOptionPane.ERROR_MESSAGE);
				}
//				if (fileTable.getSelectedRows() != null && playlistTable.getSelectedRow() >= 0) {
//					System.out.println("file table has rows selected and playlist table has 1 row selected");
//				} else {
//					System.out.println("ss");
//				}
			}
		});
	}

	public void createPlaylist(JButton btn) {
		btn.addActionListener(e -> {
			if (!isFolderSelected()) {
				JOptionPane.showMessageDialog(null,
						"No source folder has been selected.\nChoose a folder to create a playlist in.",
						"No Folder Chosen", JOptionPane.ERROR_MESSAGE);
			} else {
				systemExplorer.showSaveDialog();
				String value = systemExplorer.getSelectedValue();
				if (value != null) {
					if (pathVal.isPathValid(value) && fileManip.createPlaylist(pathVal.getPathValue())) {
						updateGUIAndFileComponents();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to create the playlist.", "Playlist Not Created",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void deletePlaylist(JButton btn) {
		btn.addActionListener(e -> {

			if (playlistTable.getSelectedRows() != null) {
				int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to deleted the folders selected?");
				if (opt == 0) {
					List<Path> foldersChosen = playlistTable.getFolderValuesOfRowsSelected();
					if (fileManip.deletePlaylist(foldersChosen)) {
						updateGUIAndFileComponents();
						JOptionPane.showMessageDialog(null, "Folders Deleted");
					} else {
						JOptionPane.showMessageDialog(null,
								"One or more folders could not be deleted.\nThis may be because there are already files in the folder.",
								"Failed to deleted folders", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Operation Cancelled");
				}
			}

		});
	}

}
