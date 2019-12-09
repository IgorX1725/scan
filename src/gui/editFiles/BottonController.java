package gui.editFiles;

import entities.DocumentX;
import javafx.scene.control.ScrollPane;
import utils.CopyDocument;
import utils.DeleteDocument;
import utils.ListDocuments;
import utils.ListDocumentsSelected;
import utils.RotateImage;

public class BottonController {

	// metodo que é responsável por disparar a ação do botão deletar na tela de
	// Edição dos arquivos
	public void onButtonDeletenAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			DeleteDocument.deleteFile(document.getFileDocument());
			ListDocuments.getInstance().remove(document);
		}
		LeftController.clearPane();
		EditFilesController.setLeft(new ScrollPane());
		EditFilesController.setRight(RightController.createPaneImages());
	}

//	metodo que é responsável por disparar a ação do botão girar para direita na tela de Edição dos arquivos
	public void onButtonRotateRightAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), -1);
			RotateImage.rotateImageView(document.getStackImage(), true);
		}

	}

//	metodo que é responsável por disparar a ação do botão girar para esquerda na tela de Edição dos arquivos
	public void onButtonRotateLeftAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), 1);
			RotateImage.rotateImageView(document.getStackImage(), false);
		}

	}

//	metodo que é responsável por disparar a ação do botão duplicar na tela de Edição dos arquivos
	public void onButtonCopyAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance())
			try {
				CopyDocument.copy(document.getFileDocument().getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}

		ListDocumentsSelected.clear();
		EditFilesController.setRight(RightController.createPaneImages());
	}
}
