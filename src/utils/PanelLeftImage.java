package utils;

import java.io.File;

import gui.EditFilesController;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class PanelLeftImage {

	ScrollPane scrollPane = null;
	ImageView imageView = null;
	Scene editFilesScene = null;
	BorderPane root = null;
	Image imageFXRotated = null;
	File imageRotated = null;
	Image imageFromFile = null;

	public PanelLeftImage() {
		editFilesScene = EditFilesController.getEditFilesScene();
		root = (BorderPane) editFilesScene.getRoot();
		scrollPane = new ScrollPane();
	}

	public void create(Image image) {
		scrollPane.setContent(null);
		imageRotated = MapImages.getInstance().get(image);
		imageFromFile = ImageFileToFXImage.toImageFX(imageRotated);
		imageView = new ImageView(imageFromFile);
		if (imageView.getImage().getWidth() > imageView.getImage().getHeight()) {
			imageView.setFitHeight(root.getWidth() / 2);
			imageView.setFitWidth(root.getHeight() + root.getWidth() * 0.5);
		} else {
			imageView.setFitHeight(root.getHeight() + root.getHeight() * 1.5);
			imageView.setFitWidth(root.getWidth() / 2);
		}
		scrollPane.setContent(imageView);
		scrollPane.setPrefSize(root.getWidth() / 2, root.getHeight());
		root.setLeft(scrollPane);
	}

	public void update(StackPane image) {
		Image imageFX = ((ImageView) image.getChildren().get(0)).getImage();
		scrollPane.setContent(null);
		create(imageFX);
	}

	public void clearPane() {
		root.setLeft(null);
	}

}
