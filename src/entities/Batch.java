package entities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	private String createNameBatch() {
		return new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	}

	public boolean createPath() {
		batch = new File(root.getAbsolutePath() + "\\" + createNameBatch());
		if (!batch.exists()) {
			batch.mkdirs();
			return true;
		} else {
			return false;
		}

	}

	public List<File> getChildFolder() {
		if (root.listFiles().length == 0) {
			createPath();
		}
		return Arrays.asList(root.listFiles());
	}

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

	public boolean deleteFiles() {
		return root.delete();
	}

	public String[] listStrinFiles() {
		return root.list();
	}

	public File[] listFiles() {
		return batch.listFiles();
	}

	public String getSource() {
		return batch.getPath();
	}

	public String getRoot() {
		return root.getParent();
	}

	public String getNameBatch() {
		return root.getName();
	}

}
