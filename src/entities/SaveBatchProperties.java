package entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class SaveBatchProperties {
	
	static String sourceProperties = (SaveBatchProperties.class.getResource("batch.conf")).toString();
	static String source = null;

	public static String getSource() {
		sourceProperties = sourceProperties.replace("file:/","");
		try (FileInputStream input = new FileInputStream(sourceProperties)) {

			Properties prop = new Properties();

			prop.load(input);

			if (prop.contains("source")) {
				source = prop.getProperty("source");
			} else {
				FileOutputStream output = new FileOutputStream(sourceProperties);
				prop = new Properties();
				prop.setProperty("source", "C:\\temp\\lote");
				prop.store(output, null);
				source = prop.getProperty("source");
			}
		} catch (IOException io) {
			Alerts.showAlert("Erro", "", "ocorreu um erro ao acessar esta opção: " + io.getMessage(), AlertType.ERROR);
		}
		return source;
	}

	public void setSource(String key, String value) {

		try (OutputStream output = new FileOutputStream("batch.conf")) {
			Properties prop = new Properties();
			prop.setProperty(key, value);
			prop.store(output, null);

		} catch (IOException io) {
			Alerts.showAlert("Erro", "", "ocorreu um erro ao salvar: " + io.getMessage(), AlertType.ERROR);
		}
	}

}