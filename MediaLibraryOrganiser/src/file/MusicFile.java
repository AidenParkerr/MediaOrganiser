package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Vector;

import pathchecker.FolderContents;

public class MusicFile extends MediaFile {
	private Vector<Vector<String>> musicFiles;
	FolderContents fc = new FolderContents();
	ValidFileExtensions extensions = new ValidFileExtensions();
	private String[] musicExtensions = extensions.getMusicExtensions();

	

	
	

	public Vector<Vector<String>> getMusicFiles() {
		return musicFiles;
	}

}
