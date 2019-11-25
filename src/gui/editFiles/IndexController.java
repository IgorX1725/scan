package gui.editFiles;


import java.io.IOException;

import gui.util.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class IndexController {

	
	private static FXMLLoader loader;
	private static AnchorPane anchorPane;
	private static TextField textFieldRA = null;
	private static TextField textFieldName = null;
	private static TextField textFieldCPF = null;
	private static ComboBox<String> comboBoxLevel = null;
	private static ComboBox<String> comboBoxCourse = null;
	private static ComboBox<String> comboBoxCategory = null;
	private static ComboBox<String> comboBoxType = null;
	private static TextField processNumberTextField = null;
	private static Button indexButton = null;

	@SuppressWarnings("unchecked")
	public static AnchorPane getIndex() {
		try {
			loader = new FXMLLoader(IndexController.class.getResource("/gui/editFiles/Index.fxml"));
			anchorPane = loader.load();
			textFieldRA = (TextField) anchorPane.lookup("#textFieldRA");
			//Permite que somente números sejam digitados no campo RA
			textFieldRA.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			        	textFieldRA.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
		
			textFieldName = (TextField) anchorPane.lookup("#textFieldName");
			textFieldName.setEditable(false);
			textFieldCPF = (TextField) anchorPane.lookup("#textFieldCPF");
			textFieldCPF.setEditable(false);
			comboBoxLevel = (ComboBox<String>) anchorPane.lookup("#comboBoxLevel");
			comboBoxLevel.setDisable(true);
			comboBoxCourse = (ComboBox<String>) anchorPane.lookup("#comboBoxCourse");
			comboBoxCourse.setDisable(true);
			comboBoxCategory = (ComboBox<String>) anchorPane.lookup("#comboBoxCategory");
			comboBoxCategory.setDisable(true);
			comboBoxType = (ComboBox<String>) anchorPane.lookup("#comboBoxType");
			comboBoxType.setDisable(true);
			processNumberTextField = (TextField) anchorPane.lookup("#processNumberTextField");
			processNumberTextField.setDisable(true);
			indexButton = (Button) anchorPane.lookup("#indexButton");
			indexButton.setDisable(true);
			return anchorPane ;
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Não foi possível acessar esta opção:"+e.getMessage(),AlertType.ERROR);
		}
		return null;
	}
	
	public void onCancelButtonAction(){
		EditFilesController.setRight(RightController.createPaneImages());
	}
	
	public void onButtonConsultAction() {
		
		if(textFieldRA.getText().trim().isEmpty()) {
			Alerts.showAlert("Aviso", "", "é necessário informar o número do RA", AlertType.WARNING);
		}
	}

}
