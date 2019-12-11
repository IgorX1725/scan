package gui.listBatch;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import application.Main;
import gui.editFiles.EditFilesController;
import gui.main.MainController;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AddIconOnScene;
import utils.ListDocuments;

// Classe responsável por recuperar um lote especifico para edição e indexação
public class ListBatchController {
	private static FXMLLoader loader;
	private static AnchorPane anchorPane;
	private static Scene scene;
	private static Stage stage;
	private static ComboBox<File> comboBoxBatch;
	private static Button buttonOpen;
	
	//exibe a janela para seleção do lote 
	@SuppressWarnings("unchecked")
	public static void showWindow() {
		try {
			loader = new FXMLLoader((ListBatchController.class.getResource("/gui/listBatch/ListBatch.fxml")));
			anchorPane = loader.load();
			comboBoxBatch = (ComboBox<File>) anchorPane.lookup("#comboBoxBatch");
			setValuesOnComboBox(comboBoxBatch);
			buttonOpen = (Button) anchorPane.lookup("#ButtonOpen");
			buttonOpen.setDisable(true);
			scene = new Scene(anchorPane);
			stage = new Stage();
			stage.setScene(scene);
			AddIconOnScene.add(stage, new Image("/icons/X_icon.png"));
			stage.setResizable(false);
			stage.setTitle("Lista de Lotes");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.getStage());
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Erro ao Exibir a Janela:" + e.getMessage(), AlertType.ERROR);
		}
	}
	
	public void comboBoxBatchOnAction() {
		buttonOpen.setDisable(false);
	}
	
	public void buttonCancelOnAction() {
		stage.close();
	}
	
	public void buttonOpenOnAction() {
		File batch = comboBoxBatch.getSelectionModel().getSelectedItem();
		stage.close();
		ListDocuments.getInstance().clear();
		EditFilesController.showDisplayEditWindow(batch.listFiles());
	}
	
	//insere os valores no comboBox
	private static void setValuesOnComboBox(ComboBox<File> comboBox) {
		List<File> list = Arrays.asList(MainController.getRootBatches().listFiles());
		comboBox.getItems().addAll(FXCollections.observableArrayList(list));
	
	}


}
