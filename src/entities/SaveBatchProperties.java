package entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class SaveBatchProperties {

	static String sourceProperties = null;
	static String source = null;

	public static String getSource() {
		sourceProperties = SaveBatchProperties.class.getResource("batch.conf").toString();
		sourceProperties = sourceProperties.replace("file:/", "");

		try (FileInputStream input = new FileInputStream(sourceProperties)) {

			Properties prop = new Properties();

			prop.load(input);
			if (prop.containsKey("source")) {

				source = prop.getProperty("source");
			}else {
				setSource("source","C:\\Temp\\lote");
			}

		} catch (IOException io) {
			Alerts.showAlert("Erro", "", "ocorreu um erro ao acessar esta opção: " + io.getMessage(), AlertType.ERROR);
		}
		return source;
	}

	public static String setSource(String key, String value) {
		sourceProperties = SaveBatchProperties.class.getResource("batch.conf").toString();
		sourceProperties = sourceProperties.replace("file:/", "");
		try (OutputStream output = new FileOutputStream(sourceProperties)) {
			Properties prop = new Properties();
			prop.setProperty(key, value);
			prop.store(output, null);

		} catch (IOException io) {
			Alerts.showAlert("Erro", "", "ocorreu um erro ao salvar: " + io.getMessage(), AlertType.ERROR);
		}
		return SaveBatchProperties.getSource();
	}
}

//
//	public static String getSource() {
//		sourceProperties = sourceProperties.replace("file:/", "");
//		System.out.println(sourceProperties);
//		try (FileInputStream input = new FileInputStream(sourceProperties)) {
//
//			Properties prop = new Properties();
//
//			prop.load(input);
//			source = prop.getProperty(sourceProperties,"c:\\temp\\lote");
//
//		} catch (IOException io) {
//			Alerts.showAlert("Erro", "", "ocorreu um erro ao acessar esta opção: " + io.getMessage(), AlertType.ERROR);
//		}
//		return source;
//	}
//
//	public static void setSource(String key, String value) {
//		sourceProperties = sourceProperties.replace("file:/", "");
//		try (OutputStream output = new FileOutputStream(sourceProperties)) {
//			System.out.println(source);
//			Properties prop = new Properties();
//			prop.setProperty(key, value);
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			Alerts.showAlert("Erro", "", "ocorreu um erro ao salvar: " + io.getMessage(), AlertType.ERROR);
//		}
//	}

//}