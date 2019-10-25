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
import entities.ImportDocuments;
import entities.ScanDocument;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ActionImageSelected;
import utils.CopyDocument;
import utils.DeleteDocument;
import utils.GetImageFXFromStackPane;
import utils.ImageFileToFXImage;
import utils.ListImagesSelected;
import utils.MapImages;
import utils.RotateImage;

public class ViewController {

	@FXML
	private Button buttonScan = null;
	@FXML
	private Button buttonImport = null;
	@FXML
	private CheckBox checkBoxFrontAndBack = new CheckBox();
	@FXML
	private AnchorPane anchorPanelistDocuments = null;
	@FXML
	private Button buttonScanEditFiles = null;
	@FXML
	private Button buttonImportEditFiles = null;
	@FXML
	private CheckBox checkBoxFrontAndBackEditFiles = null;

	private Stage stage;
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private static String source = "C:\\temp\\lote";
	private static Batch batch = new Batch(source);
	private String profile;
	private FXMLLoader viewEditFiles = new FXMLLoader(getClass().getResource("/gui/EditFiles.fxml"));

	public void retrieveBatch() {
		if (batch.getLastModified().listFiles().length > 0) {
			Optional<ButtonType> result = null;
			result = Alerts.showAlert("Continuar lote...", "", "Deseja continuar o ultmo lote?",
					Alert.AlertType.CONFIRMATION);
			if (result.get() == ButtonType.YES) {
				EditFilesController.showDisplayEditWindow(viewEditFiles, batch.listFiles());
			} else if (result.get().equals(ButtonType.CANCEL)) {
			}
		} else {
			batch.getLastModified().delete();
		}
	}

	public void onButtonScanAction() {
		batch.createPath();
		profile = checkBoxFrontAndBack.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + profile);
			EditFilesController.showDisplayEditWindow(viewEditFiles, batch.listFiles());
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
			System.out.println("Ocorreu um erro ao criar o lote:" + e.getMessage());
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
			System.out.println("Ocorreu um Problema: " + e.getMessage());
		}
	}

	public void onButtonImportAction() {
		batch.createPath();
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = ImportDocuments.importFiles(this.stage);

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
			}
			EditFilesController.showDisplayEditWindow(viewEditFiles, batch.listFiles());
		} catch(NullPointerException e) {
			Alerts.showAlert("Aviso", "", "Nenhum arquivo foi selecionado ", AlertType.WARNING);
			batch.getLastModified().delete();
		}
	}

	public void onButtonScanActionEditFiles() {
		profile = checkBoxFrontAndBack.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + profile);
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
			System.out.println("Ocorreu um erro ao criar o lote:" + e.getMessage());
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
			System.out.println("Ocorreu um Problema: " + e.getMessage());
		}
		ImageFileToFXImage.tiffToImageList(ImageFileToFXImage.getNewFiles());
		EditFilesController.updateImages();
	}

	public void onButtonImportActionEditFiles() {
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = ImportDocuments.importFiles(this.stage);

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
					EditFilesController.updateImages();
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

	public void onButtonDeletenAction() {
		int i = 0;

		while (i < ListImagesSelected.getInstance().size()) {
			Image imageFX = GetImageFXFromStackPane.get(ListImagesSelected.getInstance().get(i));
			DeleteDocument.deleteFile(MapImages.getInstance().get(imageFX));
			MapImages.getInstance().remove(imageFX);
			i++;
		}
		ActionImageSelected.getPanelImage().clearPane();
		ListImagesSelected.getInstance().clear();
		EditFilesController.updateImages();
	}

	public void onButtonRotateRightAction() {
		int i = 0;
		while (i < ListImagesSelected.getInstance().size()) {
			Image imageFX = GetImageFXFromStackPane.get(ListImagesSelected.getInstance().get(i));
			RotateImage.rotate90(MapImages.getInstance().get(imageFX), MapImages.getInstance().get(imageFX), -1);
			RotateImage.rotateImageView(ListImagesSelected.getInstance().get(i), true);
			i++;
		}

	}

	public void onButtonRotateLeftAction() {
		int i = 0;
		while (i < ListImagesSelected.getInstance().size()) {
			Image imageFX = GetImageFXFromStackPane.get(ListImagesSelected.getInstance().get(i));
			RotateImage.rotate90(MapImages.getInstance().get(imageFX), MapImages.getInstance().get(imageFX), 1);
			RotateImage.rotateImageView(ListImagesSelected.getInstance().get(i), false);
			i++;
		}

	}

	public void onButtonCopyAction() {
		int i = 0;
		while (i < ListImagesSelected.getInstance().size()) {
			try {
				CopyDocument.copy(MapImages.getInstance()
						.get(GetImageFXFromStackPane.get(ListImagesSelected.getInstance().get(i))).getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		ListImagesSelected.getInstance().clear();
		EditFilesController.updateImages();
	}

	public void init(Stage stage) {
		this.stage = stage;

	}
	
	public static Batch getBatch(){
		return batch;
	}

}
