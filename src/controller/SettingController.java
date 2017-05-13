package controller;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;

import com.sun.javafx.scene.control.skin.ButtonSkin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import view.SettingsView;

public class SettingController {
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(SettingController.class);

	static SettingController settingController = new SettingController();

	private SettingController() {} 
	
	
	static public SettingController getSettingController() {
		return settingController;
	}
	
	static SettingsView settingsView = SettingsView.getSettingsView();
	
	static private ToggleGroup players;
	static private ToggleGroup difficulty;
	
	private String noOfPlayersChoice;
	
	private String difficultyChoice;
	
	public void runSettings() {
		try {
			settingController.assignPlayerToggle();
			settingController.assignDifficultyToggle();
			settingController.DoneButton();
			settingsView.drawPane();
			logger.info("settings window created successfully");

			
			
		} catch (Exception e) {
			logger.error("couldnot build settings view");
		}
		
	}

	private void assignPlayerToggle() {
		players = new ToggleGroup();
		LinkedHashMap<RadioButton, String> playersToggleButtons = new LinkedHashMap<>();
		HBox playersHBox = new HBox();
		RadioButton r = new RadioButton();
		r.setSelected(true);
		playersToggleButtons.put(r, "1 Player");
		playersToggleButtons.put(new RadioButton(), "2 Players");
		playersToggleButtons.put(new RadioButton(), "3 Players");
		playersToggleButtons.put(new RadioButton(), "4 Players");
		for (RadioButton button : playersToggleButtons.keySet()) {
			button.setText(playersToggleButtons.get(button));
			button.setUserData(playersToggleButtons.get(button));
			button.setToggleGroup(players);
			playersHBox.getChildren().add(button);
		}
		settingsView.setPlayersToggle(playersHBox);
		
	}

	private void assignDifficultyToggle() {
		difficulty = new ToggleGroup();
		LinkedHashMap<RadioButton, String> difficultyToggleButtons = new LinkedHashMap<>();
		HBox difficultyHBox = new HBox();
		RadioButton r = new RadioButton();
		r.setSelected(true);
		difficultyToggleButtons.put(r, "Easy");
		difficultyToggleButtons.put(new RadioButton(), "Medium");
		difficultyToggleButtons.put(new RadioButton(), "Difficult");
		for (RadioButton button : difficultyToggleButtons.keySet()) {
			button.setText(difficultyToggleButtons.get(button));
			button.setUserData(difficultyToggleButtons.get(button));
			button.setToggleGroup(difficulty);
			difficultyHBox.getChildren().add(button);
		}
		settingsView.setDifficultyToggle(difficultyHBox);		
	}
	
	private void DoneButton() {
		Button done = new Button("Done");
		settingsView.setDone(done);
		done.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				noOfPlayersChoice = players.getSelectedToggle().getUserData().toString();
				difficultyChoice = difficulty.getSelectedToggle().getUserData().toString();
				MainMenuController mainMenuController = MainMenuController.getMainMenuController();
				try {
					mainMenuController.run();
				} catch (Exception e) {
				}
				
			}
		});
	}
	
	public static String getNoOfPlayersChoice() {
		return settingController.noOfPlayersChoice;
	}


	public static String getDifficultyChoice() {
		return settingController.difficultyChoice;
	}

	
	
	
	
}



