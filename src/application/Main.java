package application;
	
import java.io.IOException;

import gui.ViewControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getResource("/gui/View.fxml")));
			Parent parent = loader.load();
			ViewControler controller = (ViewControler)loader.getController();
			controller.init(stage);
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
