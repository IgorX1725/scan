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
			listPicture.getChildren().add(createPaneImages(listPicture));
			mainScene = new Scene(root);
			stage.setTitle("Editar Documentos");
			stage.setScene(mainScene);
			stage.setMaximized(true);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void updateImages() {
		
		AnchorPane listPicture = (AnchorPane)((BorderPane)mainScene.getRoot()).getRight();
		listPicture.getChildren().clear();
		ScrollPane paneImages = createPaneImages(listPicture);
		listPicture.getChildren().add(paneImages);
		
	}
	
	private static ScrollPane createPaneImages(AnchorPane parent) {
		ScrollPane paneImages = new ScrollPane();
		paneImages.setContent(GridWithImages.create());
		paneImages.prefWidthProperty().bind(parent.widthProperty());
		paneImages.prefHeightProperty().bind(parent.heightProperty());
		return paneImages;
	}


}
