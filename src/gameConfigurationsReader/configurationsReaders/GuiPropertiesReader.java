package gameConfigurationsReader.configurationsReaders;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import gameConfigurationsReader.IGuiReader;
import view.MainGui;

public class GuiPropertiesReader implements IGuiReader {

	/**
	 * The configuration file
	 */
	private File configFile;
	

	public GuiPropertiesReader() {
		configFile = new File("resources/configurationFiles/gui.properties");

	}
	@Override
	public int getGuiLength() throws Exception {
		return GetIntProperty("guiLength");
	}

	@Override
	public int getGuiwidth() throws Exception {
		return GetIntProperty("guiWidth");

	}
	
	@Override
	public int getBarHeight() throws Exception {
		return getInt("barHeight");
	}

	@Override
	public int getPlayerWidth() throws Exception {
		return getInt("playerWidth");
	}
	@Override
	public int getPlayerHeight() throws Exception {
		return getInt("playerHeigth");
	}

	@Override
	public int getObjectWidth() throws Exception {
		return getInt("objectWidth");
	}
	@Override
	public int getObjectHeight() throws Exception {
		return getInt("objectHeight");
	}
	@Override
	public int getObjectLiftDistance() throws Exception {
		return getInt("objectLiftDistance");
	}
	
	@Override
	public double getBarY(int barNumber) throws Exception {
		switch (barNumber) {
		case 0:
			return getInt("BarOneY");
		case 1:
			return getInt("BarTwoY");
		case 2:
			return getInt("BarOneY");
		case 3:
			return getInt("BarTwoY");
		default:
			return 0;
		}
	}
	@Override
	public int getBarWidth(int barNumber) throws Exception {
		switch (barNumber) {
		case 0:
			return getInt("barOneWidth");
		case 1:
			return getInt("barTwoWidth");
		case 2:
			return getInt("barThreeWidth");
		case 3:
			return getInt("barFourWidth");
		default:
			return 0;
		}
	}
	@Override
	public double getBarX(int barNumber) throws Exception {
		switch (barNumber) {
		case 0:
			return getInt("BarOneX");
		case 1:
			return getInt("BarOneX");
		case 2:
			return (MainGui.getScene().getWidth() - getBarWidth(2));
		case 3:
			return (MainGui.getScene().getWidth() - getBarWidth(3));
		default:
			return 0;
		}
	}
	@Override
	public int adjustStart() throws Exception {
		return getInt("objectAdjustStart");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public int GetIntProperty(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Integer.parseInt(prop.getProperty(property));
	}

	private int getInt(String property) throws Exception {
		FileReader reader = new FileReader(configFile);
		Properties prop = new Properties();
		prop.load(reader);
		return Integer.parseInt(prop.getProperty(property));	
	}

}
