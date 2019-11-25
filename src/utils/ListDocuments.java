package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import entities.DocumentX;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

//Classe respons�ve por criar e retornar o singleton da lista dos objetos dos documentos criados
public class ListDocuments {

	private static List<DocumentX> listImages = null;

	private ListDocuments() {
	}

	public static List<DocumentX> getInstance() {
		if (listImages == null) {
			listImages = new ArrayList<>();
			return listImages;
		}
		return listImages;
	}

// Retorna o documento que cont�m a imagem espec�fica enviada por par�metro
	public static DocumentX containsImageFX(Image image) {
		for (DocumentX document : listImages) {
			if (document.getImageFX().equals(image)) {
				return document;
			}
		}
		return null;
	}

//	Retorna o documento que cont�m o objeto file espec�fico enviada por par�metro
	public static DocumentX containsFile(File file) {
		for (DocumentX document : listImages) {
			if (document.getFileDocument().equals(file)) {
				return document;
			}
		}
		return null;
	}

// Retorna o documento que cont�m o StackPane espec�fic que foi enviado por par�metro
	public static DocumentX containsStackImage(StackPane stackImage) {
		for (DocumentX document : listImages) {
			if (document.getStackImage().equals(stackImage)) {
				return document;
			}
		}
		return null;
	}

	public static boolean DocumentTypeIsNull() {
		for (DocumentX document : ListDocuments.getInstance()) {
			if (document.getDocumentType() == null) {
				return true;
			}
		}
		return false;
	}
}