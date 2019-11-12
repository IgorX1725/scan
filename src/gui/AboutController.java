package gui;

import java.io.IOException;

import gui.util.Alerts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.AddIconOnScene;

public class AboutController {
		
		static FXMLLoader loader = null;
		static AnchorPane anchorPaneAbout;
		static Scene aboutScene;
		static Stage aboutStage;
	
	public static void showWindow() {
		try {
			loader = new FXMLLoader((AboutController.class.getResource("/gui/About.fxml")));
			anchorPaneAbout = loader.load();
			aboutScene = new Scene(anchorPaneAbout);
			aboutStage = new Stage();
			aboutStage.setScene(aboutScene);
			AddIconOnScene.add(aboutStage, new Image("\\icons\\X_icon.png"));
			aboutStage.setResizable(false);
			aboutStage.setTitle("Sobre");
			aboutStage.show();
		} catch (IOException e) {
			Alerts.showAlert("Erro", "", "Erro ao Exibir a Janela:" + e.getMessage(), AlertType.ERROR);
		}
	}
}
