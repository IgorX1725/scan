package entities;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class DocumentX {

	private File FileDocument = null;
	private Image imageFX = null;
	private StackPane stackImage = null;
	
	private Integer rAOwner = null;
	private String nameOwner = null;
	private Long cPFOwner = null;
	private String level = null;
	private String category = null;
	private String documentType = null;
	private String course = null;
	private Integer processNumber = null;
	
	public DocumentX(File fileDocument, Image imageFX) {
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

	public Integer getrAOwner() {
		return rAOwner;
	}

	public void setrAOwner(Integer rAOwner) {
		this.rAOwner = rAOwner;
	}

	public String getNameOwner() {
		return nameOwner;
	}

	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	public Long getcPFOwner() {
		return cPFOwner;
	}

	public void setcPFOwner(Long cPFOwner) {
		this.cPFOwner = cPFOwner;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}
	
	
	
}
