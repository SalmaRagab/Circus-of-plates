package view;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Mainmenu {
	

	private GridPane pane;
	private VBox vbox;
	
	
	private Mainmenu(){}
	
	static Mainmenu mainmenu = new Mainmenu();
		
	public static Mainmenu getMainmenu() {
		return mainmenu;
	}
	

	


	public void drawStage() throws Exception {
		
		pane = new GridPane();
		pane.setId("mainMenuGridPane");
		
		MainGui.scene.setRoot(pane);
		MainGui.scene.getStylesheets().add(MainGui.getPathsReader().getMainMenuStyleSheetPath());
		
		
		MainGui.stage.setTitle("Circuis Of Plates | Main Menu");
	}


	
	public void DrawButtons(LinkedHashMap<Button, String> buttons) {
		vbox = new VBox();
		vbox.setSpacing(15);
		for (Button button : buttons.keySet()) {
			button.setText(buttons.get(button));
			button.getStyleClass().add("buttons");
			button.setId(buttons.get(button).replace(" ", ""));
			vbox.getChildren().add(button);
		}
		pane.getChildren().add(vbox);
		Insets i = new Insets(MainGui.getScene().getHeight()/3, 0, 0, MainGui.getScene().getWidth()/2.2);
		vbox.setPadding(i);
		
		
	}




}
