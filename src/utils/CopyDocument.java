package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyDocument {

	


	@SuppressWarnings("finally")
	public static boolean copy(String directory) throws IOException {
		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;
		
		String extension = directory.substring(directory.lastIndexOf("."));
		String destination = directory.replace(extension, "_Copy" + extension);
		File file = new File(destination);

		if (file.exists()) {
			int i = 1;
			destination = destination.replace(extension, i + extension);
			while (file.exists()) {
				destination = destination.replace((i - 1) + extension, i + extension);
				System.out.println(extension);
				file = new File(destination);
				i++;
			}
		}
	
		
		try {
			
			sourceChannel = new FileInputStream(directory).getChannel();
			destinationChannel = new FileOutputStream(destination).getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
			return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			if (sourceChannel != null && sourceChannel.isOpen())
				sourceChannel.close();
			if (destinationChannel != null && destinationChannel.isOpen())
				destinationChannel.close();
			return false;
		}

	}
}
