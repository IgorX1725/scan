package gui.editFiles;

import javafx.scene.control.ScrollPane;

public class RightController {

	
	// Metodo para atualizar a visualização dos documentos quando houver alguma
	// alteração no lote
	public static void updateImages() {
		ScrollPane paneImages = createPaneImages();
		EditFilesController.setRight(paneImages);

	}

// Metodo para criar o painel dos documentos presentes no lote
	public static ScrollPane createPaneImages() {
		ScrollPane paneImages = new ScrollPane(GridWithImages.create());
		return paneImages;
	}
}
