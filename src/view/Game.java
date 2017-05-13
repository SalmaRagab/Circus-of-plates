package view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import additionalObjects.Bar;
import controller.StartNewGameController;
import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.ObjectAbstract;
import player.PlayerAbstract;

public class Game {
	
//	private GridPane pane;
//	private Group pane;
	private Pane pane;
	private HBox hbox;

	
	private Game(){}
	
	static Game game = new Game();

	
	
	static public Game getGame() {
		return game;
	}
	
	public void drawPane() throws Exception {
		pane = new Pane();
		pane.setId("GamePane");
		
		hbox = new HBox();
		hbox.setTranslateX(0);
		hbox.setTranslateY(0);
		pane.getChildren().add(hbox);
		
		
		MainGui.scene.setRoot(pane);
		MainGui.scene.getStylesheets().add(MainGui.getPathsReader().getgameSceneStyleSheetPath());

		MainGui.stage.setTitle("Circuits Of Plates | Game");
		
	}
	

	public void DrawButtons(LinkedHashMap<Button, String> buttons) {
		hbox.setSpacing(15);
		for (Button button : buttons.keySet()) {
			button.setText(buttons.get(button));
			button.getStyleClass().add("b2");
			hbox.getChildren().add(button);
		}

	}

	public void drawLabels(LinkedHashMap<PlayerAbstract,Label> labels) {

		for (PlayerAbstract player : labels.keySet()) {
			String space = " ";
			for (int i = 0; i < player.getName().length() /2 ; i++ ) {
				space += " ";
			}
			labels.get(player).setText(player.getName() + "\n" + space +  "0");
			hbox.getChildren().add(labels.get(player));
		}	
	}

	public void drawImageView(ImageView imageView, int x, int y) {
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        pane.getChildren().add(imageView);
	}

	public void drawPlayers(ArrayList<PlayerAbstract> players) throws Exception {
			Point position = new Point();
			ImageView imageView;
			int i = 0;
			for (PlayerAbstract player : players) {
				imageView = new ImageView(player.getImgPath().toString().replace("resources\\", ""));
				imageView.setFitWidth(MainGui.getGuiPropertiesReader().getPlayerWidth());
				imageView.setFitHeight(MainGui.getGuiPropertiesReader().getPlayerHeight());
				player.setImageView(imageView);
				position = getPlayerPosition(i, imageView);
				player.setxPosition((int) position.getX());
				player.setyPosition((int) position.getY());
				drawImageView(imageView, (int) position.getX(), (int) position.getY());
				i++;
			}
			
			
		}



	private Point getPlayerPosition(int noOfPlayerDrawn, ImageView imageView) throws Exception {
		Point location = new Point();
		int noOfPlayers = StartNewGameController.getNoOfPlayers();
		double screenWidth = MainGui.getScene().getWidth();
		double increaseDistance = screenWidth / (noOfPlayers+1);
		int xCoordinates = (int) (noOfPlayerDrawn * increaseDistance + increaseDistance - imageView.getFitWidth()) ;
		int yCoordinates = (int) (MainGui.getScene().getHeight() - imageView.getFitHeight());
		location.x = xCoordinates;
		location.y = yCoordinates;
		return location;
	}

	public void drawBars(Bar[] Bar) throws Exception {
			int i = 0;
			for (Bar bar : Bar) {
				bar.setImageView(bar.getImageView());
				drawImageView(bar.getImageView(), (int) MainGui.getGuiPropertiesReader().getBarX(i), (int) MainGui.getGuiPropertiesReader().getBarY(i));
				i++;
			}
			
		}

	public void resizeScreen(Bar[] Bar, ArrayList<PlayerAbstract> players) throws Exception {
			int i = 0;
	//		move players positions
			for (PlayerAbstract player : players) {
				Point position = getPlayerPosition(i, player.getImageView());
				player.getImageView().setTranslateX(position.getX());
				player.getImageView().setTranslateY(position.getY());
				player.setxPosition((int) position.getX());
				player.setyPosition((int) position.getY());
				i++;
			}
			i = 0;
			for (Bar bar : Bar) {
				bar.getImageView().setTranslateX(MainGui.getGuiPropertiesReader().getBarX(i));
				bar.getImageView().setTranslateY(MainGui.getGuiPropertiesReader().getBarY(i));
				i++;
			}
			
		}
	
	public void drawObject(ObjectAbstract object) throws Exception {
		drawImageView(object.getImageView(), (int) object.getBar().getInitialX(), (int) object.getBar().getInitialY()- MainGui.getGuiPropertiesReader().getObjectLiftDistance());
	}
	
	public void updateLabel(PlayerAbstract player, int score, LinkedHashMap<PlayerAbstract, Label> labels) {
		String space = " ";
		for (int i = 0; i < player.getName().length() /2 ; i++ ) {
			space += " ";
		}
		labels.get(player).setText(player.getName() + "\n" + space +  score);
		
	}

	public void removeObject(ObjectAbstract object) {
		pane.getChildren().remove(object.getImageView());
	}

	public String getFromDialogue() {
		
		 Pane pane2 = new Pane();
		 Scene scene2 = new Scene(pane2, 500, 500);
		 
		 VBox vbox1 = new VBox();
		    
		    Label label = new Label("Please Enter a name for your game");
		    
		    TextArea text = new TextArea();
		    text.setText(Long.toString(System.currentTimeMillis()));
		    text.setPrefColumnCount(8);
			text.setPrefRowCount(1);
			
		    HBox hBox1 = new HBox();
		    Button save = new Button("Save");
		    Button cancel = new Button("Cancel");
		    hBox1.getChildren().addAll(save, cancel);
		    
		    
		    
		    vbox1.getChildren().addAll(label, text, hBox1);
		    
		    pane2.getChildren().add(vbox1);
		 
	     Stage newStage = new Stage();
	     newStage.setScene(scene2);
	     //tell stage it is meannt to pop-up (Modal)
	     newStage.initModality(Modality.WINDOW_MODAL);
	     newStage.show();
	     newStage.setTitle("Pop up window");
	     
	     return ("hi");
//		
//		 	Stage dialogStage = new Stage();
////		    dialogStage.initModality(Modality.WINDOW_MODAL);
//
//		    VBox vbox1 = new VBox();
//		    
//		    Label label = new Label("Please Enter a name for your game");
//		    
//		    TextArea text = new TextArea();
//		    text.setText(Long.toString(System.currentTimeMillis()));
//		    text.setPrefColumnCount(8);
//			text.setPrefRowCount(1);
//			
//		    HBox hBox1 = new HBox();
//		    Button save = new Button("Save");
//		    Button cancel = new Button("Cancel");
//		    hBox1.getChildren().addAll(save, cancel);
//		    
//		    
//		    
//		    vbox1.getChildren().addAll(label, text, hBox1);
//		    dialogStage.setScene(new Scene(vbox1));
//		    dialogStage.show();
//		    
//		    
//		    return text.getText();
	}
	


	
	

}
