package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GetImageFXFromStackPane {
	
	public static Image get(StackPane stackPane) {
		return ((ImageView) stackPane.getChildren().get(0)).getImage();
	
	}
}
