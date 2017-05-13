package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;

import Score.ScoreCalculator;
import additionalObjects.Bar;
import gameMemento.Memento;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import object.ObjectAbstract;
import object.ObjectPoolHandler;
import observer.FloorObserver;
import observer.ObserverIF;
import observer.StackObserver;
import player.PlayerAbstract;
import saveANDload.LoadIF;
import saveANDload.SaveIF;
import saveANDload.xml.LoadXML;
import saveANDload.xml.SaveXML;
import view.Game;
import view.MainGui;

public class GameController {
		
	private Memento mementoObject;
	
	private SaveIF savingObject;
	private LoadIF loadingObject;
	private ArrayList<PlayerAbstract> playersList;
	private ArrayList<ObjectAbstract> objectsInScene;
	private LinkedHashMap<PlayerAbstract, Label> labels;
	
	private Game game;
	private ObjectPoolHandler poolHandler;
	private ScoreCalculator scoreCalculator;
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(GameController.class);

	private int  barNo;
	private int maxNoOfObjects;
	private ArrayList<ObserverIF> observers;
	private volatile boolean pausePressed = false;
	
	private GameController() {} 
	
	static GameController gameController = new GameController();

	static {
		gameController.objectsInScene = new ArrayList<ObjectAbstract>();
		try {
			gameController.poolHandler = ObjectPoolHandler.getObjectPoolHandler();
		} catch (Exception e) {
			logger.error("Error occured in object pool.");
		}

	}
	
	
	static public GameController getGameController() {
		return gameController;
	}

	public void setPlayersList(ArrayList<PlayerAbstract> playersList) {
		this.playersList = playersList;
		
	}
	
	public void runController() throws Exception {
		scoreCalculator = ScoreCalculator.getScoreCalculator(SettingController.getDifficultyChoice());
		maxNoOfObjects = MainGui.getDefaultSettingsReader().getMaxNoOfObjects();
		
		gameController.resizeListener();
		gameController.setAnimationHandler();
		gameController.pressKeyHandler();
		gameController.releaseKeyHandler();
		gameController.barNo = 0;
		game = Game.getGame();
		game.drawPane();
		assignButtons();
		setScorePanel();
		game.drawPlayers(StartNewGameController.players);
		game.drawBars(MainController.barList);
		defineObservers();

	}


		
	

	

	protected void drawNewObject() throws Exception {
		if (objectsInScene.size() < maxNoOfObjects) {
			ObjectAbstract object = gameController.poolHandler.getObject();
			if (!(object == null)) {
				object.setBar(MainController.barList[barNo % MainController.barList.length]);
				barNo ++;
				objectsInScene.add(object);
				object.setObservers(observers);
				game.drawObject(object);
			}
		}
	
	}



	protected void moveObjects() throws Exception {
		Point position;
		for (int i = 0; i < objectsInScene.size(); i++) {
			position = objectsInScene.get(i).move();
			if (position != null) {
				objectsInScene.get(i).getImageView().setTranslateX(position.getX());
				objectsInScene.get(i).getImageView().setTranslateY(position.getY());
				objectsInScene.get(i).setPosition(position);
			}
			objectsInScene.get(i).notifyObservers();
			if (objectsInScene.get(i).isRemove()) {
//				remove from view
				game.removeObject(objectsInScene.get(i));
				logger.info("shape removed from scene");
				objectsInScene.get(i).setRemove(false);
				poolHandler.returnObject(objectsInScene.remove(objectsInScene.indexOf(objectsInScene.get(i))));
			}
			
		}
		
	}




	private void performPlayersMovement(PlayerAbstract player, int i) throws Exception {
		Point position = player.moveBy();
		if (position != null) {
			player.getImageView().setTranslateX(position.getX());
			player.getImageView().setTranslateY(position.getY());
			player.setxPosition((int) position.getX());
			player.setyPosition((int) position.getY());
		}
	}

	
	private void updateScore() {
		int score;
		for (PlayerAbstract player : playersList) {
			score = scoreCalculator.calculateScore(player);
			game.updateLabel(player, score, labels);
			
		}
		
	}


	private void defineObservers() throws Exception {
		FloorObserver floorObserver = new FloorObserver();
		observers = new ArrayList<>();
		observers.add(floorObserver);
		for (PlayerAbstract player : playersList) {
			player.setStackObserver();
			observers.add((StackObserver) player.getStackObserver());	
		}
	}
	
	private void setScorePanel() {
		labels = new LinkedHashMap<>();
		for (PlayerAbstract player : playersList) {
			labels.put(player, new Label());
		}
		game.drawLabels(labels);
		
	}



	private void assignButtons() {
		LinkedHashMap<Button, String> buttons = new LinkedHashMap<>();
		buttons.put(new Button(), "Pause");
		buttons.put(new Button(), "Save");
		buttons.put(new Button(), "Load");
		buttons.put(new Button(), "Exit");
		
		game.DrawButtons(buttons);
		defineButtonsHandlers(buttons);
	}
	
	private void defineButtonsHandlers(HashMap<Button, String> buttons) {
		for (Button button : buttons.keySet()) {
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						assignButtonsActions(buttons.get(button));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}
	
	private void assignButtonsActions(String buttonName) throws Exception {
		switch (buttonName) {
		case "Pause":
			if (!pausePressed) {
				pausePressed = true;
			} else {
				pausePressed = false;
			}
			break;
		case "Save":
			pausePressed = true;
			save();
			pausePressed = false;
			break;
		case "Load":
			pausePressed = true;
			load();
			pausePressed = false;
			break;
		case "Exit":
			MainGui.getStage().close();
			break;

		default:
			break;
		}
	}



	private void save() {
		mementoObject = mementoObject.getMemento();
		mementoObject.setMementoConverter(playersList, objectsInScene);
//		String name = openFileExplorerSave();
//		String saveGameName = game.getFromDialogue();
		String saveGameName = "SavedGame";
		savingObject = new SaveXML();
		savingObject.saveToFile(saveGameName, mementoObject);
	}
	
	
//	private String openFileExplorerSave() {
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Choose saving location");
//		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.XML"));
//		java.io.File file1 = fileChooser.showSaveDialog(MainGui.getStage());
//		String fileExtension = "";
//		if (file1 != null) {
//			String fileName = file1.getName();
//			fileExtension = fileName.substring(fileName.indexOf(".") + 1, file1.getName().length());
//			return (fileName.replace(fileExtension, ""));
//		}
//		return null;
//	}
//	
	
	private void load() throws Exception {
//		String name = openFileExplorerSave();
		mementoObject = mementoObject.getMemento();
		loadingObject = new LoadXML();
		mementoObject = loadingObject.loadFile("SavedGame");
		ArrayList<ObjectAbstract> loadedobjects = mementoObject.getObjectArrayList();
		ArrayList<PlayerAbstract> loadedPlayers = mementoObject.getPlayerArrayList();
		StartNewGameController.setPlayers(loadedPlayers);
		this.playersList = loadedPlayers;
		objectsInScene = loadedobjects;
		gameController.runController();

		
	}

	
//	private String openFileExplorerLoad() {
//		 FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("Choose file location");
//
//			//Set extension filter
//			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.XML"));
//
//			//Show save file dialog
//			java.io.File chosenFile = fileChooser.showOpenDialog(MainGui.getStage());
//			String fileExtension = "";
//			String path = "";
////			if (chosenFile != null) {
//				String fileName = chosenFile.getName();
//				fileExtension = fileName.substring(fileName.indexOf(".") + 1, chosenFile.getName().length());
//				return (fileName.replace(fileExtension, ""));
////			}
////			return null;
//	 }
	
	
	public void setAnimationHandler() {
			
			AnimationTimer timer = new AnimationTimer() {
			private long prevTimeMove = System.currentTimeMillis();
			private long prevTimeDraw = System.currentTimeMillis();
	
		        @Override
		        public void handle(long now) {
		        	if(!pausePressed) {
			            try {
							long currentTime = System.currentTimeMillis();
							if (currentTime - prevTimeDraw >= 100) {
		//						draw a ball
								gameController.drawNewObject();
								prevTimeDraw = currentTime;
		
								
							}
							if (currentTime - prevTimeMove >= 5) {
		//						move balls
								gameController.moveObjects();
								gameController.updateScore();
								prevTimeMove = currentTime;
		
								
							}
							int i = 0;
			            	for(PlayerAbstract player : playersList) {
			            		gameController.performPlayersMovement(player, i);
			            		i++;
			            	}
						} catch (Exception e) {
							e.printStackTrace();
						}
		        }
		        }
		    };
			
		    timer.start();
			
		}



	public void pressKeyHandler() {
		MainGui.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	        	try {
	            switch (event.getCode()) {
	            case LEFT:
	            	playersList.get(0).setMoveLeft(true);
	            	break;
	            case RIGHT:
	            	playersList.get(0).setMoveright(true);
	            	break;
	            case A:
	            	playersList.get(1).setMoveLeft(true);
	            	break;
	            case D:
	            	playersList.get(1).setMoveright(true);
	            	break;
	            case NUMPAD4:
	            	playersList.get(2).setMoveLeft(true);
	            	break;
	            case NUMPAD6:
	            	playersList.get(2).setMoveright(true);
	            	break;
	            case K:
		           	playersList.get(3).setMoveLeft(true);
		           	break;
		        case H:
		           	playersList.get(3).setMoveright(true);
		           	break;
	            default:
	           		break;
	            }
	        } catch(Exception e) {}
	        }
	    });
	}
	
	
	
	public void releaseKeyHandler() {
		MainGui.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            case LEFT:
	            	playersList.get(0).setMoveLeft(false);
	            	break;
	            case RIGHT:
	            	playersList.get(0).setMoveright(false);
	            	break;
	            case A:
	            	playersList.get(1).setMoveLeft(false);
	            	break;
	            case D:
	            	playersList.get(1).setMoveright(false);
	            	break;
	            case NUMPAD4:
	            	playersList.get(2).setMoveLeft(false);
	            	break;
	            case NUMPAD6:
	            	playersList.get(2).setMoveright(false);
	            	break;
	            case K:
		           	playersList.get(3).setMoveLeft(false);
		           	break;
		        case H:
		           	playersList.get(3).setMoveright(false);
		           	break;
	            default:
	           		break;
	            }
			}
		});
	}
	
	private void resizeListener() {
		MainGui.getScene().widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		         try {
					game.resizeScreen(MainController.barList, StartNewGameController.players);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
	
		});
		MainGui.getScene().heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	try {
					game.resizeScreen(MainController.barList, StartNewGameController.players);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
	}



}
	
	
	
	
	
	
	
	
	
	
	







