package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;



public class MapImages {
	
	private static Map<Image, File> mapImages = null;
	
	private MapImages() {}

	public static Map<Image, File> getInstance() {
		if(mapImages == null) {
			mapImages = new HashMap<Image, File>();
			return mapImages;
		}
		return mapImages;
	}
	
	
}