package entities;

import java.io.File;


public class Batch {

	private File source;

	public Batch(String nameBatch) {
		this.source = new File(nameBatch);
		createPath();
	}

	private boolean createPath() {
		if (!source.exists()) {
			source.mkdirs();
			return true;
		}else {
			return false;
		}

	}

	public boolean deleteFiles() {
		return source.delete();
	}

	public String[] listStrinFiles() {
		return source.list();
	}
	
	public File[] listFiles() {
		return source.listFiles();
	}

	public String getSource() {
		return source.getPath();
	}

	public String getRoot() {
		return source.getParent();
	}

	public String getNameBatch() {
		return source.getName();
	}

}
