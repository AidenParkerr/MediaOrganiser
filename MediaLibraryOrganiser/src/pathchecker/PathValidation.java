package pathchecker;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidation {
	private Path pathValue = null;

	/**
	 * Returns the {@code Path} value of the folder provide as an argument in {@code isValidAndIsExistingDirectory}.
	 * 
	 * @return pathValue the folder provided converted to a {@code Path} object
	 * @see #isValidAndIsExistingDirectory
	 */
	
	public Path getPathValue() {
		return pathValue;
	}
	
	
	public boolean isPathValid(String path) {
		try {
			pathValue = Paths.get(path);
			return true;
		} catch (InvalidPathException | NullPointerException e) {
			return false;
		}
	}

	
	/**
	 * Checks the validity and existence of a path provided
	 * 
	 * @param path the path to be validated 
	 * @return true if the path can be parsed to a {@code Path} object and exists
	 */
	public boolean isValidAndIsExistingDirectory(String path) {
		if (isPathValid(path))
			if (doesPathExist(pathValue))
				if (Files.isDirectory(Paths.get(path)))
					return true;
		return false;
	}

	public boolean doesPathExist(Path path) {
		if (Files.exists(path))
			return true;
		else {
			return false;
		}
	}

}
