package gui.editFiles;

import entities.DocumentX;
import javafx.scene.control.ScrollPane;
import utils.CopyDocument;
import utils.DeleteDocument;
import utils.ListDocuments;
import utils.ListDocumentsSelected;
import utils.RotateImage;

public class BottonController {

	// metodo que � respons�vel por disparar a a��o do bot�o deletar na tela de
	// Edi��o dos arquivos
	public void onButtonDeletenAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			DeleteDocument.deleteFile(document.getFileDocument());
			ListDocuments.getInstance().remove(document);
		}
		LeftController.clearPane();
		EditFilesController.setLeft(new ScrollPane());
		EditFilesController.setRight(RightController.createPaneImages());
	}

//	metodo que � respons�vel por disparar a a��o do bot�o girar para direita na tela de Edi��o dos arquivos
	public void onButtonRotateRightAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), -1);
			RotateImage.rotateImageView(document.getStackImage(), true);
		}

	}

//	metodo que � respons�vel por disparar a a��o do bot�o girar para esquerda na tela de Edi��o dos arquivos
	public void onButtonRotateLeftAction() {
		for (DocumentX document : ListDocumentsSelected.getInstance()) {
			RotateImage.rotate90(document.getFileDocument(), document.getFileDocument(), 1);
			RotateImage.rotateImageView(document.getStackImage(), false);
		}

	}

//	metodo que � respons�vel por disparar a a��o do bot�o duplicar na tela de Edi��o dos arquivos
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
