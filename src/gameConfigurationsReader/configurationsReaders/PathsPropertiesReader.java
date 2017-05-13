package gameConfigurationsReader.configurationsReaders;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import gameConfigurationsReader.IPathsReader;

public class PathsPropertiesReader implements IPathsReader {

	private File configFile;
	

	public PathsPropertiesReader() {
		configFile = new File("resources/configurationFiles/paths.properties");

	}
	@Override
	public Path getPlayerImagesPath() throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Paths.get(prop.getProperty("playersImagesPath"));
	}

	@Override
	public Path getPlayerClassesPath() throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Paths.get(prop.getProperty("playersClassesPath"));
	}
	@Override
	public Path getObjectsImagesPath() throws Exception {
		return getPath("objectsImagesPath");
	}
	@Override
	public Path getObjectsClassesPath() throws Exception {
		return getPath("objectsClassesPath");
	}
	
	@Override
	public Path getBarPath() throws Exception {
		return getPath("barPath");
	}
	
	
	@Override
	public String getMainMenuStyleSheetPath() throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return prop.getProperty("mainMenuStyleSheetPath");
	}
	@Override
	public String getStartNewGameStyleSheetPath() throws Exception {
		return getStringPath("startNewGameStyleSheetPath");
	}
	@Override
	public String getLoadingSceneStyleSheetPath() throws Exception {
		return getStringPath("loadingSceneStyleSheet");
	}
	
	
	@Override
	public String getgameSceneStyleSheetPath() throws Exception {
		return getStringPath("gameSceneStyleSheet");

	}
	@Override
	public String getSettingsSceneStyleSheetPath() throws Exception {
		return getStringPath("SettingsSceneStyleSheet");
	}
	
	
	@Override
	public String getSavePath() throws Exception {
		return getStringPath("saveDirectory");

	}
	private String getStringPath(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return prop.getProperty(property);
	}
	
	
	private Path getPath(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Paths.get(prop.getProperty(property));
	}

	
	
	

}
