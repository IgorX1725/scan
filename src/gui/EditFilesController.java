package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.Main;
import entities.Batch;
import entities.DocumentX;
import entities.SaveBatchProperties;
import entities.ScanDocument;
import entities.WindowExplorerCreator;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.ActionImageSelected;
import utils.AddIconOnScene;
import utils.CopyDocument;
import utils.DeleteDocument;
import utils.GridWithImages;
import utils.ImageFileToFXImage;
import utils.ListDocuments;
import utils.ListDocumentsSelected;
import utils.RotateImage;

// Controlador da GUI EditFiles.fxml
public class EditFilesController {
	@FXML
	private Button buttonScanEditFiles = null;
	@FXML
	private Button buttonImportEditFiles = null;
	@FXML
	private CheckBox checkBoxFrontAndBackEditFiles = null;
	@FXML
	private Button buttonDone = null;
	@FXML
	private AnchorPane anchorPanelistDocuments = null;
	
	private static String source = SaveBatchProperties.getSource();
	private static Batch batch = new Batch(source);
	private FileInputStream fileInputStream = null;
	private FileOutputStream fileOutputStream = null;
	private static Scene editFilesScene = null;
	private static BorderPane root = null;
	private static Stage stage = null;
	private static ScrollPane scrollImages = null;
	private final static Set<KeyCode> pressedKeys = new HashSet<>();
	private static FXMLLoader viewEditFiles = null;
	private String profile = null;

	// Metodo para exibir a janela de edição dos documentos do lote
	public static void showDisplayEditWindow(File[] list) {
		try {
			if (stage == null) {
				viewEditFiles = new FXMLLoader(EditFilesController.class.getResource("/gui/EditFiles.fxml"));
				root = viewEditFiles.load();
				editFilesScene = new Scene(root);
				ImageFileToFXImage.tiffToImageList(list);
				stage = new Stage();
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Main.getStage());
				stage.setTitle("Editar Documentos");
				stage.setScene(editFilesScene);
				stage.setMaximized(true);
				AddIconOnScene.add(stage, new Image("\\icons\\X_Icon.png"));
				stage.show();
				scrollImages = createPaneImages();
				root.setRight(scrollImages);
			} else {
				ImageFileToFXImage.tiffToImageList(list);
				updateImages();
				stage.show();
			}
			editFilesScene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
			editFilesScene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Metodo para atualizar a visualização dos documentos quando houver alguma
	// alteração no lote
	public static void updateImages() {
		root.setRight(null);
		ScrollPane paneImages = createPaneImages();
		root.setRight(paneImages);

	}

// Metodo para criar o painel dos documentos presentes no lote
	private static ScrollPane createPaneImages() {
		Double Width = root.getWidth() / 2;
		Double height = root.getHeight()
				- (((AnchorPane) root.getTop()).getHeight() + ((ToolBar) root.getBottom()).getHeight());
		ScrollPane paneImages = new ScrollPane(GridWithImages.create());
		paneImages.setPrefSize(Width, height);
		return paneImages;
	}

//	metodo que é responsável por disparar a ação do botão de digitaliação na tela de Edição dos arquivos
	public void onButtonScanActionEditFiles() {
		profile = checkBoxFrontAndBackEditFiles.isSelected() ? Profiles.FRENTEVERSO.toString().toLowerCase()
				: Profiles.FRENTE.toString().toLowerCase();
		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
					+ "\"\\$(n).tiff\" --split --progress -p " + profile);
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Ocorreu um erro ao criar o lote:" + e.getMessage(), AlertType.ERROR);
		} catch (DomainExceptions e) {
			Alerts.showAlert("Alerta", "", "Ocorreu um Problema: " + e.getMessage(), AlertType.WARNING);
		}
		ImageFileToFXImage.tiffToImageList(ImageFileToFXImage.getNewFiles());
		EditFilesController.updateImages();
	}

//	metodo que é responsável por disparar a ação do botão de importação na tela de Edição dos arquivos
	public void onButtonImportActionEditFiles() {
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		List<File> files;
		try {
			files = WindowExplorerCreator.importFiles(stage);

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

//	metodo que é responsável por disparar a ação do botão feito quando o lote estiver pronto para indexar
	public void onButtonDoneAction() {
		ListDocumentsSelected.getInstance().clear();
		ListDocuments.getInstance().clear();
		EditFilesController.closeStage();
	}

	// metodo que é responsável por disparar a ação do botão deletar na tela de
	// Edição dos arquivos
	public void onButtonDeletenAction() {
			for(DocumentX document : ListDocumentsSelected.getInstance()) {
			DeleteDocument.deleteFile(document.getFileDocument());
			ListDocuments.getInstance().remove(document);
		}
		ActionImageSelected.getPanelImage().clearPane();
		ListDocumentsSelected.getInstance().clear();
		EditFilesController.updateImages();
	}

//	metodo que é responsável por disparar a ação do botão girar para direita na tela de Edição dos arquivos
	public void onButtonRotateRightAction() {
		for(DocumentX document :  ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), -1);
			RotateImage.rotateImageView(document.getStackImage(), true);
		}

	}

//	metodo que é responsável por disparar a ação do botão girar para esquerda na tela de Edição dos arquivos
	public void onButtonRotateLeftAction() {
			for(DocumentX document : ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), 1);
			RotateImage.rotateImageView(document.getStackImage(), false);
		}

	}

//	metodo que é responsável por disparar a ação do botão duplicar na tela de Edição dos arquivos
	public void onButtonCopyAction() {
		for(DocumentX document : ListDocumentsSelected.getInstance())
			try {
				CopyDocument.copy(document.getFileDocument().getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		ListDocumentsSelected.getInstance().clear();
		EditFilesController.updateImages();
	}
	
	

	public static void setSource(String source) {
		EditFilesController.source = source;
	}

	public static String getSource() {
		return source;
	}

	public static Set<KeyCode> getPressedkeys() {
		return pressedKeys;
	}

	public static Scene getEditFilesScene() {
		return editFilesScene;
	}

	public static Stage getEditFilesStage() {
		return stage;
	}

	public static void closeStage() {
		stage.close();

	}

}
