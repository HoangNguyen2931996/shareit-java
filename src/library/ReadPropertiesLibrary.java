package library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesLibrary {
	private Properties properties = null;

	public ReadPropertiesLibrary() {
		properties = new Properties();
	}

	public Properties readProperties() {
		String urlFile = getClass().getResource("").getPath();
		urlFile = urlFile.replaceFirst("/", "");
		urlFile = urlFile.replaceFirst("/classes/library/", "");
		try {
			properties.load(new FileInputStream(urlFile +"/config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}
