
package object;

import java.awt.Point;
import java.nio.file.Path;
import java.util.ArrayList;

import additionalObjects.Bar;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import javafx.scene.image.ImageView;
import object.state.StartState;
import object.state.StateAbstract;
import observer.ObserverIF;

/**
 *The abstract class of objects.
 *Objects are the different falling shapes.
 *This class contains general properties
 *and methods of all objects.
 *
 */
public abstract class ObjectAbstract {

	private Path imagePath;
	
	private ImageView imageView;
	
	private Bar bar;
	
	private StateAbstract state;
	
	private Point currentLocation;
	private Point position;
	
	private boolean remove;
	private boolean caught;
	
	private ObserverIF currentObserver;
	
	private double distance;
	private String color;
	
	private String detectedStack;
	
	public void setDetectedStack(String detectedStack) {
		this.detectedStack = detectedStack;
	}
	public String getDetectedStack() {
		return detectedStack;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public void setCaught(boolean caught) {
		this.caught = caught;
	}

	public boolean isCaught() {
		return caught;
	}
	
	public ObserverIF getCurrentObserver() {
		return currentObserver;
	}

	
	public Path getImagePath() {
		return imagePath;
	}

	public void setImagePath(Path imagePath) {
		this.imagePath = imagePath;
	}
	
	public Point getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
	}


	public ObjectAbstract(Path imagePath) {

		try {
			IGuiReader guiReader = new GuiPropertiesReader();
			this.imagePath = imagePath;
			ImageView newImageview = new ImageView(imagePath.toString().replace("resources\\", ""));
			newImageview.setFitWidth(guiReader.getObjectWidth());
			newImageview.setFitHeight(guiReader.getObjectHeight());
			this.imageView = newImageview;
			caught = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		state = new StartState(this); //default state
	}
	
	public StateAbstract getState() {
		return state;
	}

	public void setState(StateAbstract state) {
		this.state = state;
	}
	
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public void setBar(Bar bar) {
		this.bar = bar;
		this.currentLocation = new Point();
		this.currentLocation.setLocation((this.bar.getInitialX()), (this.bar.getInitialY() - 40));
//		this.state = new BallStartState(this);
	}
	
	public Bar getBar() {
		return bar;
	}
	
	
	public Point move() throws Exception {
		return null;
	}

	public abstract Point getPosition();

	public abstract void setPosition(Point position);

	public abstract void setObservers(ArrayList<ObserverIF> observers);

	public abstract ArrayList<ObserverIF> getObservers();
	
	public abstract void notifyObservers() throws Exception;

	
	public abstract boolean isRemove();

	public abstract void setRemove(boolean remove);
	
	public abstract void defineStackObserver(ObserverIF stackObserver);


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}
	public abstract void setCurrentObserver(ObserverIF currentObserver);
	
	
}
