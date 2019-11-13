package utils;

import java.util.ArrayList;
import java.util.List;

import entities.DocumentX;

// Classe responsável por criar e retornar o Singleton da lista dos arquivos que estão sendo selecionados na janela de edição para serem editadas 
public class ListDocumentsSelected {

	
	private static List<DocumentX> images = null;

	private ListDocumentsSelected() {}
	
	public static List<DocumentX> getInstance(){
		if(images == null) {
			images = new ArrayList<DocumentX>();
		}else {
			return images;
		}
		return images;
	}

}
