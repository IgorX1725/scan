
package entities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//Esta classe cria o lote dos documentos Digitalizados.
public class Batch {

	// retorna o diretório raiz onde contém os lotes crados em um objeto do tipo
	public static File getFileRootBatch(String source) {
		File root = new File(source);
		if (root.exists()) {
			return root;
		} else {
			root.mkdirs();
			return root;
		}
	}

	// retorna o ultimo lote alterado
	public static File getLastModified(File root) {
		Long last = 0L;
		File lastPath = null;
		File[] listPath = root.listFiles();
		for (File file : listPath) {
			if (file.lastModified() > last) {
				last = file.lastModified();
				lastPath = file;
			}
		}
		return lastPath;
	}

	// cria a pasta que armazenará os documentos digitalizados
	public static File createBatch(File root) {
		File batch = null;
		batch = new File(root.getAbsolutePath() + "\\" + createNameBatch());
		return batch;
	}

//	cria o nome do lote
	private static String createNameBatch() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}

}
