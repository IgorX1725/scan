package utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.StackPane;

// Classe responsável por criar e retornar o Singleton da lista dos arquivos que estão sendo selecionados na janela de edição para serem editadas 
public class ListImagesSelected {

	
	private static List<StackPane> images = null;

	private ListImagesSelected() {}
	
	public static List<StackPane> getInstance(){
		if(images == null) {
			images = new ArrayList<StackPane>();
		}else {
			return images;
		}
		return images;
	}

}
