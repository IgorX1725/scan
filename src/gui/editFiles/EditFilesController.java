package gui.editFiles;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import application.Main;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AddIconOnScene;
import utils.ImageFileToFXImage;
import utils.ListDocuments;

// Controlador da GUI EditFiles.fxml
public class EditFilesController {

	@FXML
	private AnchorPane anchorPanelistDocuments = null;

	private static Scene scene = null;
	private static VBox root = null;
	private static Stage stage = null;
	private final static Set<KeyCode> pressedKeys = new HashSet<>();
	private static FXMLLoader topLoader;
	private static FXMLLoader bottonLoader;
	private static FXMLLoader leftLoader;
	private static AnchorPane top = null;
	private static ToolBar botton = null;
	private static ScrollPane left = null;
	private static ScrollPane right = null;
	private static GridPane panes = null;
	private static Button indexButton;
	private static File batch;

	// Metodo para exibir a janela de edição dos documentos do lote
	public static void showDisplayEditWindow(File[] list) {
		setBatch(list[0].getParentFile());
		try {
			topLoader = new FXMLLoader(EditFilesController.class.getResource("Top.fxml"));
			bottonLoader = new FXMLLoader(EditFilesController.class.getResource("Botton.fxml"));
			leftLoader = new FXMLLoader(EditFilesController.class.getResource("Left.fxml"));
				ListDocuments.getInstance().clear();
				ImageFileToFXImage.tiffToImageList(list);
				root = new VBox();
				setTop(topLoader.load());
				// EventListener para centralizar o bottão "indexar" quando o tamanho da janela
				// é reajustado
				indexButton = (Button) top.lookup("#index");
				top.widthProperty().addListener(
						(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
							indexButton.setLayoutX(newValue.doubleValue() / 2
									- (indexButton.widthProperty().getValue() / 2));
						});
				panes = new GridPane();
				setBotton(bottonLoader.load());
				setLeft(leftLoader.load());
				setRight(RightController.createPaneImages());
				
				panes.setPrefHeight(root.getHeight() - top.getHeight() - botton.getHeight());
				root.getChildren().add(top);
				root.getChildren().add(panes);
				root.getChildren().add(botton);
				scene = new Scene(root);
				stage = new Stage();
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Main.getStage());
				stage.setTitle("Editar Documentos");
				stage.setScene(scene);
				stage.setMaximized(true);
				AddIconOnScene.add(stage, new Image("\\icons\\X_Icon.png"));
				stage.show();				
				panes.setPrefHeight(root.getHeight() - top.getHeight() - botton.getHeight());
				left.setMinWidth(root.getWidth() / 4);
				left.setMinHeight(root.getHeight() / 2);
				right.setMinWidth(root.getWidth() / 4);
				right.setMinHeight(root.getHeight() / 2);
				right.prefWidthProperty().bind(panes.widthProperty());
				right.prefHeightProperty().bind(panes.heightProperty());
				left.prefWidthProperty().bind(panes.widthProperty());
				left.prefHeightProperty().bind(panes.heightProperty());
				stage.setMinWidth(root.getWidth() / 2);
				stage.setMinHeight(root.getHeight()/1.5);
			scene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
			scene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static VBox getRoot() {
		return root;
	}

	public static Set<KeyCode> getPressedkeys() {
		return pressedKeys;
	}

	public static Scene getEditFilesScene() {
		return scene;
	}

	public static Stage getStage() {
		return stage;
	}

	public static ScrollPane getRight() {
		return right;
	}
	

	public static ScrollPane getLeft() {
		return left;
	}

	public static void setLeft(ScrollPane left) {
		EditFilesController.left = left;
		EditFilesController.left.setMinWidth(root.getWidth() / 2);
		panes.add(left,1,1);
		AnchorPane.setLeftAnchor(left, 0.0);
		AnchorPane.setBottomAnchor(left, 0.0);
		AnchorPane.setTopAnchor(left, 0.0);
	}

	public static void setRight(ScrollPane right) {
		EditFilesController.right = right;
		if(panes.getChildren().contains(right)) {
			panes.getChildren().remove(1);
		}
		panes.add(right,2,1);
		AnchorPane.setRightAnchor(right, 0.0);
		AnchorPane.setTopAnchor(right, 0.0);
		AnchorPane.setBottomAnchor(right, 0.0);
	}

	public static AnchorPane getTop() {
		return top;
	}

	public static void setTop(AnchorPane top) {
		EditFilesController.top = top;
	}

	public static ToolBar getBotton() {
		return botton;
	}

	public static void setBotton(ToolBar botton) {
		EditFilesController.botton = botton;
	}

	public static File getBatch() {
		return batch;
	}

	public static void setBatch(File batch) {
		EditFilesController.batch = batch;
	}
	
	
}