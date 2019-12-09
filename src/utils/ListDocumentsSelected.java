package utils;

import java.util.ArrayList;
import java.util.List;

import entities.DocumentX;

// Classe responsável por criar e retornar o Singleton da lista dos arquivos que estão sendo selecionados na janela de edição para serem editadas 
public class ListDocumentsSelected {

	private static List<DocumentX> images = null;
	private static Integer index = null;

	private ListDocumentsSelected() {
	}

	public static List<DocumentX> getInstance() {
		if (images == null) {
			images = new ArrayList<DocumentX>();
			index = 0;
		} else {
			return images;
		}
		return images;
	}

	public static void clear() {
		images.clear();
		index = 0;
	};

	public static DocumentX next(boolean increment) {
		if (index < images.size()) {
			if (increment) {
				return images.get(index++);
			}else {
				return images.get(index);
			}
		} else {
			return null;
		}
	}

}
