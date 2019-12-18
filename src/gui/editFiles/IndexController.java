package gui.editFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entities.DocumentX;
import gui.util.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.AddCheck;
import utils.GetDatasJson;
import utils.ListDocumentsSelected;

public class IndexController {

	private static FXMLLoader loader = null;
	private static AnchorPane anchorPane = null;
	private static TextField textFieldRA = null;
	private static TextField textFieldName = null;
	private static TextField textFieldCPF = null;
	private static ComboBox<String> comboBoxLevel = null;
	private static ComboBox<String> comboBoxCourse = null;
	private static ComboBox<String> comboBoxCategory = null;
	private static ComboBox<String> comboBoxType = null;
	// usu�rio n�o utiliza este campo
	// private static TextField processNumberTextField = null;
	private static Button indexButton = null;
	private static JSONObject jsonResult = null;

	//carrega e gera a view index.fxml na janela edit files
	@SuppressWarnings("unchecked")
	public static AnchorPane getIndex() {
		try {
			loader = new FXMLLoader(IndexController.class.getResource("/gui/editFiles/Index.fxml"));
			anchorPane = loader.load();
			textFieldRA = (TextField) anchorPane.lookup("#textFieldRA");
			
			// Permite que somente n�meros sejam digitados no campo RA
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
			comboBoxCourse = (ComboBox<String>) anchorPane.lookup("#comboBoxCourse");
			comboBoxCategory = (ComboBox<String>) anchorPane.lookup("#comboBoxCategory");
			comboBoxType = (ComboBox<String>) anchorPane.lookup("#comboBoxType");
			indexButton = (Button) anchorPane.lookup("#indexButton");

			// usu�rio n�o utiliza este campo
//			processNumberTextField = (TextField) anchorPane.lookup("#processNumberTextField");
			setProprietsOnComponents();
			LeftController.update(ListDocumentsSelected.next(false).getStackImage());
			return anchorPane;
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "N�o foi poss�vel acessar esta op��o:" + e.getMessage(), AlertType.ERROR);
		}
		return null;
	}

	//ac�o a ser executada quando o bot�o cancelar � clicado
	public void onCancelButtonAction() {
		EditFilesController.setRight(RightController.createPaneImages());
	}
	//a��o a ser executada quando o bot�o consultar � clicado
	public void onButtonConsultAction() {
		if (textFieldRA.getText().trim().isEmpty()) {
			Alerts.showAlert("Aviso", "", "� necess�rio informar o n�mero do RA", AlertType.WARNING);
		} else {
			jsonResult = new JSONObject(GetDatasJson.getRA(Integer.parseInt(textFieldRA.getText())));
			if (jsonResult.isEmpty()) {
				Alerts.showAlert("Aviso", "", "RA n�o localizado. Favor, verificar", AlertType.WARNING);
			} else {				
				textFieldName.setText(jsonResult.getString("name"));
				textFieldCPF.setText(jsonResult.getString("CPF"));
				comboBoxLevel.setDisable(false);
				comboBoxLevel.getItems().addAll(addItems("level"));
				comboBoxCourse.getItems().addAll(addItems("course"));
				comboBoxCategory.getItems().addAll(addItems("category"));
				comboBoxType.getItems().addAll(addItems("type"));

			}
		}
	}
	//a��o a ser executada quando o comboBox do n�vel sofre alguma altera��o
	public void comboBoxLevelOnAction() {
		if (!comboBoxLevel.getSelectionModel().getSelectedItem().isEmpty()) {
			comboBoxCourse.setDisable(false);

		} else {
			comboBoxCourse.setDisable(true);
		}
	}
	//a��o a ser executada quando o comboBox do curso sofre alguma altera��o
	public void comboBoxCourseOnAction() {
		if (!comboBoxCourse.getSelectionModel().getSelectedItem().isEmpty()) {
			comboBoxCategory.setDisable(false);
		} else {
			comboBoxCategory.setDisable(true);
		}
	}
	//a��o a ser executada quando o comboBox da categoria sofre alguma altera��o
	public void comboBoxCategoryOnAction() {
		if (!comboBoxCategory.getSelectionModel().getSelectedItem().isEmpty()) {
			comboBoxType.setDisable(false);
		} else {
			comboBoxType.setDisable(true);
		}
	}
	//a��o a ser executada quando o comboBox do tipo do documento sofre alguma altera��o
	public void comboBoxTypeOnAction() {
		if (!comboBoxType.getSelectionModel().getSelectedItem().isEmpty()) {
			indexButton.setDisable(false);
		}else {
			indexButton.setDisable(true);
		}
	}
	//a��o a ser executada quando o bot�o indexar e prosseguir � clicado
	public void indexButtonOnAction() {
		if (comboBoxType.getSelectionModel().getSelectedItem().isEmpty()
				|| comboBoxLevel.getSelectionModel().getSelectedItem().isEmpty()
				|| comboBoxCourse.getSelectionModel().getSelectedItem().isEmpty()
				|| comboBoxCategory.getSelectionModel().getSelectedItem().isEmpty()) {
			Alerts.showAlert("Alerta", "", "� necess�rio preencher todos os campos", AlertType.WARNING);
		} else {
			setParamsOnDocuments(ListDocumentsSelected.next(false));
			AddCheck.greenCheck(ListDocumentsSelected.next(false).getStackImage());
			
			if(ListDocumentsSelected.next(true) == null) {
				
				Alerts.showAlert("Alerta", "", "Todos os documentos selecionados foram indexados", AlertType.INFORMATION);
				ListDocumentsSelected.clear();
				loader = null;
				anchorPane = null;
				textFieldRA = null;
				textFieldName = null;
				textFieldCPF = null;
				comboBoxLevel = null;
				comboBoxCourse = null;
				comboBoxCategory = null;
				comboBoxType = null;
				indexButton = null;
				jsonResult = null;
				EditFilesController.setRight(RightController.createPaneImages());
				
			}else {				
			LeftController.update(ListDocumentsSelected.next(false).getStackImage());
			setProprietsOnComponents();
			}
		}
	}
	
	//metodo respons�vel por adicionar os itens dos comboBoxs 
	private static ObservableList<String> addItems(String field) {
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray(jsonResult.get(field).toString());
		list.add("");
		for (int i = 0; i < jsonArray.length(); i++) {
			list.add(jsonArray.getString(i));
		}
		return FXCollections.observableArrayList(list);
	}
	
	//metodo respons�vel por setar os par�metros dos documentos que est�o sendo indexados
	private static void setParamsOnDocuments(DocumentX document) {
		document.setrAOwner(Integer.parseInt(textFieldRA.getText()));
		document.setNameOwner(textFieldName.getText());
		document.setcPFOwner(Long.parseLong(textFieldCPF.getText().replace(".", "").replace("-", "")));
		document.setLevel(comboBoxLevel.getSelectionModel().getSelectedItem().toString());
		document.setCourse(comboBoxCourse.getSelectionModel().getSelectedItem().toString());
		document.setCategory(comboBoxCategory.getSelectionModel().getSelectedItem().toString());
		document.setDocumentType(comboBoxType.getSelectionModel().getSelectedItem().toString());
		document.setProcessNumber(0);
		//ap�s teste, remover a linha abaixo
		System.out.println(document.toString());
	}
	
	//definir as propriedades dos componentes visuais da view index.fxml
		private static void setProprietsOnComponents() {
			comboBoxLevel.getSelectionModel().select(0);
			if (textFieldRA.getText().isEmpty()) {
				comboBoxLevel.setDisable(true);
			}
			comboBoxCourse.getSelectionModel().select(0);
			comboBoxCourse.setDisable(true);
			comboBoxCategory.getSelectionModel().select(0);
			comboBoxCategory.setDisable(true);
			comboBoxType.getSelectionModel().select(0);
			comboBoxType.setDisable(true);
			// usu�rio n�o utiliza este campo
			// processNumberTextField.setDisable(true);

			indexButton.setDisable(true);
		}

}
