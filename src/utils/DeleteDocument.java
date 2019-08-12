package utils;

import java.io.File;

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
