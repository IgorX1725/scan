package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;


//Classe respons�ve por criar e retornar o singleton do mapa das imagens que est�o em mem�ria
//Este mapa tem a refer�ncia do respectivo arquivo em disco (File) para que quando qualquer imagem em mem�ria for manipulada, o arquivo em disco tamb�m seja manipulado
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