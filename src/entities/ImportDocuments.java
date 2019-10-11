package entities;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ImportDocuments {

	public static List<File> importFiles(Stage stage) {
		FileChooser fileChoose = new FileChooser();
		
		fileChoose.setTitle("Selecionar Arquivos a serem importados");
		FileChooser.ExtensionFilter filterTiffJpg = new FileChooser.ExtensionFilter("*.tiff",  "*.jpg", "*.tif","*.JPG","*.JPEG", "*.TIFF", "*.TIF"); 
		FileChooser.ExtensionFilter filterAll = new FileChooser.ExtensionFilter("All Files","*.*");
		
		fileChoose.getExtensionFilters().addAll(filterTiffJpg,filterAll);
		List<File> files = fileChoose.showOpenMultipleDialog(stage);
		

		
		return files;
	}
}
