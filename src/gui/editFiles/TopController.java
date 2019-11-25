package gui.editFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import entities.Batch;
import entities.SaveBatchProperties;
import entities.ScanDocument;
import entities.WindowExplorerCreator;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import utils.ImageFileToFXImage;
import utils.ListDocumentsSelected;

public class TopController {
	@FXML
	private Button buttonScanEditFiles = null;
	@FXML
	private Button buttonImportEditFiles = null;
	@FXML
	private CheckBox checkBoxFrontAndBackEditFiles = null;
	@FXML
	private Button index = null;

	private static String source = SaveBatchProperties.getSource();
	private static Batch batch = new Batch(source);
	private FileInputStream fileInputStream = null;
	private FileOutputStream fileOutputStream = null;
	private String profile = null;
	private ScrollPane indexScroll = null;

//	metodo que é responsável por disparar a ação do botão de digitaliação na tela de Edição dos arquivos
	public void onButtonScanActionEditFiles() {
		profile = checkBoxFrontAndBackEditFiles.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + profile);
			ImageFileToFXImage.tiffToImageList(ImageFileToFXImage.getNewFiles());
			RightController.updateImages();
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
		}
	}

//	metodo que é responsável por disparar a ação do botão de importação na tela de Edição dos arquivos
	public void onButtonImportActionEditFiles() {
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = WindowExplorerCreator.importFiles(EditFilesController.getStage());

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
					RightController.updateImages();
				} catch (FileNotFoundException e) {
					Alerts.showAlert("Erro", "", "Ocorreu um erro ao importar o arquivo para o lote: " + e.getMessage(),
							AlertType.ERROR);
					e.printStackTrace();
				} catch (IOException e) {
					Alerts.showAlert("Erro", "", "Ocorreu um erro ao importar o arquivo: " + e.getMessage(),
							AlertType.ERROR);
					e.printStackTrace();
				}
			}
		} catch (NullPointerException e) {
			Alerts.showAlert("Aviso", "", "Nenhum arquivo foi selecionado ", AlertType.WARNING);
		}
	}

//	metodo que é responsável por disparar a ação do botão feito quando o lote estiver pronto para indexar
	public void onButtonIndexAction() {
		if (ListDocumentsSelected.getInstance().isEmpty()) {
			Alerts.showAlert("Alerta", "", "Nenhum Documento foi Selecionado", AlertType.WARNING);
		} else {
			indexScroll = new ScrollPane(IndexController.getIndex());
			EditFilesController.setRight(indexScroll);
		}
	}
}
