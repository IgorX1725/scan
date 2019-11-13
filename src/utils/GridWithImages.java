package utils;

import entities.DocumentX;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//Classe responsável por criar o Painel Grid das imagens imagens do lote em miniatura 
public class GridWithImages {

	static GridPane gridPane = null;
	public static MousePressedEvent mousePressedEvent = new MousePressedEvent();

	public static GridPane create() {
			gridPane = new GridPane();
			int x = 0;
			int y = 0;
			for (DocumentX image : ListDocuments.getInstance()) {
				if (x > 2) {
					y++;
					x = 0;
				}
				StackPane stackImage = new StackPane(ImageViewGenerator.create(image.getImageFX(), 200, 300));
				image.setStackImage(stackImage);
				stackImage.setOnMousePressed(mousePressedEvent);
				gridPane.add(stackImage, x, y);
				GridPane.setMargin(stackImage, new Insets(7, 50, 7, 50));
				x++;
			}
		return gridPane;
	}

	public static GridPane getGridPane() {
		return gridPane;
	}

}
