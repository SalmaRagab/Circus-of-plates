package object.state;

import java.awt.Point;

import object.ObjectAbstract;

public class StartState extends StateAbstract {

	
	
	private ObjectAbstract ball;

	private Point barStartPoint, barEndPoint;

	private Point currentLocation;
	
	
	public StartState(ObjectAbstract objectAbstract) {
		super(objectAbstract);
		ball = objectAbstract;
		barStartPoint = new Point();
		barEndPoint = new Point();
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
			barEndPoint = barStartPoint;
//			barEndPoint.setLocation((barStartPoint.getX() - 5), (barStartPoint.getY()));
			barStartPoint = temp;			
		}

	}
	
	
	@Override
	public Point move() {
		setBarStartAndEndPoint();
		
		currentLocation = new Point();
		currentLocation.setLocation(barStartPoint.getX(), (barStartPoint.getY() - 40));
		
		ball.setState(new MovingState(ball));
		
		return currentLocation;
	}
	


	
}
