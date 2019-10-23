package utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class MousePressedEvent implements EventHandler<MouseEvent>{

	
	
	@Override
	public void handle(MouseEvent e) {
		StackPane image = (StackPane) e.getSource();
		ActionImageSelected.addImage(image);
		
	}

}
