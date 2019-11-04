package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

// Classe responsável por emitir alertas de exceções
public class Alerts {

	public static Optional<ButtonType> showAlert(String title, String header, String content, AlertType typeAlert) {
		Alert alert = null;
		if (typeAlert == Alert.AlertType.CONFIRMATION) {
			alert = new Alert(typeAlert);
			alert.initStyle(StageStyle.TRANSPARENT);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			ButtonType okButton = ButtonType.YES;
			ButtonType noButton = ButtonType.CANCEL;
			alert.getButtonTypes().setAll(okButton, noButton);
		} else {
			alert = new Alert(typeAlert);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
		}
		return alert.showAndWait();
	}

}
