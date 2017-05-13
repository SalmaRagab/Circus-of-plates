package view;


import java.awt.Insets;
import java.util.ArrayList;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartNewGame {
	

	private Pane pane;
	private VBox vbox;
	
	
	private StartNewGame(){}
	
	static StartNewGame startNewGame = new StartNewGame();

	
	
	public static StartNewGame getStartNewGame() {
		return startNewGame;
	}
	


	public void drawStage() throws Exception { 
		pane = new Pane();
		pane.setId("startNewGamePane");
		
		vbox = new VBox();
		vbox.setSpacing(15);
		pane.getChildren().add(vbox);
		
		MainGui.scene.setRoot(pane);
		MainGui.scene.getStylesheets().add(MainGui.getPathsReader().getStartNewGameStyleSheetPath());
		
		MainGui.stage.setTitle("Circus Of Plates | Choose Players");

	}
	
	

	public void Draw(ImageView imageview, Button nextImage, Button prevImage, Button nextPlayer, Button back,
			String title, TextArea name) throws Exception {
		pane.getChildren().remove(vbox);
		vbox = new VBox();
		vbox.setSpacing(25);
		pane.getChildren().add(vbox);
		Label Title = new Label(title);
		vbox.getChildren().add(Title);
		imageview.setFitWidth(MainGui.getGuiPropertiesReader().getPlayerWidth());
		imageview.setFitHeight(MainGui.getGuiPropertiesReader().getPlayerHeight());
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().add(prevImage);
		hbox.getChildren().add(imageview);
		hbox.getChildren().add(nextImage);
		vbox.getChildren().add(hbox);
		name.setPrefColumnCount(10);
		name.setPrefRowCount(1);
		vbox.getChildren().add(name);
		vbox.getChildren().add(nextPlayer);
		vbox.getChildren().add(back);
		
		pane.setId("MainPane");
		Title.setId("title");
		imageview.setId("image");
		hbox.setId("hbox");
		nextImage.setId("nextImg");
		prevImage.setId("nextImg");
		
		
		nextPlayer.getStyleClass().add("buttons");
		back.getStyleClass().add("buttons");
		
		javafx.geometry.Insets i = new javafx.geometry.Insets(MainGui.getScene().getHeight() /2 - MainGui.getGuiPropertiesReader().getPlayerHeight()
				,0,0,MainGui.getScene().getWidth()/2 - MainGui.getGuiPropertiesReader().getPlayerWidth());
		vbox.setPadding(i);
	}
}
