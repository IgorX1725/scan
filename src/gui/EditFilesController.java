package gui;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.GridWithImages;
import utils.ImageFileToFXImage;
// Controlador da GUI EditFiles.fxml
public class EditFilesController {

	private static Scene editFilesScene = null;
	private static BorderPane root = null;
	private static Stage stage = null;
	private static ScrollPane scrollImages = null;
	private final static Set<KeyCode> pressedKeys = new HashSet<>();
	
	//Metodo para exibir a janela de edição dos documentos do lote
	public static void showDisplayEditWindow(FXMLLoader fxml, File[] list) {
		try {
			if (stage == null) {
				root = fxml.load();
				editFilesScene = new Scene(root);
				ImageFileToFXImage.tiffToImageList(list);
				stage = new Stage();
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Main.stage);
				stage.setTitle("Editar Documentos");
				stage.setScene(editFilesScene);
				stage.setMaximized(true);
				stage.show();
				scrollImages = createPaneImages();
				root.setRight(scrollImages);
			}else {
				ImageFileToFXImage.tiffToImageList(list);
				updateImages();
				stage.show();
			}
			editFilesScene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
			editFilesScene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	//Metodo para atualizar a visualização dos documentos quando houver alguma alteração no lote
	public static void updateImages() {
		root.setRight(null);
		ScrollPane paneImages = createPaneImages();
		root.setRight(paneImages);

	}
// Metodo para criar o painel das imagens presentes no lote
	private static ScrollPane createPaneImages() {
		Double Width = root.getWidth() / 2;
		Double height = root.getHeight()
				- (((AnchorPane) root.getTop()).getHeight() + ((ToolBar) root.getBottom()).getHeight());
		ScrollPane paneImages = new ScrollPane(GridWithImages.create());
		paneImages.setPrefSize(Width, height);
		return paneImages;
	}
	
	public static Set<KeyCode> getPressedkeys() {
		return pressedKeys;
	}

	public static Scene getEditFilesScene() {
		return editFilesScene;
	}

	public static Stage getEditFilesStage() {
		return stage;
	}

	public static void closeStage() {
		stage.close();

	}

}
