package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;



public class TiffToFXImage{
	private static BufferedImage tiff = null;
	private static BufferedImage result = null;
	private static Image imageFX = null;
	
	
	public static boolean tiffToImage(File imageFile) {
		
		try {
			tiff = ImageIO.read(imageFile);
			result = new BufferedImage(tiff.getWidth(), tiff.getHeight(), BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(tiff, 0, 0, Color.WHITE, null);
			imageFX = SwingFXUtils.toFXImage(result, null); 
			MapImages.getInstance().put(imageFX,imageFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	public static boolean tiffToImageList(File[] listFiles){
			
		for(File imageFile : listFiles) {
			tiffToImage(imageFile);
		}
		return true;
	}
	
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

}
