package gameConfigurationsReader;

public interface IGuiReader {
	
	public int getGuiLength() throws Exception; 
	public int getGuiwidth() throws Exception; 
	public int GetIntProperty(String property) throws Exception;
	public int getObjectLiftDistance() throws Exception;
	public int getPlayerWidth() throws Exception;
	public int getObjectWidth() throws Exception;
	public int getPlayerHeight() throws Exception;
	public int getObjectHeight() throws Exception;
	public double getBarX(int barNumber) throws Exception;
	public double getBarY(int barNumber) throws Exception;
	public int getBarHeight() throws Exception;
	public int getBarWidth(int barNumber) throws Exception;
	public int adjustStart() throws Exception;
	


}
