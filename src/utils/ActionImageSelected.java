package utils;

import entities.DocumentX;
import javafx.scene.image.Image;

//Classe respons�vel por disparar a a��o quando um documento � selecionado
public class ActionImageSelected {
	private static PanelLeftImage panelImage = new PanelLeftImage();
	static Image imageFX = null;

//M�todo respons�vel por adicionar a imagem selecionada na lista de imagens selecionadas (ListImagesSelected)
	public static void mouseClickedOnly(DocumentX image) {
		
		if (ListDocumentsSelected.getInstance().size() > 0) {
			ListDocumentsSelected.getInstance().forEach((n) -> n.getStackImage().setStyle("-fx-border-style: none"));
			ListDocumentsSelected.getInstance().clear();
		}
		ListDocumentsSelected.getInstance().add(image);
		imageFX = image.getImageFX();
		panelImage.create(imageFX);
		image.getStackImage().setStyle("-fx-border-style: solid");
	}

//M�todo respons�vel por adicionar mais de uma imagem selecionada na lista de imagens selecionadas (ListImagesSelected) quando o evento CTRL + Bot�o direito do mouse � invocado 
	public static void mouseClickedAndButtonCtrlPressed(DocumentX image) {
		if (!ListDocumentsSelected.getInstance().contains(image)) {
			ListDocumentsSelected.getInstance().add(image);
			imageFX = image.getImageFX();
			panelImage.create(imageFX);
			image.getStackImage().setStyle("-fx-border-style: solid");
		}
	}

	public static PanelLeftImage getPanelImage() {
		return panelImage;
	}

}