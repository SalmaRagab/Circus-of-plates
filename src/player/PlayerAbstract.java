package player;

import java.awt.Point;
import java.nio.file.Path;

import controller.GameController;
import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import observer.ObserverIF;

/**
 *This class is the clown object, It contains all the
 *properties and the methods of any clown.
 *
 */
public abstract class PlayerAbstract {
	
	private String name;
	private Path imgPath;
	private int score;
	private int playerSpeed;
	private boolean moveLeft;
	private boolean moveright;
	private ImageView imageView;
	private int xPosition;
	private int yPosition;
	private ObserverIF stackObserver;

	
	
	public abstract void setStackObserver() throws Exception;
	
	public abstract ObserverIF getStackObserver();
	
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public abstract ImageView getImageView();
	public abstract void setImageView(ImageView imageView);
	
	public boolean isMoveLeft() {
		return moveLeft;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public boolean isMoveright() {
		return moveright;
	}
	public void setMoveright(boolean moveright) {
		this.moveright = moveright;
	}
	public int getPlayerSpeed() {
		return playerSpeed;
	}
	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Path getImgPath() {
		return imgPath;
	}
	public void setImgPath(Path imgPath) {
		this.imgPath = imgPath;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	public PlayerAbstract(Path img, String name) {
		IGuiReader guiReader = new GuiPropertiesReader();
		this.imgPath = img;
		this.name = name;
		this.score = 0;
		moveLeft = false;
		moveright = false;
		imageView = new ImageView(img.toString().replace("resources\\", ""));
		

		try {
			this.playerSpeed = getSpeed();
			imageView.setFitWidth(guiReader.getPlayerWidth());
			imageView.setFitHeight(guiReader.getPlayerHeight());
			setImageView(imageView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Point moveBy() throws Exception {
		return null;
		
	}
	
	private int getSpeed() throws Exception {
		IDefaultSettingsReader reader = new DefaultSettingsPropertiesReader();
		return reader.getPlayerSpeed();
	}
	
	
}
