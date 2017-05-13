package object.state;

import java.awt.Point;
import java.util.Random;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import object.ObjectAbstract;
import object.state.MovingState;
import object.state.StateAbstract;
import objects.classes.ball.Ball;
import view.MainGui;
import org.apache.logging.log4j.LogManager;

public class MovingState extends StateAbstract {

	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MovingState.class);

	private ObjectAbstract ball;
	
	private Point currentLocation;
	
	private Point barPoint;

	private Point newLocation;
	
	Random random;
	
	private Point barStartPoint, barEndPoint;
	
	private IDefaultSettingsReader defaultReader;
	
	private boolean fly;
	
	public MovingState(ObjectAbstract objectAbstract) {
		super(objectAbstract);
		ball = objectAbstract;
		barPoint = new Point();
		newLocation = new Point();
		barStartPoint = new Point();
		barEndPoint = new Point();
		defaultReader = new DefaultSettingsPropertiesReader();
		fly = false;
	}

	

	private void setBarParameters() {
		barPoint.setLocation(ball.getBar().getInitialX(), ball.getBar().getInitialY());
		currentLocation = new Point();
		currentLocation.setLocation(barPoint.getX(), (barPoint.getY() - 40));
	}
	
	
	private void setCurrentLocation() {
		if ((currentLocation.getX() + ball.getBar().getLength()) >= 1000) {
			//therefore it's one of the bars on the right
			currentLocation.setLocation((currentLocation.getX() + ball.getBar().getLength() - 100),
					barPoint.getY() - 40);
		}
	}
	
	private void setBarStartAndEndPoint() {
		barStartPoint.setLocation(ball.getBar().getInitialX(), ball.getBar().getInitialY());
		if ((barStartPoint.getX() == 0)) { //ana f wa7ed mn elly 3la el shmaal

			barEndPoint.setLocation((barStartPoint.getX() + (ball.getBar().getLength())),
					ball.getBar().getInitialY());
		} else { //f wa7ed mn elly 3la el ymeen

			barEndPoint.setLocation((barStartPoint.getX() + (ball.getBar().getLength())),
					ball.getBar().getInitialY());
			Point temp = barEndPoint;
//			barEndPoint = barStartPoint;
			barEndPoint = new Point();
			barEndPoint.setLocation((barStartPoint.getX() - 50), (barStartPoint.getY()));
			barStartPoint = temp;			
		}

	}
	
	@Override
	public Point move() throws Exception {

		setBarStartAndEndPoint();
		
		if ((currentLocation == null) ||
				(currentLocation.getX() == 0) && (currentLocation.getY() == 0)) { //lesa bbd2
			
			currentLocation = new Point();
			currentLocation.setLocation(barStartPoint.getX(), (barStartPoint.getY() - 40));
		}
		
		if (((currentLocation.getX() + (defaultReader.getObjectSpeed())) > barStartPoint.getX())
				&& ((currentLocation.getX() + (defaultReader.getObjectSpeed())) < barEndPoint.getX())
				&& !fly) {
			
			moveHorizontalRight();
		} else if (((currentLocation.getX() - (defaultReader.getObjectSpeed())) < barStartPoint.getX())
				&& ((currentLocation.getX() - (defaultReader.getObjectSpeed())) > barEndPoint.getX())
				&& !fly) {
			
			moveHorizontalLeft();
		} else if (((currentLocation.getX() + (defaultReader.getObjectSpeed())) >= barEndPoint.getX())
				&& ((currentLocation.getX() + (defaultReader.getObjectSpeed()) > barStartPoint.getX()))
				&& !fly) {
			flyRight();
			fly = true;
		} else if (((currentLocation.getX() - 100) <= barEndPoint.getX())
				&& ((currentLocation.getX() - (defaultReader.getObjectSpeed())) < barStartPoint.getX())
				&& !fly) {
			flyLeft();
			fly = true;
		} else if ((currentLocation.getY() != (barStartPoint.getY() - 40)) && fly){ //continue flying
			newLocation.setLocation(currentLocation.getX(), (currentLocation.getY() + 3));
		}
	
		currentLocation = newLocation;
		return newLocation;
	}

	private void moveHorizontalRight() throws Exception {
//		newLocation.setLocation((currentLocation.getX() + 5), currentLocation.getY());
		newLocation.setLocation((currentLocation.getX() + (defaultReader.getObjectSpeed())),
				currentLocation.getY());
		logger.info("Ball " + ball.getColor() + " is moving right!");
//		logger.info("Ball is moving right!");
	}
	
	private void moveHorizontalLeft() throws Exception {
//		newLocation.setLocation((currentLocation.getX() - 5), currentLocation.getY());
		int newX = (int) (currentLocation.getX() - defaultReader.getObjectSpeed());
//		newLocation.setLocation((currentLocation.getX() - (defaultReader.getObjectSpeed())),
//				currentLocation.getY());
		newLocation.setLocation(newX, currentLocation.getY());
	}
	
	private void flyRight() throws Exception {
//		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		random = new Random();
		double randomValueX = (barEndPoint.getX() + 50) +
				((barEndPoint.getX() + (defaultReader.getObjectSpeed())) - (barEndPoint.getX() + 50))
				* random.nextDouble();
		double randomValueY = (barEndPoint.getY() + 2) +
				(barEndPoint.getY() - (barEndPoint.getY() + 2)) * random.nextDouble();
		newLocation.setLocation(randomValueX, randomValueY);
	}
	
	private void flyLeft() {
//		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		random = new Random();
		double randomValueX = (barEndPoint.getX() - 20) +
				((barEndPoint.getX() - 10) - (barEndPoint.getX() - 20)) * random.nextDouble();
		double randomValueY = (barEndPoint.getY() + 2) +
				(barEndPoint.getY()+20 - (barEndPoint.getY() + 2)) * random.nextDouble();
		
		
		newLocation.setLocation(randomValueX, randomValueY);
	}


}

