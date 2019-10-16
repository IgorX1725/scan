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
	BorderPane root = null;
	AnchorPane left = null;
	
	public PanelLeftImage()  {
		try {
		loader = new FXMLLoader(getClass().getResource("../gui/EditFiles.fxml"));
			root =  loader.load();
			left = (AnchorPane) root.getLeft();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void create(Image image) {
		left.getChildren().clear();
		imageView = new ImageView(image);
		scrollPane = new ScrollPane(imageView);
		left.getChildren().add(scrollPane);
	}
}
