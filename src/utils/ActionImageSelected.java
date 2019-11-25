package utils;

import entities.DocumentX;
import gui.editFiles.LeftController;
import javafx.scene.image.Image;

//Classe responsável por disparar a ação quando um documento é selecionado
public class ActionImageSelected {
	private static Image imageFX = null;

//Método responsável por adicionar a imagem selecionada na lista de imagens selecionadas (ListImagesSelected)
	public static void mouseClickedOnly(DocumentX image) {
		
		if (ListDocumentsSelected.getInstance().size() > 0) {
			ListDocumentsSelected.getInstance().forEach((n) -> n.getStackImage().setStyle("-fx-border-style: none"));
			ListDocumentsSelected.getInstance().clear();
		}
		ListDocumentsSelected.getInstance().add(image);
		imageFX = image.getImageFX();
		LeftController.create(imageFX);
		image.getStackImage().setStyle("-fx-border-style: solid");
	}

//Método responsável por adicionar mais de uma imagem selecionada na lista de imagens selecionadas (ListImagesSelected) quando o evento CTRL + Botão direito do mouse é invocado 
	public static void mouseClickedAndButtonCtrlPressed(DocumentX image) {
		if (!ListDocumentsSelected.getInstance().contains(image)) {
			ListDocumentsSelected.getInstance().add(image);
			imageFX = image.getImageFX();
			LeftController.create(imageFX);
			image.getStackImage().setStyle("-fx-border-style: solid");
		}
	}

}