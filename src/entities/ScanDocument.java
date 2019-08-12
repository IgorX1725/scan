package entities;

import java.io.IOException;

import entities.exceptions.DomainExceptions;

public class ScanDocument {


	public static void scanningDocument(String command) throws IOException, DomainExceptions {

		Process p = null;

		p = Runtime.getRuntime().exec(command);
		java.io.InputStream stdin = p.getInputStream();
		java.io.InputStreamReader isr = new java.io.InputStreamReader(stdin);
		java.io.BufferedReader br = new java.io.BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.contentEquals("No scanned pages to export."))
				throw new DomainExceptions("não há páginas para digitalizar");
			if(line.contentEquals("O scanner escolhido est  offline."))
				throw new DomainExceptions("O scanner escolhido está offline.");
			if (line.contentEquals("An unexpected error occurred."))
				throw new DomainExceptions("Um erro inesperado ocorreu");
			System.out.println(line);
		}

	}

}
