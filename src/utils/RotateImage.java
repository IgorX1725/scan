package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class RotateImage {

	public static final int ROTATE_LEFT = 1;
	public static final int ROTATE_RIGHT = -1;

	public static void rotate90(File input, File output, int direction) {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(input);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
			ImageReader reader = iterator.next();
			String format = reader.getFormatName();

			BufferedImage image = ImageIO.read(iis);
			int width = image.getWidth();
			int height = image.getHeight();

			BufferedImage rotated = new BufferedImage(height, width, image.getType());

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					switch (direction) {
					case ROTATE_LEFT:
						rotated.setRGB(y, (width - 1) - x, image.getRGB(x, y));
						break;
					case ROTATE_RIGHT:
						rotated.setRGB((height - 1) - y, x, image.getRGB(x, y));
					}
				}
			}
			ImageIO.write(rotated, format, output);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void rotateImageView(StackPane image, boolean direction) {
			int row = GridPane.getRowIndex(image);
			int column = GridPane.getColumnIndex(image);

			if (direction) {
				image.setRotate(image.getRotate() + 90);
			} else {
				image.setRotate(image.getRotate() - 90);
			}
			GridWithImages.getGridPane().getChildren().remove(image);
			GridWithImages.getGridPane().add(image, row, column);
	}
}
