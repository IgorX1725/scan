package utils;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//classe responsável por gerar o circulo verde no documento para identificar que o mesmo já foi indexado
public class AddCheck {

	private static Circle circle = null;
	private static int x = 0;
	private static int y = 0;

	public static void greenCheck(StackPane image) {
		x = (int) ((int) image.getWidth() * 0.1);
		y = (int) ((int) image.getHeight() * 0.1);
		circle = new Circle(x, y, 20, Color.GREEN);
		image.getChildren().add(circle);
		StackPane.setAlignment(circle, Pos.BOTTOM_RIGHT);
	}
}
