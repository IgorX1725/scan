package gui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Batch;
import entities.ImportDocuments;
import entities.ScanDocument;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ViewControler {

	@FXML
	private Button buttonScan;
	@FXML
	private Button buttonImport;

	private Stage stage;

	String nameBatch = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	Batch batch = new Batch(new File("C:\\Temp\\" + nameBatch));

	public void onButtonScanAction() {
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + Profiles.FRENTE.toString().toLowerCase());
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
			System.out.println("Ocorreu um erro ao criar o lote:" + e.getMessage());
		} catch (DomainExceptions e) {
			batch.deletePath();
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
			System.out.println("Ocorreu um Problema: " + e.getMessage());
		}
	}

	public void onButtonImportAction() {
		ImportDocuments.selectFiles(this.stage);
	}

	public void init(Stage stage) {
		this.stage = stage;

	}
}
