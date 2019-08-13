package entities;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportDocuments {

	public static File selectFiles(Stage stage) {
		FileChooser fileChoose = new FileChooser();
		File file = fileChoose.showOpenDialog(stage);
		if (file != null) {
			return file;
		} else {
			return null;
		}
	}
}
