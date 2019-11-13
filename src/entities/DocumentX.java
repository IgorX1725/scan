package entities;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class DocumentX {

	private File FileDocument = null;
	private Image imageFX = null;
	private String documentType = null;
	private StackPane stackImage = null;
	
	public DocumentX(File fileDocument, Image imageFX) {
		super();
		FileDocument = fileDocument;
		this.imageFX = imageFX;
	}
	
	public File getFileDocument() {
		return FileDocument;
	}
	public void setFileDocument(File fileDocument) {
		FileDocument = fileDocument;
	}
	public Image getImageFX() {
		return imageFX;
	}
	public void setImageFX(Image imageFX) {
		this.imageFX = imageFX;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public StackPane getStackImage() {
		return stackImage;
	}

	public void setStackImage(StackPane stackImage) {
		this.stackImage = stackImage;
	}
	
	
	
}
