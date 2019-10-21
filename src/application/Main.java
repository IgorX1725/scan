package application;
	
import java.io.IOException;

import gui.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getResource("/gui/View.fxml")));
			Parent parent = loader.load();
			ViewController controller = (ViewController)loader.getController();
			controller.init(stage);			
			Main.stage = stage;
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
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