package controller;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.MainGui;
import view.Mainmenu;

public class MainMenuController {
	
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainMenuController.class);

	private MainMenuController () {}

	static MainMenuController mainMenuController = new MainMenuController();
	
	static MainMenuController getMainMenuController() {
		return mainMenuController;
	}
	
	public void run() throws Exception {
		Mainmenu mainMenu = Mainmenu.getMainmenu();
		mainMenu.drawStage();
		assignButtons(mainMenu);
		
		
	}

	private void assignButtons(Mainmenu mainMenu) {
		LinkedHashMap<Button, String> buttons = new LinkedHashMap<>();
		buttons.put(new Button(), "Start Game");
		buttons.put(new Button(), "Settings");
		buttons.put(new Button(), "Exit Game");
		
		assignButtonsHandlers(buttons);
		mainMenu.DrawButtons(buttons);
		
		
	}

	private void assignButtonsHandlers(LinkedHashMap<Button, String> buttons) {
		for (Button button : buttons.keySet()) {
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						assignButtonsActions(buttons.get(button));
					} catch (Exception e) {
						logger.error("error happened in main menu buttons");
					}
				}
			});
		}
		
	}
	
	private void assignButtonsActions(String buttonName) throws Exception {
		switch (buttonName) {
		case "Start Game":
			StartNewGameController startNewGameController = StartNewGameController.getStartNewGameController();
			startNewGameController.run();
			break;
		case "Settings":
			SettingController settingController = SettingController.getSettingController();
			settingController.runSettings();
			break;
		case "Exit Game":
			MainGui.getStage().close();
			break;

		default:
			break;
		}
	}


}
