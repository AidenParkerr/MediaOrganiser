package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import pathchecker.PathValidation;

public class FileManipulation {
	private PathValidation pathVal = new PathValidation();

	/**
	 * Attempts to create a file provided onto the system disk. The file provided
	 * 
	 * @param file the {@code Path} value of the file to be created
	 * @return true if the file was created successfully
	 */
	public boolean createMediaFile(String file) {
		if (!pathVal.isPathValid(file)) {
			return false;
		}
		try {
			Files.createFile(Paths.get(file));
			System.out.println("created file: " + file.toString());
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Attempts to delete the file(s) provided from the system disk. A single file
	 * or multiple files can be provided.
	 * 
	 * @param files an {@code ArrayList<Path>} of the absolute path for the file(s)
	 *              to be deleted
	 * @return true if files were deleted successfully
	 */
	public boolean deleteMediaFiles(List<Path> files) {
		try {
			for (Path file : files) {
				Files.delete(file);
				System.out.println("deleted file: " + file.toString());
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean createPlaylist(Path folder) {
		try {
			Files.createDirectory(folder);
			System.out.println("created folder " + folder.toString());
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean deletePlaylist(List<Path> folders) {
		try {
			for (Path folder : folders) {
				System.out.println(folder);
				Files.delete(folder);
			}
			return true;
		} catch (IOException e) {
			System.out.println("failed to delete folder");
			return false;
		}
	}
}
