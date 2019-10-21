package application;

import entities.Batch;

public class Test {


	public static void main(String[] args) {
		Batch batch = new Batch("C:\\temp\\lote");
		System.out.println( batch.getLastModified().getName());
		batch.createPath();
		System.out.println( batch.getLastModified().getName());
	}

}
