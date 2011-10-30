package org.chickymate.server.util;

public class ResourceAnalyzer {

	public static boolean isImage(String path) {
		String pathLowerCase = path.toLowerCase();
		if (path != null && path.length() > 3) {
			if (pathLowerCase.endsWith(".jpeg")
					|| pathLowerCase.endsWith(".png")
					|| pathLowerCase.endsWith(".jpg")
					|| pathLowerCase.endsWith(".gif")
					|| pathLowerCase.endsWith(".bmp")) {
				return true;
			}
		}
		return false;
	}
}
