package gui.editFiles;

import java.io.File;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.ImageFileToFXImage;
import utils.ListDocuments;

//classe respons�vel por criar a visualiza��o do documento em maior escala do lado esquerdo da janela de edi��o dos arquivos
public class LeftController {
	private static ImageView imageView = null;
	private static ScrollPane scrollImage = null;
	private static File imageRotated = null;
	private static Image imageFromFile = null;

	// M�todo respons�vel por criar  a imagem a ser exibida quando h� o evento do clique do mouse em alguma image do grid de imagens 
	public static void create(Image image) {
		scrollImage = EditFilesController.getLeft();
		scrollImage.setContent(null);
		imageRotated = ListDocuments.containsImageFX(image).getFileDocument();
		imageFromFile = ImageFileToFXImage.toImageFX(imageRotated);
		imageView = new ImageView(imageFromFile);
		if (imageView.getImage().getWidth() > imageView.getImage().getHeight()) {
			imageView.setFitHeight(scrollImage.getWidth());
			imageView.setFitWidth(scrollImage.getHeight() + scrollImage.getWidth() * 0.5);
		} else {
			imageView.setFitHeight(scrollImage.getHeight() + scrollImage.getHeight() * 1.0);
			imageView.setFitWidth(scrollImage.getWidth());
		}
		scrollImage.setContent(imageView);
	}
// M�todo respons�vel por atualizar a imagem a ser exibida quando h� o evento do clique do mouse em alguma image do grid de imagens 
	public static void update(StackPane image) {
		Image imageFX = ((ImageView) image.getChildren().get(0)).getImage();
		scrollImage.setContent(null);
		create(imageFX);
	}
// remove a visualiza��o da imagem quando algum evento relacionado � acionado.
	public static void clearPane() {
		EditFilesController.setLeft(new ScrollPane());
	}
}
