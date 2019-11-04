package entities;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ImageFileToFXImage;


//classe respons�vel por importar arquivo digitais para o lote
// esta classe � usada quando o usu�rio j� possuir o documento digital e n�o precisa digitalizar o documento fisco. 
public class ImportDocuments {

	public static List<File> importFiles(Stage stage) {
		FileChooser fileChoose = new FileChooser();

		fileChoose.setTitle("Selecionar Arquivos a serem importados");
		FileChooser.ExtensionFilter filterTiffJpg = new FileChooser.ExtensionFilter("*.tiff *.jpg *.png", "*.jpg",
				"*.tif", "*.JPG", "*.JPEG", "*.TIFF", "*.TIF", "*.png", "*.PNG");
		fileChoose.getExtensionFilters().addAll(filterTiffJpg);
		List<File> files = fileChoose.showOpenMultipleDialog(stage);

		ImageFileToFXImage.tiffToImageList(files.toArray(new File[files.size()]));

		return files;
	}
}
