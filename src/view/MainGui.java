package view;

import controller.MainController;
import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainGui extends Application {
	
	static  Stage stage;
	static Scene scene;
	private GridPane pane;
	
	static private LoadingGui loadingGui;
	static private MainGui mainGui = new MainGui();
	
	static IGuiReader guiPropertiesReader = new GuiPropertiesReader();
	static IDefaultSettingsReader defaultSettingsReader = new DefaultSettingsPropertiesReader();
	static IPathsReader pathsReader = new PathsPropertiesReader();
	

	public static Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new GridPane();
		scene = new Scene(pane);
		stage = primaryStage;
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);
		MainController mainController = MainController.GetMainController();
		mainController.run();
	}
	

	public static IGuiReader getGuiPropertiesReader() {
		return guiPropertiesReader;
	}

	public static IDefaultSettingsReader getDefaultSettingsReader() {
		return defaultSettingsReader;
	}

	public static IPathsReader getPathsReader() {
		return pathsReader;
	}

}
