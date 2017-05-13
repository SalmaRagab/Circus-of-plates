package observer;

import java.util.Stack;

import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import object.ObjectAbstract;
import object.state.StartState;
import view.MainGui;

public class FloorObserver implements ObserverIF {
	ObjectAbstract object;

	@Override
	public void update(ObjectAbstract object, Object o) {
		this.object = object;
		try {
			if (detect()) {
				object.setState(new StartState(object));
				object.setRemove(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean detect() throws Exception {
		IGuiReader guiReader = new GuiPropertiesReader();
		double floorY = MainGui.getScene().getHeight();
		double objectY = object.getPosition().getY();
		if ((Math.abs(floorY - (objectY + guiReader.getObjectHeight())) < 2) && objectY <= floorY){
		return true;
		}
		return false;
	}
	@Override
	public void setxStart(int x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setxEnd(int i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getDetectedStack() {
		// TODO Auto-generated method stub
	}
	@Override
	public int getxStart() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getxEnd() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Stack<ObjectAbstract> getRightStack() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Stack<ObjectAbstract> getLeftStack() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setLeftStack(Stack<ObjectAbstract> removeLastThree) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRightStack(Stack<ObjectAbstract> removeLastThree) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNoOfRemovedObjects(int noOfRemovedObjects) {
		// TODO Auto-generated method stub
		
	}
}
