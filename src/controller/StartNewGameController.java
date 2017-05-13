package controller;

import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import player.PlayerAbstract;
import player.PlayerFactory;
import view.MainGui;
import view.Mainmenu;
import view.StartNewGame;

public class StartNewGameController implements EventHandler<ActionEvent> {
	
	static ArrayList<PlayerAbstract> players;
	private static List<Path> playersImageList;
	private PlayerFactory factory;
	private StartNewGame startNewGame;
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(StartNewGameController.class);

	
	private StartNewGameController(){}
	
	static StartNewGameController startNewGameController = new StartNewGameController();

	public static StartNewGameController getStartNewGameController() {
		return startNewGameController;
	}
	static String no;
	static int noOfPlayers;
	
	static {
		startNewGameController.players = new ArrayList<PlayerAbstract>(); 
		startNewGameController.playersImageList = MainController.PlayersImagesList;		
		try {
		startNewGameController.factory = new PlayerFactory();
		if (SettingController.getNoOfPlayersChoice() != null) {
			no = (String) SettingController.getNoOfPlayersChoice().subSequence(0, 1);
			noOfPlayers = Integer.parseInt(no);
		} else {
			noOfPlayers = MainGui.getDefaultSettingsReader().getNoOfPlayers();
		}
		} catch (Exception e) {
			logger.error("error in choosing player");
		}
	}
	
	private TextArea ChosenName; // chosen player's name;
	private ImageView imageview; // chosen player's character
	private int playerIndex; // player number
	private int imageIndex;
	private Button nextImage;
	private Button prevImage;
	private Button nextPlayer;
	private Button Back;
	private String title;
	
	

	
	public void run() throws Exception {
		startNewGame= StartNewGame.getStartNewGame();
		startNewGameController.defineButtons();
		startNewGame.drawStage();
		playerIndex = 0;
		startNewGameController.drawPlayerPane();
		startNewGame.Draw(imageview, nextImage, prevImage, nextPlayer, Back, title, ChosenName);
		
	}

	private void defineButtons() throws Exception {
		nextImage = new Button(">");
		prevImage = new Button("<");
		nextPlayer = new Button("Next");
		Back = new Button("Back");
		ChosenName = new TextArea();
		nextImage.setOnAction(this);
		prevImage.setOnAction(this);
		nextPlayer.setOnAction(this);
		Back.setOnAction(this);


	}



	private void drawPlayerPane() {
		imageIndex = 0;
		if (playerIndex == noOfPlayers - 1) { // last choice
			nextPlayer.setText("Start Game");
		}
		if (playerIndex == 0) {
			Back.setText("Back To Main Menu");
		}
		title = "Choose Player " + (playerIndex + 1);
		ChosenName.setUserData("Player_" + (playerIndex + 1));
		ChosenName.setText("Player_" + (playerIndex + 1));
		imageview = new ImageView(playersImageList.get(imageIndex).toString().replace("resources\\", ""));		
	}


	@Override
	public void handle(ActionEvent event) {
		try{
			if (event.getSource() == nextImage) {
				if (!(imageIndex == playersImageList.size() - 1)) {
					imageview = new ImageView(playersImageList.get(++imageIndex).toString().replace("resources\\", ""));
				}
			} else if (event.getSource() == prevImage) {
				if (!(imageIndex == 0)) {
					imageview = new ImageView(playersImageList.get(--imageIndex).toString().replace("resources\\", ""));
				}
			} else if (event.getSource() == nextPlayer) {
				if (!(playerIndex == noOfPlayers - 1)) {
					getUsersChoice();
					playerIndex++;
					drawPlayerPane();
				} else { // start game
					getUsersChoice();
					GameController gameController = GameController.getGameController();
					gameController.setPlayersList(startNewGameController.players);
					gameController.runController();
					logger.info("users choices gathered and starting game...");
				}
			} else if (event.getSource() == Back) {
				if (!(playerIndex == 0)) {
					Back.setText("previous");
					getUsersChoice();
					playerIndex--;
					drawPlayerPane();
				} else { // back to main menu
					Back.setText("Back to main menu");
					MainMenuController mainMenuController = MainMenuController.getMainMenuController();
					mainMenuController.run();
					logger.info("returning back to main menu...");
				}
				
			}
			startNewGame.Draw(imageview, nextImage, prevImage, nextPlayer, Back, title, ChosenName);
		} catch (Exception e) {
			
		}
	}

	private void getUsersChoice() throws Exception {
		PlayerAbstract player = factory.createPlayer(playersImageList.get(imageIndex), ChosenName.getText().toString());
		try{
			players.remove(playerIndex);
		} catch (Exception e) {}
		players.add(playerIndex, player);
	}

	public static ArrayList<PlayerAbstract> getPlayers() {
		return players;
	}
	
	

	public static void setPlayers(ArrayList<PlayerAbstract> players) {
		StartNewGameController.players = players;
	}

	public static int getNoOfPlayers() {
		return noOfPlayers;
	}
	
	
	
	
	

}
