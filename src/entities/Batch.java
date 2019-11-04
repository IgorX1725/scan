
package entities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//Esta classe cria o lote dos documentos Digitalizados.
public class Batch {

	private File root;
	private File batch;
	private List<File> listPath;

	public Batch(String source) {
		root = new File(source);
		listPath = new ArrayList<File>();
		if (getLastModified() != null) {
			batch = getLastModified();
		}
	}
//	cria o nome do lote
	private String createNameBatch() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}
//cria a pasta que armazenará os documentos digitalizados
	public boolean createPath() {
		batch = new File(root.getAbsolutePath() + "\\" + createNameBatch());
		if (!batch.exists()) {
			batch.mkdirs();
			return true;
		} else {
			return false;
		}

	}
//retorna a lista de todos os lotes criados
	public List<File> getChildFolder() {
		if (root.listFiles().length == 0) {
			createPath();
		}
		return Arrays.asList(root.listFiles());
	}
// retorna o ultimo lote alterado
	public File getLastModified() {
		Long last = 0L;
		File lastPath = null;
		listPath = getChildFolder();
		for (File file : listPath) {
			if (file.lastModified() > last) {
				last = file.lastModified();
				lastPath = file;
			}
		}
		return lastPath;
	}
// deleta os lotes
	public boolean deleteFiles() {
		return root.delete();
	}
//retorna o nome dos lotes
	public String[] listStrinFiles() {
		return root.list();
	}
// retorna os documentos do lote
	public File[] listFiles() {
		return batch.listFiles();
	}
//retorna o caminho completo onde o lote está armazenado
	public String getSource() {
		return batch.getPath();
	}
//retorna o nome da pasta raiz
	public String getRoot() {
		return root.getParent();
	}
//retorna o nome do lote
	public String getNameBatch() {
		return root.getName();
	}

}
