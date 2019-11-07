package application;
	
import java.io.IOException;

import gui.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.AddIconOnScene;


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
			AddIconOnScene.add(stage,new Image("\\icons\\X_Icon.png"));
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
