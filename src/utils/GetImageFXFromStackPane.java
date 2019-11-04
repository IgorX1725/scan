package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

//Classe responsável por obter a Imagem que está contida dentro do Stackpane
public class GetImageFXFromStackPane {
	
	public static Image get(StackPane stackPane) {
		return ((ImageView) stackPane.getChildren().get(0)).getImage();
	
	}
}
