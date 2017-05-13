package additionalObjects;

import java.nio.file.Path;

import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.MainGui;
import org.apache.logging.log4j.LogManager;

public class Bar {
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Bar.class);

	private double initialX;
	private double initialY;
	
	private double length;
	
	private Path imagePath;
	
	public Path getImagePath() {
		return imagePath;
	}



	public void setImagePath(Path imagePath) {
		this.imagePath = imagePath;
	}



	private ImageView imageView;
	
	public ImageView getImageView() {
		return imageView;
	}



	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}



	private Image image;
	
	public double getInitialX() {
		return initialX;
	}



	public void setInitialX(double initialX) {
		this.initialX = initialX;
	}



	public double getInitialY() {
		return initialY;
	}



	public void setInitialY(double initialY) {
		this.initialY = initialY;
	}



	public double getLength() {
		return length;
	}



	public void setLength(double length) {
		this.length = length;
	}



	public Bar(double x, double y, double length, Path imgPath) {
		this.initialX = x;
		this.initialY = y;
		this.length = length;
		this.imagePath = imgPath;
		imageView = new ImageView(imgPath.toString().replace("resources\\", ""));
		imageView.setFitWidth(length);
		try {
			imageView.setFitHeight(MainGui.getGuiPropertiesReader().getBarHeight());
		} catch (Exception e) {
			logger.info("Some pro");
		}
	}
	
	
	
}

