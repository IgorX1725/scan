package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

//Classe responsável por disparar a ação quando um documento é selecionado
public class ActionImageSelected {
	private static PanelLeftImage panelImage = new PanelLeftImage();
	static Image imageFX = null;

//Método responsável por adicionar a imagem selecionada na lista de imagens selecionadas (ListImagesSelected)
	public static void mouseClickedOnly(StackPane image) {
		
		if (ListImagesSelected.getInstance().size() > 0) {
			ListImagesSelected.getInstance().forEach((n) -> n.setStyle("-fx-border-style: none"));
			ListImagesSelected.getInstance().clear();
		}
		ListImagesSelected.getInstance().add(image);
		imageFX = ((ImageView) image.getChildren().get(0)).getImage();
		panelImage.create(imageFX);
		image.setStyle("-fx-border-style: solid");
	}

//Método responsável por adicionar mais de uma imagem selecionada na lista de imagens selecionadas (ListImagesSelected) quando o evento CTRL + Botão direito do mouse é invocado 
	public static void mouseClickedAndButtonCtrlPressed(StackPane image) {
		if (!ListImagesSelected.getInstance().contains(image)) {
			ListImagesSelected.getInstance().add(image);
			imageFX = ((ImageView) image.getChildren().get(0)).getImage();
			panelImage.create(imageFX);
			image.setStyle("-fx-border-style: solid");
		}
	}

	public static PanelLeftImage getPanelImage() {
		return panelImage;
	}

}