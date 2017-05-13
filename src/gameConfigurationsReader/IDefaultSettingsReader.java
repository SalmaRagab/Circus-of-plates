package gameConfigurationsReader;

public interface IDefaultSettingsReader {
	
	public int getPlayerSpeed() throws Exception;
	public int getContainerSize() throws Exception;
	public int getNumberOfBars() throws Exception;
	public int getNoOfPlayers() throws Exception;
	public int getMaxNoOfObjects() throws Exception;
	public int getObjectSpeed() throws Exception;
	public int getMovingRate() throws Exception;
	public int getDrawingRate() throws Exception;
	public int getAcceptancePercentage() throws Exception;
	public int getDistanceFromLeftStack() throws Exception;
	public int getDistanceFromRightStack() throws Exception;
	
	
	
}
