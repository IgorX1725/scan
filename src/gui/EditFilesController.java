package gui;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.GridWithImages;
import utils.TiffToFXImage;

public class EditFilesController {
	
	private static Scene mainScene = null;
	

	public static void showDisplayEditWindow(FXMLLoader fxml, File[] list) {
		try {
			Stage stage = new Stage();
			BorderPane root = fxml.load();
			AnchorPane listPicture = (AnchorPane) root.getRight();
			TiffToFXImage.tiffToImageList(list);
			listPicture.autosize();
			listPicture.getChildren().add(createPaneImages(listPicture.getWidth(), listPicture.getHeight()));
			mainScene = new Scene(root);
			stage.setTitle("Editar Documentos");
			stage.setScene(mainScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void updateImages() {
		
		AnchorPane listPicture = (AnchorPane)((BorderPane)mainScene.getRoot()).getRight();
		listPicture.getChildren().clear();
		ScrollPane paneImages = new ScrollPane(createPaneImages(listPicture.getWidth(), listPicture.getHeight()));
		listPicture.getChildren().add(paneImages);
		
	}
	
	private static ScrollPane createPaneImages(Double width, Double height) {
		ScrollPane paneImages = new ScrollPane();
		paneImages.setPrefWidth(width);
		paneImages.setPrefHeight(height);
		paneImages.setContent(GridWithImages.create());
		return paneImages;
	}


}
