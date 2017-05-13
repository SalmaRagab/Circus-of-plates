package view;

import java.util.LinkedHashMap;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsView {
	private Pane pane;
	private VBox vbox;
	private HBox playersToggle;
	private HBox difficultyToggle;
	private Button done;
	
	





	private SettingsView(){}
	
	static SettingsView settingsView = new SettingsView();

	
	
	static public SettingsView getSettingsView() {
		return settingsView;
	}
	
	
	
	
	public void drawPane() throws Exception {
		pane = new Pane();
		pane.setId("SettingsPane");

		vbox = new VBox();
		pane.getChildren().add(vbox);
		
		drawLabels();
		vbox.getChildren().add(done);
		
		
		MainGui.scene.setRoot(pane);
		MainGui.scene.getStylesheets().add(MainGui.getPathsReader().getSettingsSceneStyleSheetPath());

		MainGui.stage.setTitle("Circuits Of Plates | Settings");
		
	}
	
	
	public void drawLabels() {
		vbox.setSpacing(15);
		Label playersLabel = new Label("No Of Players");
		vbox.getChildren().add(playersLabel);
		playersToggle.setSpacing(15);
		vbox.getChildren().addAll(playersToggle);
		Label difficultyLabel = new Label("Difficulty");
		vbox.getChildren().add(difficultyLabel);
		difficultyToggle.setSpacing(15);
		vbox.getChildren().addAll(difficultyToggle);
		
 

	}
	
	public void setPlayersToggle(HBox playersToggle) {
		this.playersToggle = playersToggle;
	}




	public void setDifficultyToggle(HBox difficultyToggle) {
		this.difficultyToggle = difficultyToggle;
	}



	public void setDone(Button done) {
		this.done = done;
	}

	




}



	

	

		

	
//
//
//
//	public void drawLabels(LinkedHashMap<PlayerAbstract,Label> labels) {
//		for (PlayerAbstract player : labels.keySet()) {
//			String space = " ";
//			for (int i = 0; i < player.getName().length() /2 ; i++ ) {
//				space += " ";
//			}
//			labels.get(player).setText(player.getName() + "\n" + space +  "0");
//			hbox.getChildren().add(labels.get(player));
//		}	
//	}
//
//	public void drawImageView(ImageView imageView, int x, int y) {
//        imageView.setTranslateX(x);
//        imageView.setTranslateY(y);
//        pane.getChildren().add(imageView);
//	}
//
//	public void drawPlayers(ArrayList<PlayerAbstract> players) throws Exception {
//			Point position = new Point();
//			ImageView imageView;
//			int i = 0;
//			for (PlayerAbstract player : players) {
//				imageView = new ImageView(player.getImgPath().toString());
//				imageView.setFitWidth(guiReader.getPlayerWidth());
//				imageView.setFitHeight(guiReader.getPlayerHeight());
//				player.setImageView(imageView);
//				position = getPlayerPosition(i, imageView);
//				player.setxPosition((int) position.getX());
//				player.setyPosition((int) position.getY());
//				drawImageView(imageView, (int) position.getX(), (int) position.getY());
//				i++;
//				
//			}
//			
//			
//		}
//
//
//
//	private Point getPlayerPosition(int noOfPlayerDrawn, ImageView imageView) throws Exception {
//		Point location = new Point();
//		int noOfPlayers = defaultSettingsReader.getNoOfPlayers();
//		double screenWidth = MainGui.getScene().getWidth();
//		double increaseDistance = screenWidth / (noOfPlayers+1);
//		int xCoordinates = (int) (noOfPlayerDrawn * increaseDistance + increaseDistance - imageView.getFitWidth()) ;
//		int yCoordinates = (int) (MainGui.getScene().getHeight() - imageView.getFitHeight());
//		location.x = xCoordinates;
//		location.y = yCoordinates;
//		return location;
//	}
//
//	public void drawBars(Bar[] Bar) throws Exception {
//			int i = 0;
//			for (Bar bar : Bar) {
//				bar.setImageView(bar.getImageView());
//				drawImageView(bar.getImageView(), (int) guiReader.getBarX(i), (int) guiReader.getBarY(i));
//				i++;
//			}
//			
//		}
//
//	public void resizeScreen(Bar[] Bar, ArrayList<PlayerAbstract> players) throws Exception {
//			int i = 0;
//	//		move players positions
//			for (PlayerAbstract player : players) {
//				Point position = getPlayerPosition(i, player.getImageView());
//				player.getImageView().setTranslateX(position.getX());
//				player.getImageView().setTranslateY(position.getY());
//				player.setxPosition((int) position.getX());
//				player.setyPosition((int) position.getY());
//				i++;
//			}
//			i = 0;
//			for (Bar bar : Bar) {
//				bar.getImageView().setTranslateX(guiReader.getBarX(i));
//				bar.getImageView().setTranslateY(guiReader.getBarY(i));
//				i++;
//			}
//			
//		}
//	
//	public void drawObject(ObjectAbstract object) throws Exception {
//		drawImageView(object.getImageView(), (int) object.getBar().getInitialX(), (int) object.getBar().getInitialY()- guiReader.getObjectLiftDistance());
//	}
//	
//	public void updateLabel(PlayerAbstract player, int score, LinkedHashMap<PlayerAbstract, Label> labels) {
//		String space = " ";
//		for (int i = 0; i < player.getName().length() /2 ; i++ ) {
//			space += " ";
//		}
//		labels.get(player).setText(player.getName() + "\n" + space +  score);
//		
//	}
//
//	public void removeObject(ObjectAbstract object) {
//		pane.getChildren().remove(object.getImageView());
//	}
//
//	private void declareConfigurtionFilesReader() {
//		guiReader = new GuiPropertiesReader();
//		pathReader = new PathsPropertiesReader();
//		defaultSettingsReader = new DefaultSettingsPropertiesReader();
//		
//	}
//	
//
//
//	
//	
//
//}
//
