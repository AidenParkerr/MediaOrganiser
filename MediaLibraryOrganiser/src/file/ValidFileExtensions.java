package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidFileExtensions {

	private static String[] movieExtensions = { ".mkv", ".mpeg-2", ".avchd", ".wmv", ".webm", ".mpg", ".mpeg", ".mpe",
			".mpv", ".mp4", ".m4v", ".avi", ".wmv", ".mov", ".flv", };
	private static String[] musicExtensions = { ".pcm", ".mp3", ".wav", ".m4a", ".flac", ".wma", ".aac", ".aiff",
			".ogg", ".alac", ".webm", };
	private static String[] imageExtensions = { ".png", ".jpeg", ".jpg", ".jfif", ".bmp", ".tif", ".tiff", ".bmp",
			".gif", ".eps", };

	public List<String> getAllMediaExtensions() {
		List<String> allExt = new ArrayList<String>();
		allExt.addAll(Arrays.asList(movieExtensions));
		allExt.addAll(Arrays.asList(musicExtensions));
		allExt.addAll(Arrays.asList(imageExtensions));
		return allExt;
	}

	public String[] getMovieExtensions() {
		return movieExtensions;
	}

	public String[] getMusicExtensions() {
		return musicExtensions;
	}

	public String[] getImageExtensions() {
		return imageExtensions;
	}

}
