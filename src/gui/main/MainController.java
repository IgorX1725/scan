package gui.main;

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
import gui.about.AboutController;
import gui.editFiles.EditFilesController;
import gui.listBatch.ListBatchController;
import gui.sourceBatch.BatchController;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import utils.ListDocuments;

//Controlador da GUI View.fxml
public class MainController {

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
	private static File rootBatches = Batch.getFileRootBatch(SaveBatchProperties.getSource());
	private static File batch = null;
	private String profile = null;

// Metodo responsável por recuperar o ultimo lote manipulado pelo usuário
	public void retrieveBatch() {
		if(rootBatches.listFiles().length != 0) {
			if (Batch.getLastModified(rootBatches).listFiles().length != 0) {
				Optional<ButtonType> result = null;
				result = Alerts.showAlert("Continuar lote...", "", "Deseja continuar o ultmo lote?",
						Alert.AlertType.CONFIRMATION);
				if (result.get() == ButtonType.YES) {
					EditFilesController.showDisplayEditWindow(Batch.getLastModified(rootBatches));
				} else if (result.get().equals(ButtonType.CANCEL)) {
				}
			} else {
				Batch.getLastModified(rootBatches).delete();
				retrieveBatch();
			}
		}

	}

// metodo que é responsável por disparar a ação do botão de digitaliação
	public void onButtonScanAction() {
		batch = Batch.createBatch(rootBatches);
		profile = checkBoxFrontAndBack.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument(
					"cmd /c naps2.console -o " + batch.getPath() + "\"\\$(n).tiff\" --split --progress -p " + profile);
			EditFilesController.showDisplayEditWindow(batch);
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
		}
	}

//	metodo que é responsável por disparar a ação do botão de importação de arquivos
	public void onButtonImportAction() {
		batch = Batch.createBatch(rootBatches);
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = WindowExplorerCreator.importFiles(this.stage);

			for (File file : files) {
				try {
					fileInputStream = new FileInputStream(file.getAbsolutePath());
					sourceChannel = fileInputStream.getChannel();
					fileOutputStream = new FileOutputStream(batch.getPath() + "\\" + file.getName());
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
				ListDocuments.getInstance().clear();
				EditFilesController.showDisplayEditWindow(batch);
			}
		} catch (NullPointerException e) {
			Alerts.showAlert("Aviso", "", "Nenhum arquivo foi selecionado ", AlertType.WARNING);
			Batch.getLastModified(rootBatches).delete();
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

	public void onMenuItemListBatch() {
		ListBatchController.showWindow();
	}

	public static File getBatch() {
		return batch;
	}

	public void init(Stage stage) {
		this.stage = stage;

	}

	public static File getRootBatches() {
		return rootBatches;
	}

	public static void setRootBatches(File rootBatches) {
		MainController.rootBatches = rootBatches;
	}

}
