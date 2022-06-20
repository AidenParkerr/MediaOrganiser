package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

public class MediaFile {
	private Vector<Vector<String>> fileObjects = new Vector<Vector<String>>();
	private Vector<Vector<String>> folderObjects = new Vector<Vector<String>>();
	private String name = "";
	private String type = "";
	private long size;

	public String getName() {
		return name;
	}

	public void setFileName(Path file) {
		this.name = file.getFileName().toString().substring(0, file.getFileName().toString().lastIndexOf("."));
	}
	
	public void setFolderName(Path folder) {
		this.name = folder.getFileName().toString();
	}

	public String getType() {
		return type;
	}

	public void setFileType(Path file) {
		String fileString = file.toString();
		this.type = fileString.substring(fileString.lastIndexOf("."), fileString.length());
	}

	public long getSizeInMB() {
		return (size / 1024) / 1024;
	}

	public void setFileSize(Path file) {
		try {
			size = Files.size(file);
		} catch (IOException e) {

		}
	}
	
	public void createFileObjects(List<Path> files) {
		for (Path file : files) {
			Vector<String> fileDetails = new Vector<String>(3);
			MediaFile fileObj = new MediaFile();
			fileObj.setFileName(file);
			fileObj.setFileSize(file);
			fileObj.setFileType(file);
			fileDetails.add(fileObj.getName());
			fileDetails.add(fileObj.getType());
			fileDetails.add(String.valueOf(fileObj.getSizeInMB()+ " MB"));
			fileObjects.add(fileDetails);

		}
	}

	public void createFolderObjects(List<Path> folders) {
		for (Path folder : folders) {
			Vector<String> folderDetails = new Vector<String>(2);
			MediaFile folderObj = new MediaFile();
			folderObj.setFolderName(folder);
			folderDetails.add(folderObj.getName());
			folderObjects.add(folderDetails);
		}
	}
	
	/**
	 * Sets
	 */
	public void clearFileObjectsVector() {
		fileObjects.clear();
	}
	public void clearFolderObjectsVector() {
		folderObjects.clear();
	}

	public void clearVectors() {
		fileObjects.clear();
		folderObjects.clear();
	}
	
	public Vector<Vector<String>> getFolderObjects() {
		return this.folderObjects;
	}
	
	
	/**
	 * Retr
	 * @return fileObjects a 2D vector that contains the name, size and type of a file/
	 */
	public Vector<Vector<String>> getFileObjects() {
		return this.fileObjects;
	}
}
