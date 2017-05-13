package objects.classes.ball;

import java.awt.Point;
import java.nio.file.Path;
import java.util.ArrayList;

import additionalObjects.Bar;
import javafx.scene.image.ImageView;
import object.ObjectAbstract;
import object.state.MovingState;
import object.state.StartState;
import object.state.StateAbstract;
import observer.ObserverIF;

public class Ball extends ObjectAbstract {

	private Path imagePath;
	
	private ImageView imageView;
	
	private Bar bar;
	private boolean remove;
	private StateAbstract state;
	
	private Point currentLocation;
	private Point position;
	private Point newLocation;
	
	private ArrayList<ObserverIF> observers;
	private ObserverIF currentObserver;

	private double distance;

	private String detectedStack;
	
	@Override
	public void setDetectedStack(String detectedStack) {
		this.detectedStack = detectedStack;
	}
	
	@Override
	public String getDetectedStack() {
		return detectedStack;
	}
	
	public ObserverIF getCurrentObserver() {
		return currentObserver;
	}


	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		super.setBar(bar);
		this.bar = bar;
		currentLocation.setLocation(bar.getInitialX(), (bar.getInitialY() - 40));
		this.state = new StartState(this);

	}
	
/*	 public Ball(Path imagePath) {
		super(imagePath);
		currentLocation = new Point();
		guiReader = new GuiPropertiesReader();
		defaultSettingsReader = new DefaultSettingsPropertiesReader();
				
		try {
			this.imagePath = imagePath;
			ImageView newImageview = new ImageView(imagePath.toString().replace("resources\\", ""));
			newImageview.setFitWidth(guiReader.getObjectWidth());
			newImageview.setFitHeight(guiReader.getObjectHeight());
			this.imageView = newImageview;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		state = new BallStartState(this); //default state
	}*/
	 

	public Ball(Path imagePath) {
		super(imagePath);
		state = new MovingState(this); //default
		currentLocation = new Point();
		this.imagePath = imagePath;
		
	}

	@Override
	public Point move() throws Exception {
		
		newLocation = state.move();
		
		return newLocation;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
		
	}
	
	@Override
	public void setState(StateAbstract state) {
		super.setState(state);
		this.state = state;
	}
	@Override
	public ArrayList<ObserverIF> getObservers() {
		return observers;
	}
	@Override
	public void setObservers(ArrayList<ObserverIF> observers) {
		this.observers = observers;
	}

	@Override
	public void notifyObservers() throws Exception {
		for (ObserverIF observer : observers) {
			this.currentObserver = observer;
			observer.update(this, null);
		}
		
	}

	@Override
	public boolean isRemove() {
		return remove;
	}

	@Override
	public void setRemove(boolean remove) {
		this.remove = remove;
		
	}

	@Override
	public void defineStackObserver(ObserverIF stackObserver) {
		this.currentObserver = stackObserver;
		switch (detectedStack) {
		case "Left":
			distance = (currentObserver.getxStart()) - (position.x);
			break;
		case "Right":
			distance = (currentObserver.getxEnd()) - (position.x);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void setCurrentObserver(ObserverIF currentObserver) {
		this.currentObserver = currentObserver;
		
	}
	
}
