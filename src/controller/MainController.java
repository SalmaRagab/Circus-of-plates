package controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import additionalObjects.Bar;
import object.ObjectPoolHandler;
import object.ObjectsPool;
import view.LoadingGui;
import view.MainGui;

public class MainController{
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainController.class);

	static List<Path> PlayersImagesList;
	static ObjectsPool objectsPool;
	static Bar[] barList;
		
	private MainController() {}

	static MainController mainController = new MainController();
	
	static {
		objectsPool = ObjectsPool.getObjectPool();
	}

	static public MainController GetMainController() {
		return mainController;
	}
	
	
	public void run() {
		try {
			DefineClasses();
			loadLoadingScreen();
			loadResources();
			loadMainMenusScreen();
		} catch (Exception e) {
			
		}

		
	}
	
	

	
	
	/**
	 * Start by defining  gui and controller classes to be used
	 */
	private static void DefineClasses() {

	}

	private static void loadLoadingScreen() throws Exception {
		LoadingGui loadingGui = LoadingGui.getLoadingGui();
		loadingGui.drawPane();
	}





	private static void loadResources() throws Exception {
		loadPlayersImageList();
		loadObjectsList();
		loadBarList();
		
	}


	private static void loadBarList() throws Exception {
		ArrayList<Path> barsImagePaths = new ArrayList<Path>();
		File[] barsImagesFolder = new File (MainGui.getPathsReader().getBarPath().toString()).listFiles();
		List<Path> pathsList = new ArrayList<Path>();
		barsImagePaths = (ArrayList<Path>) getPathsList(barsImagesFolder, pathsList);
		barList = new Bar[4];
		Bar bar;
		int index = 0;
		int randomBar = (int) ((Math.random() * barsImagePaths.size()));
		for (int i = 0; i < MainGui.getDefaultSettingsReader().getNumberOfBars(); i++) {
			bar = new Bar(MainGui.getGuiPropertiesReader().getBarX(i), MainGui.getGuiPropertiesReader().getBarY(i)
					, MainGui.getGuiPropertiesReader().getBarWidth(i), barsImagePaths.get(randomBar));
			
			barList[index] = bar;
			index++;
		}
		logger.info("bars loaded");
		
	}


	private static void loadPlayersImageList() throws Exception {
		PlayersImagesList = new ArrayList<Path>();
		File[] charactersImagesFolder = new File (MainGui.getPathsReader().getPlayerImagesPath().toString()).listFiles();		
		List<Path> pathsList = new ArrayList<Path>();
		PlayersImagesList = getPathsList(charactersImagesFolder, pathsList);
		logger.info("list of players available created");
		
	}


	private static void loadObjectsList() throws Exception {
		ArrayList<Path> objectImagePaths = new ArrayList<Path>();
		File[] objectsImagesFolder = new File (MainGui.getPathsReader().getObjectsImagesPath().toString()).listFiles();
		List<Path> pathsList = new ArrayList<Path>();
		objectImagePaths = (ArrayList<Path>) getPathsList(objectsImagesFolder, pathsList);
		//////// create object pool <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		createObjectPool(objectImagePaths);
		logger.info("list of objects images created");
	}


	private static void createObjectPool(ArrayList<Path> objectImagePaths) throws Exception {
		ObjectPoolHandler objectPoolHandler = ObjectPoolHandler.getObjectPoolHandler();
		objectsPool = objectPoolHandler.fillObjectsPool(objectImagePaths);
		logger.info("objects pool created");
	}


	/**
	 * start loading till controller finish loading resources
	 * @throws Exception 
	 */
	private static void loadMainMenusScreen() throws Exception {
		MainMenuController mainMenuController = MainMenuController.getMainMenuController();
		mainMenuController.run();
		
	}
	
	private static List<Path> getPathsList(File[] folder,List<Path> pathsList ) throws Exception {
		for (File file : folder) {
			if (file.isDirectory()) {
				pathsList = getPathsList(file.listFiles(), pathsList);
			} else {
				pathsList.add(Paths.get(file.getPath()));
				
			}
		}
		return pathsList;
	}




}

