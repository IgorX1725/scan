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
	//limpa a lista dos documentos selecionados
	public static void clear() {
		images.clear();
		index = 0;
	};
	// metodo que percorre os elementos da lista de documentos selecionados.
	// Quando o parâmetro for true, o metodo retorna o próximo elemento da lista.
	//Quando o parâmetro for false, retorna o elemento atual da lista sem saltar para o próximo
	public static DocumentX next(boolean increment) {
		if (increment) {
			index++;
		}
		if (index < images.size()) {
			return images.get(index);
		} else {
			return null;
		}
	}

}
