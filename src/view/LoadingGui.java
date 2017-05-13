package view;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;
import javafx.scene.layout.GridPane;

public class LoadingGui {
	

	private GridPane pane;
	
	private IGuiReader guiReader;
	private IPathsReader pathReader;
	private IDefaultSettingsReader defaultSettingsReader;

	
	private LoadingGui() {}
	
	static  LoadingGui loadingGui = new LoadingGui();
	
	static {
		loadingGui.declareConfigurtionFilesReader();
	}
	
	static public LoadingGui getLoadingGui() {
		return loadingGui;
	}
	
	
	public void drawPane() throws Exception {
		pane = new GridPane();
		pane.setId("loadingPane");
		
		MainGui.scene.setRoot(pane);
		MainGui.scene.getStylesheets().add(pathReader.getLoadingSceneStyleSheetPath());
		
		MainGui.stage.setTitle("Circus Of Plates | loading...");
//		MainGui.stage.setScene(MainGui.scene);
		
	}
	
	
	
	
	
	
	
	private void declareConfigurtionFilesReader() {
		guiReader = new GuiPropertiesReader();
		pathReader = new PathsPropertiesReader();
		defaultSettingsReader = new DefaultSettingsPropertiesReader();
		
	}
}
