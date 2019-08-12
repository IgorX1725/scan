package application;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Batch;
import entities.ScanDocument;
import entities.enums.Profiles;
import entities.exceptions.DomainExceptions;

public class Program {

	public static void main(String[] args) {
		String nameBatch = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
		Batch batch = new Batch(new File("C:\\Temp\\" + nameBatch));

		try {
			ScanDocument.scanningDocument("cmd /c naps2.console -o " + batch.getSource()
			+ "\"\\$(n).tiff\" --split --progress -p " + Profiles.FRENTE.toString().toLowerCase());
		} catch (IOException e) {
			System.out.println("Ocorreu um erro inesperado:"+e.getMessage());
		}catch(DomainExceptions e) {
			batch.deletePath();
			System.out.println("Ocorreu um Problema: "+e.getMessage());
		}

	}
}
