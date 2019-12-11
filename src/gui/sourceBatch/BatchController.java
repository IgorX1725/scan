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
//classe responsável por alterar o diretório onde os lotes serão gravados
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
	// exibe a janela de seleção do diretório
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
			Alerts.showAlert("Erro", "", "Não foi possível acessar esta opção:"+e.getMessage(),AlertType.ERROR);
		}
		
	}
	//ação que abre uma janela no explorer do sistema operacional para selecionar o diretório
	// a função não permite que seja selecionado um diretório que contenha arquivos o pastas nele.
	// caso altere o diretório e desejas voltar ao diretório anterior, é necessário mover os lotes do diretório para outro local,
	//selecionar o diretório na janela de seleção, salvar e mover de volta os lotes para o diretório desejado. 
	public void onSearchButtonAction() {
		String source = WindowExplorerCreator.getSource(stage);
		File fileSource = new File(source);
		if(fileSource.list().length == 0) {
			setTextInSourceTextField(source);
		}else {
			Alerts.showAlert("Alerta", "", "O diretório não está vazio. \n por favor, selecione um diretório vazio ou crie uma pasta",AlertType.WARNING);
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
	// indica o diretório atual que está sendo salvo os lotes
	public void setTextInSourceTextField(String source) {
		if(sourceTextField == null) {
			sourceTextField = new TextField(source);
			sourceTextField.setEditable(false);
		}else {
			sourceTextField.setText(source);
		}
	}
	
	
}
