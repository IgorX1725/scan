package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewGenerator {
	private static ImageView imageView = null;

	public static ImageView create(Image image, int width, int height) {
		imageView = new ImageView(image);
		if (image.getWidth() > image.getHeight()) {
			imageView.setFitWidth(height);
			imageView.setFitHeight(width);
		} else {
			imageView.setFitWidth(width);
			imageView.setFitHeight(height);
		}
		return imageView;
	}
}
