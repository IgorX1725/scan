package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

//Classe respons�vel por disparar a a��o quando um documento � selecionado
public class ActionImageSelected {
	private static PanelLeftImage panelImage = new PanelLeftImage();
	static Image imageFX = null;

//M�todo respons�vel por adicionar a imagem selecionada na lista de imagens selecionadas (ListImagesSelected)
	public static void mouseClickedOnly(StackPane image) {
		if (ListImagesSelected.getInstance().size() > 0) {
			int i = 0;
			while (i < ListImagesSelected.getInstance().size()) {
				ListImagesSelected.getInstance().get(i).setStyle("-fx-border-style: none");
				ListImagesSelected.getInstance().remove(ListImagesSelected.getInstance().get(i));
				i++;
			}
		}
		ListImagesSelected.getInstance().add(image);
		imageFX = ((ImageView) image.getChildren().get(0)).getImage();
		panelImage.create(imageFX);
		image.setStyle("-fx-border-style: solid");

	}
//M�todo respons�vel por adicionar mais de uma imagem selecionada na lista de imagens selecionadas (ListImagesSelected) quando o evento CTRL + Bot�o direito do mouse � invocado 
	public static void mouseClickedAndButtonCtrlPressed(StackPane image) {
		if(!ListImagesSelected.getInstance().contains(image)) {
			image.setStyle("-fx-border-style: solid");
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