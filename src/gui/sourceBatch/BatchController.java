package gui.sourceBatch;


import java.io.File;
import java.io.IOException;

import application.Main;
import entities.SaveBatchProperties;
import entities.WindowExplorerCreator;
import gui.main.MainController;
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
//classe respons�vel por alterar o diret�rio onde os lotes ser�o gravados
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
	// exibe a janela de sele��o do diret�rio
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
	//a��o que abre uma janela no explorer do sistema operacional para selecionar o diret�rio
	// a fun��o n�o permite que seja selecionado um diret�rio que contenha arquivos o pastas nele.
	// caso altere o diret�rio e desejas voltar ao diret�rio anterior, � necess�rio mover os lotes do diret�rio para outro local,
	//selecionar o diret�rio na janela de sele��o, salvar e mover de volta os lotes para o diret�rio desejado. 
	public void onSearchButtonAction() {
		String source = WindowExplorerCreator.getSource(stage);
		File fileSource = new File(source);
		if(fileSource.list().length == 0) {
			setTextInSourceTextField(source);
		}else {
			Alerts.showAlert("Alerta", "", "O diret�rio n�o est� vazio. \n por favor, selecione um diret�rio vazio ou crie uma pasta",AlertType.WARNING);
		}
	}
	
	public void onSaveButtonAction() {
		SaveBatchProperties.setSource("source",sourceTextField.getText());
		MainController.setRootBatches(new File(SaveBatchProperties.getSource()));
		stage.close();
	}
	
	public void onCancelButtonAction() {
		stage.close();
	}

	public TextField getSourceTextField() {
		return sourceTextField;
	}
	// indica o diret�rio atual que est� sendo salvo os lotes
	public void setTextInSourceTextField(String source) {
		if(sourceTextField == null) {
			sourceTextField = new TextField(source);
			sourceTextField.setEditable(false);
		}else {
			sourceTextField.setText(source);
		}
	}
	
	
}
