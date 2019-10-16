package utils;

import javafx.scene.layout.StackPane;

public class ActionImageSelected {
	private static PanelLeftImage panelImage = new PanelLeftImage();

	public static void addImage(StackPane image) {
		
		if (ListImagesSelected.getInstance().size() > 0) {
			int i = 0;
			while (i < ListImagesSelected.getInstance().size()) {
				ListImagesSelected.getInstance().get(i).setStyle("-fx-border-style: none");
				ListImagesSelected.getInstance().remove(ListImagesSelected.getInstance().get(i));
			}
		}
			ListImagesSelected.getInstance().add(image);
			panelImage.create(GetImageFXFromStackPane.get(image));
			image.setStyle("-fx-border-style: solid");

		}
}