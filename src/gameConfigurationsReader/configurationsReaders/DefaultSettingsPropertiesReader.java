package gameConfigurationsReader.configurationsReaders;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import gameConfigurationsReader.IDefaultSettingsReader;

public class DefaultSettingsPropertiesReader implements IDefaultSettingsReader {

	/**
	 * The configuration file
	 */
	private File configFile;
	

	public DefaultSettingsPropertiesReader() {
		configFile = new File("resources/configurationFiles/defaultSettings.properties");

	}
	@Override
	public int getPlayerSpeed() throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Integer.parseInt(prop.getProperty("playersSpeed"));
		
		
	}
	@Override
	public int getContainerSize() throws Exception {
		return getInt("objectContainerSize");
	}
	



	@Override
	public int getNoOfPlayers() throws Exception {
		return getInt("noOfPlayers");
	}
	
	@Override
	public int getNumberOfBars() throws Exception {
		return getInt("numberOfBars");
	}

	@Override
	public int getMaxNoOfObjects() throws Exception {
		return getInt("maxNoOfObjectsInScene");
	}
	@Override
	public int getObjectSpeed() throws Exception {
		return getInt("objectsSpeed");

	}
	@Override
	public int getMovingRate() throws Exception {
		return getInt("rateofMovingObjects");
	}
	@Override
	public int getDrawingRate() throws Exception {
		return getInt("rateOfDrawingObjects");

	}
	@Override
	public int getAcceptancePercentage() throws Exception {
		return getInt("objectAcceptancePercentage");

	}
	private int getInt(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Integer.parseInt(prop.getProperty(property));	
	}
	private double getDouble(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Double.parseDouble(prop.getProperty(property));
	}
	@Override
	public int getDistanceFromLeftStack() throws Exception {
		return getInt("distanceFromLeftStack");
	}
	@Override
	public int getDistanceFromRightStack() throws Exception {
		return getInt("distanceFromRightStack");
	}
	
}
