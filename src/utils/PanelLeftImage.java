package utils;

import gui.EditFilesController;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PanelLeftImage {

	ScrollPane scrollPane = null;
	ImageView imageView = null;
	Scene editFilesScene = null;
	BorderPane root = null;
	AnchorPane left = null;

	public PanelLeftImage() {
		editFilesScene = EditFilesController.getEditFilesScene();
		root = (BorderPane) editFilesScene.getRoot();
		left = new AnchorPane();
	}

	public void create(Image image) {
		left.getChildren().clear();
		imageView = new ImageView(image);
		if (image.getWidth() > image.getHeight()) {
			imageView.setFitHeight(root.getWidth() / 2);
			imageView.setFitWidth(root.getHeight() + root.getWidth() * 0.5);
		} else {
			imageView.setFitHeight(root.getHeight() + root.getHeight() * 1.5);
			imageView.setFitWidth(root.getWidth() / 2);
		}
		scrollPane = new ScrollPane(imageView);
		scrollPane.setPrefSize(root.getWidth() / 2, root.getHeight());
		root.setLeft(scrollPane);
	}

}
