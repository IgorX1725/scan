package utils;

import gui.EditFilesController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
// Classe respons�vel por criar o evento do clique do mouse sobre a imagem que ser� editada 
public class MousePressedEvent implements EventHandler<MouseEvent>{

	
	
	@Override
	public void handle(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && EditFilesController.getPressedkeys().contains(KeyCode.CONTROL)) {
        	StackPane image = (StackPane) e.getSource();
			ActionImageSelected.mouseClickedAndButtonCtrlPressed(image);
        }else {
			StackPane image = (StackPane) e.getSource();
			ActionImageSelected.mouseClickedOnly(image);
        }
	}

}
