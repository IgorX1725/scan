package utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PanelLeftImage {
	
	ScrollPane scrollPane = null;
	ImageView imageView = null;
	FXMLLoader loader = null;
	AnchorPane root = null;
	
	public PanelLeftImage() throws IOException {
		loader = new FXMLLoader(getClass().getResource("gui/EditFiles.fxml"));
		root =  loader.load();
		root = (AnchorPane)((BorderPane) root.getChildren().get(0)).getLeft();
	}
	
	public boolean create(Image image) {
		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		return true;
	}
}
