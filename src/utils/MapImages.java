package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;


//Classe responsáve por criar e retornar o singleton do mapa das imagens que estão em memória
//Este mapa tem a referência do respectivo arquivo em disco (File) para que quando qualquer imagem em memória for manipulada, o arquivo em disco também seja manipulado
public class MapImages {
	
	private static Map<Image, File> mapImages = null;
	
	private MapImages() {}

	public static Map<Image, File> getInstance() {
		if(mapImages == null) {
			mapImages = new HashMap<Image, File>();
			return mapImages;
		}
		return mapImages;
	}
	
	
}