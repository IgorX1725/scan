package utils;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GridWithImages {
	
	static GridPane gridPane;
	
	public static GridPane create() {
	gridPane = new GridPane();
	int x = 0;
	int y = 0;
	for (Image imageKey : MapImages.getInstance().keySet()) {
		if(x > 1) {
			y++;
			x = 0;
		}
		StackPane stackImage = new StackPane(ImageViewGenerator.create(imageKey,200,300));
		stackImage.setOnMouseClicked(new MousePressedEvent());
		gridPane.add(stackImage, x, y);
		GridPane.setMargin(stackImage, new Insets(7, 7, 7, 7));
		x++;
	}
	return gridPane;
	}
}
