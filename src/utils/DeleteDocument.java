package utils;

import java.io.File;
//Classe responsável por deletar o documento do lote em disco
public class DeleteDocument {
	
	public static boolean deleteFile(File deleteFile) {
		
		if(deleteFile.exists()) {
			deleteFile.delete();
				return true;
		}else {
			return false;
		}

	}
}
