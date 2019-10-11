package utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.StackPane;

public class ListImagesSelected {

	private static List<StackPane> images = null;

	private ListImagesSelected() {}
	
	public static List<StackPane> getInstance(){
		if(images == null) {
			images = new ArrayList<StackPane>();
			return images;
		}else {
			return images;
		}
	}

}
