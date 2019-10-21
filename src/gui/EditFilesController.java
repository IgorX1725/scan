package gui;

import java.io.File;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.GridWithImages;
import utils.TiffToFXImage;

public class EditFilesController {

	private static Scene editFilesScene = null;

	public static void showDisplayEditWindow(FXMLLoader fxml, File[] list) {
		try {
			 
			BorderPane root = fxml.load();
			AnchorPane listPicture = new AnchorPane();
			TiffToFXImage.tiffToImageList(list);
			listPicture.getChildren().add(createPaneImages(listPicture));
			listPicture.setMaxWidth(root.getWidth()/2);
			root.setRight(listPicture);
			editFilesScene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.stage);
			stage.setTitle("Editar Documentos");
			stage.setScene(editFilesScene);
			stage.setMaximized(true);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void updateImages() {

		AnchorPane listPicture = (AnchorPane) ((BorderPane) editFilesScene.getRoot()).getRight();
		listPicture.getChildren().clear();
		ScrollPane paneImages = createPaneImages(listPicture);
		listPicture.getChildren().add(paneImages);

	}

	private static ScrollPane createPaneImages(AnchorPane parent) {
		ScrollPane paneImages = new ScrollPane();
		paneImages.setContent(GridWithImages.create());
		return paneImages;
	}
	
	public static Scene getEditFilesScene() {
		return editFilesScene;
	}
}
