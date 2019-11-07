package utils;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddIconOnScene {
	public static void add(Stage stage, Image image) {
		stage.getIcons().add(image); 
	}
}
