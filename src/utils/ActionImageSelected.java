package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ActionImageSelected {
	private static PanelLeftImage panelImage = new PanelLeftImage();
	static Image imageFX = null;

	public static void addImage(StackPane image) {
		
		if (ListImagesSelected.getInstance().size() > 0) {
			int i = 0;
			while (i < ListImagesSelected.getInstance().size()) {
				ListImagesSelected.getInstance().get(i).setStyle("-fx-border-style: none");
				ListImagesSelected.getInstance().remove(ListImagesSelected.getInstance().get(i));
			}
		}
			ListImagesSelected.getInstance().add(image);
			imageFX = ((ImageView) image.getChildren().get(0)).getImage();
			panelImage.create(imageFX);
			image.setStyle("-fx-border-style: solid");

		}

	public static PanelLeftImage getPanelImage() {
		return panelImage;
	}
		
}