package object.state;

import java.awt.Point;

import object.ObjectAbstract;

public abstract class StateAbstract {
	
	private ObjectAbstract object;
	
	Point barStartPoint, barEndPoint;
	
	
	public StateAbstract(ObjectAbstract objectAbstract) {
		object = objectAbstract;
		barStartPoint = new Point();
		barEndPoint = new Point();
	}
	
	public abstract Point move() throws Exception;
	
	/*public void setBarStartAndEndPoint() {
		barStartPoint.setLocation(object.getBar().getInitialX(), object.getBar().getInitialY());
		if ((barStartPoint.getX() == 0)) { //ana f wa7ed mn elly 3la el shmaal

			barEndPoint.setLocation((barStartPoint.getX() + (object.getBar().getLength())),
					object.getBar().getInitialY());
		} else { //f wa7ed mn elly 3la el ymeen

			barEndPoint.setLocation((barStartPoint.getX() + (object.getBar().getLength())),
					object.getBar().getInitialY());
			Point temp = barEndPoint;
			barEndPoint = barStartPoint;
			barStartPoint = temp;			
		}

	}*/
}
