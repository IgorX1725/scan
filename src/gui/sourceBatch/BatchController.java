package gui.sourceBatch;


import java.io.IOException;

import application.Main;
import entities.SaveBatchProperties;
import entities.WindowExplorerCreator;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AddIconOnScene;

public class BatchController {
	
	@FXML
	private Button searchButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	private static TextField sourceTextField;
	private static Scene scene;
	private static Stage stage;
	private static AnchorPane root;
	private static FXMLLoader loader;
	
	public static void showWindow() {
		try {
			loader = new FXMLLoader(BatchController.class.getResource("SourceBatch.fxml"));
			root = loader.load();
			scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			AddIconOnScene.add(stage, new Image("\\icons\\X_icon.png"));
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.getStage());
			stage.setTitle("Editar caminho do lote");
			sourceTextField = (TextField)scene.lookup("#sourceTextField");
			sourceTextField.setText(SaveBatchProperties.getSource());
			sourceTextField.setEditable(false);
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "N�o foi poss�vel acessar esta op��o:"+e.getMessage(),AlertType.ERROR);
		}
		
	}
	
	public void onSearchButtonAction() {
		String source = WindowExplorerCreator.getSource(stage);
		setTextInSourceTextField(source);
	}
	
	public void onSaveButtonAction() {
		SaveBatchProperties.setSource("source",sourceTextField.getText());
		stage.close();
	}
	
	public void onCancelButtonAction() {
		stage.close();
	}

	public TextField getSourceTextField() {
		return sourceTextField;
	}

	public void setTextInSourceTextField(String source) {
		if(sourceTextField == null) {
			sourceTextField = new TextField(source);
			sourceTextField.setEditable(false);
		}else {
			sourceTextField.setText(source);
		}
	}
	
	
}
