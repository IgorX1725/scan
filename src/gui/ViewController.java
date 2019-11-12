package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

import entities.Batch;
import entities.WindowExplorerCreator;
import entities.SaveBatchProperties;
import entities.ScanDocument;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import utils.MapImages;

//Controlador da GUI View.fxml
public class ViewController {

	@FXML
	private Button buttonScan = null;
	@FXML
	private Button buttonImport = null;
	@FXML
	private CheckBox checkBoxFrontAndBack = new CheckBox();
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem about;
	@FXML
	private MenuItem MenuItemsource;

	private Stage stage = null;
	private FileInputStream fileInputStream = null;
	private FileOutputStream fileOutputStream = null;
	private static String source = SaveBatchProperties.getSource();
	private static Batch batch = new Batch(source);
	private String profile = null;
	

// Metodo responsável por recuperar o ultimo lote manipulado pelo usuário
	public void retrieveBatch() {
		if (batch.getLastModified().listFiles().length > 0) {
			Optional<ButtonType> result = null;
			result = Alerts.showAlert("Continuar lote...", "", "Deseja continuar o ultmo lote?",
					Alert.AlertType.CONFIRMATION);
			if (result.get() == ButtonType.YES) {
				EditFilesController.showDisplayEditWindow(batch.listFiles());
			} else if (result.get().equals(ButtonType.CANCEL)) {
			}
		} else {
			batch.getLastModified().delete();
		}
	}

// metodo que é responsável por disparar a ação do botão de digitaliação
	public void onButtonScanAction() {
		batch.createPath();
		profile = checkBoxFrontAndBack.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + profile);
			EditFilesController.showDisplayEditWindow(batch.listFiles());
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
		}
	}

//	metodo que é responsável por disparar a ação do botão de importação de arquivos
	public void onButtonImportAction() {
		batch.createPath();
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = WindowExplorerCreator.importFiles(this.stage);

			for (File file : files) {
				try {
					fileInputStream = new FileInputStream(file.getAbsolutePath());
					sourceChannel = fileInputStream.getChannel();
					fileOutputStream = new FileOutputStream(batch.getSource() + "\\" + file.getName());
					destinationChannel = fileOutputStream.getChannel();
					sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
					if (sourceChannel != null && sourceChannel.isOpen())
						sourceChannel.close();
					if (destinationChannel != null && destinationChannel.isOpen())
						destinationChannel.close();
				} catch (FileNotFoundException e) {
					Alerts.showAlert("Erro", "", "Ocorreu um erro ao importar o arquivo para o lote: " + e.getMessage(),
							AlertType.ERROR);
					e.printStackTrace();
				} catch (IOException e) {
					Alerts.showAlert("Erro", "", "Ocorreu um erro ao importar o arquivo: " + e.getMessage(),
							AlertType.ERROR);
					e.printStackTrace();
				}
				MapImages.getInstance().clear();
				EditFilesController.showDisplayEditWindow(batch.listFiles());
			}
		} catch (NullPointerException e) {
			Alerts.showAlert("Aviso", "", "Nenhum arquivo foi selecionado ", AlertType.WARNING);
			batch.getLastModified().delete();
		}
	}

// Metodo que é responsável por disparar a ação do item de menu arquivo > sair
	public void onExitAction() {
		stage.close();
	}

	// Metodo que é responsável por disparar a ação do item de menu ajuda > sobre
	public void onAboutAction() {
		AboutController.showWindow();
	}
	
	public void onMenuItemSourceAction() {
		BatchController.showWindow();
	}

	public static Batch getBatch() {
		return batch;
	}

	public void init(Stage stage) {
		this.stage = stage;

	}

}
