package gameConfigurationsReader;

import java.nio.file.Path;

public interface IPathsReader {
	
	
	public Path getPlayerImagesPath() throws Exception ;
	
	public Path getPlayerClassesPath() throws Exception;
	
	public Path getObjectsImagesPath() throws Exception ;
	
	public Path getObjectsClassesPath() throws Exception;

	public String getMainMenuStyleSheetPath() throws Exception;

	public String getStartNewGameStyleSheetPath() throws Exception;
	
	public String getLoadingSceneStyleSheetPath() throws Exception;
	
	public String getSettingsSceneStyleSheetPath() throws Exception;

	public String getgameSceneStyleSheetPath() throws Exception;
	
	public Path getBarPath() throws Exception ;

	public String getSavePath() throws Exception ;

	
	

}
