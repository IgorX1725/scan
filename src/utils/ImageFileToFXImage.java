package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import gui.ViewController;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

//Classe respons�vel por converter o arquivo de imagem em disco para uma imagem em mem�ria do tipo ImageFX para ser exibida na interface gr�fica
public class ImageFileToFXImage {
	private static BufferedImage tiff = null;
	private static BufferedImage result = null;
	private static Image imageFX = null;
//m�todo que converte a imagem do tipo File para imagemFX
	public static boolean tiffToImage(File imageFile) {

		try {
			tiff = ImageIO.read(imageFile);
			result = new BufferedImage(tiff.getWidth(), tiff.getHeight(), BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(tiff, 0, 0, Color.WHITE, null);
			imageFX = SwingFXUtils.toFXImage(result, null);
			//Armazena a imagem convertida no Map de imagens presentes no lote
			MapImages.getInstance().put(imageFX, imageFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
//	m�todo que recebe a imagem do tipo File para imagemFX em quantidade acima de um arquivo
	public static boolean tiffToImageList(File[] listFiles) {

		for (File imageFile : listFiles) {
			tiffToImage(imageFile);
		}
		return true;
	}
	
//m�todo que converte a imagem do tipo File para imagemFX sem adiciona-la no Map de imagens presentes no lote
	public static Image toImageFX(File image) {
		try {
			tiff = ImageIO.read(image);
			result = new BufferedImage(tiff.getWidth(), tiff.getHeight(), BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(tiff, 0, 0, Color.WHITE, null);
			imageFX = SwingFXUtils.toFXImage(result, null);
			return imageFX;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
// M�todo que retorna os arquivos que est�o no lote e ainda n�o foram adicionados no Map
	public static File[] getNewFiles() {

		File[] batch = ViewController.getBatch().listFiles();
		List<File> newImages = new ArrayList<>();

		for (File image : batch) {
			if (!MapImages.getInstance().containsValue(image)) {
				newImages.add(image);
			}
		}
		return newImages.toArray(new File[newImages.size()]);
	}

}
