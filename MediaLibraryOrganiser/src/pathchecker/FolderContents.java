package pathchecker;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import file.ValidFileExtensions;

public class FolderContents {

	private ValidFileExtensions extensions = new ValidFileExtensions();
	private List<String> mediaExtensions = extensions.getAllMediaExtensions();
	private DirectoryStream<Path> directory;
	private static List<Path> mediaFilePaths;
	private static List<Path> folderPaths;
	private Path folderChosen;
	private int fileCount = 0;

	/**
	 * Returns an <code>ArrayList</code> of <code>Path</code> values that point to
	 * the location of the media files found.
	 * 
	 * @param folder the folder to search for media files in
	 */

	public void setFolderContent(Path folder) {
		directory = null;
		createDirectoryStream(folder);
		folderChosen = folder;
		mediaFilePaths = new ArrayList<Path>();
		folderPaths = new ArrayList<Path>();

		if (directory != null) {
			for (Path item : directory)
				if (Files.isRegularFile(item)) {
					getFilesInPath(item);
				} else if (Files.isDirectory(item)) {
					folderPaths.add(item);
				}
		}
	}

	private void getFilesInPath(Path file) {
		for (String extension : mediaExtensions)
			if (file.getFileName().toString().endsWith(extension)) {
				mediaFilePaths.add(file);
				fileCount++;
			}
	}

	public boolean isMediaFiles() {
		if (directory == null) {
			return false;
		}
		return mediaFilePaths.size() > 0 ? true : false;
	}

	public void updateMediaFiles() {
		setFolderContent(folderChosen);
	}

	/**
	 * Returns a directory stream of the folders/files absolute paths in the path
	 * provided via the user's selection in the system explorer dialog.
	 * 
	 * @param path an absolute path of the folder to create a directory stream
	 * @return directory the directory stream for the path specified
	 */
	private void createDirectoryStream(Path path) {
		try {
			this.directory = Files.newDirectoryStream(path);
		} catch (IOException | NullPointerException | InvalidPathException e) {
			// NullPointer and InvalidPath Exceptions are probably not needed though it's
			// surely best to be safe.
			this.directory = null;
		}
	}

	public int getFileCount() {
		return this.fileCount;
	}

	public List<Path> getMediaFilePaths() {
		return mediaFilePaths;
	}

	public List<Path> getFolderPaths() {
		return folderPaths;
	}
}
