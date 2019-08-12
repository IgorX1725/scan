package entities;

import java.io.File;

public class Batch {

	private File sourse;

	public Batch(File nameBatch) {
		this.sourse = nameBatch;
	}

	public boolean createPath() {
		if (sourse.exists()) {
			return false;
		} else {
			sourse.mkdirs();
			return true;
		}
	}

	public boolean deletePath() {
			return sourse.delete();
	}

	public String[] listStrinFiles() {
		return sourse.list();
	}
	
	public File[] listFiles() {
		return sourse.listFiles();
	}

	public String getSource() {
		return sourse.getPath();
	}

	public String getRoot() {
		return sourse.getParent();
	}

	public String getNameBatch() {
		return sourse.getName();
	}

}
