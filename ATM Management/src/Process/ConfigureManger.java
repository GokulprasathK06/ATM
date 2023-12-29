package Process;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConfigureManger {
	private Properties properties = new Properties();
	private static final Logger logger = Logger.getLogger(ConfigureManger.class.getName());
    

    public void ConfigManager() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Config.properties")) {
            properties.load(inputStream);
        } catch (IOException exception) {
        	logger.log(Level.SEVERE, "Error while loading Config.properties", exception);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
