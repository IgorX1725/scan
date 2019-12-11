package application;
	

import java.io.IOException;
import gui.main.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.AddIconOnScene;

public class Main extends Application {
	private static Stage stage;
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/main/Main.fxml"));
			Parent parent = loader.load();
			MainController controller = (MainController)loader.getController();
			controller.init(stage);			
			Main.stage = stage;
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			AddIconOnScene.add(stage,new Image("/icons/X_Icon.png"));
			stage.setTitle("ScannerX");
			stage.show();
			controller.retrieveBatch();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
