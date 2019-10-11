package utils;

import javafx.scene.layout.StackPane;

public class ActionImageSelected {

	public static void addImage(StackPane image) {
		if( ListImagesSelected.getInstance().size() == 0) {
			image.setStyle("-fx-border-style: solid");
			 ListImagesSelected.getInstance().add(image);
		}else {
			int i = 0;
			while ( i  <  ListImagesSelected.getInstance().size()) {
				 ListImagesSelected.getInstance().get(i).setStyle("-fx-border-style: none");
				 ListImagesSelected.getInstance().remove( ListImagesSelected.getInstance().get(i));
			}
			 ListImagesSelected.getInstance().add(image);
			image.setStyle("-fx-border-style: solid");
		}
	}
}
