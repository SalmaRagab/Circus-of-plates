package object.state;

import java.awt.Point;

import object.ObjectAbstract;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import object.ObjectAbstract;
import object.state.CaughtState;
import object.state.StateAbstract;
import observer.ObserverIF;

public class CaughtState extends StateAbstract {
	
	private IDefaultSettingsReader dsReader;
	private ObjectAbstract ball;
	private ObserverIF stackObserver;
	
	private Point currentLocation;
	private Point newLocation;
	
	private List<ObjectAbstract> objectsInLeftStack;
	private List<ObjectAbstract> objectsInRightStack;
		
	public CaughtState(ObjectAbstract objectAbstract) {
		super(objectAbstract);
		dsReader = new DefaultSettingsPropertiesReader();
		this.ball = objectAbstract;
		this.stackObserver = ball.getCurrentObserver();
		this.currentLocation = ball.getCurrentLocation();
		newLocation = new Point();
		objectsInLeftStack = new ArrayList<>();
		objectsInRightStack = new ArrayList<>();
	}

	@Override
	public Point move() throws Exception {
		String stack = ball.getDetectedStack();
//		newLocation.setLocation(currentLocation);
		
		switch (stack) {
		case "Left":
			moveWithLeftStack();
			return newLocation;
		case "Right":
			moveWithRightStack();
			break;
		default:
			break;
		}
		
		return newLocation;
	}
	
	private void moveWithLeftStack() throws Exception {
		double distance = ball.getDistance();
//		int objectXPosition = ball.getPosition().x;
		if (distance > 0) {
			newLocation.setLocation(stackObserver.getxStart() - dsReader.getDistanceFromLeftStack()
					, ball.getPosition().getY());
		} else if (distance < 0) {
			newLocation.setLocation(stackObserver.getxStart() + dsReader.getDistanceFromLeftStack()
					, ball.getPosition().getY());
		} else if (distance == 0) {
			newLocation.setLocation(stackObserver.getxStart(), ball.getPosition().getY());
		}
		
	}

	private void moveWithRightStack() throws Exception {
		double distace = ball.getDistance();
		if (distace > 0) {
//			newLocation.setLocation((ball.getPosition().getX() + 100), ball.getPosition().getY());
			newLocation.setLocation((stackObserver.getxEnd() - 150), ball.getPosition().getY());
		} else if (distace < 0) {
			newLocation.setLocation((stackObserver.getxEnd() - dsReader.getDistanceFromRightStack())
					, ball.getPosition().getY());
		} else if (distace == 0) { //byd5ol hna bsss
			newLocation.setLocation((stackObserver.getxEnd() - dsReader.getDistanceFromRightStack())
			, ball.getPosition().getY());
		}
		
	}
}
