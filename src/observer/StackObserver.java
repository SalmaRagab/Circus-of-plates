package observer;

import java.util.Stack;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import object.ObjectAbstract;
import object.state.CaughtState;

public class StackObserver implements ObserverIF {
	
	
	private int xStart;
	private int xEnd;
	private int leftStackHeight;
	private int rightStackHeight;
	
	static private double acceptRange;
	static private double adjustStart;
	static private int objectHeight;
	
	private ObjectAbstract object;
	private IGuiReader guiReader;
	private IDefaultSettingsReader defaultSettingsReader;
	
	private boolean detectLeftStack;
	private boolean detectRightStack;
	private Stack<ObjectAbstract> leftStack;
	private Stack<ObjectAbstract> rightStack;
	
	private int noOfRemovedObjects;
	

	public Stack<ObjectAbstract> getRightStack() {
		return rightStack;
	}


	public void setRightStack(Stack<ObjectAbstract> rightStack) {
		this.rightStack = rightStack;
		this.rightStackHeight = rightStackHeight + noOfRemovedObjects*objectHeight;
	}
	public Stack<ObjectAbstract> getLeftStack() {
		return leftStack;
	}


	public void setLeftStack(Stack<ObjectAbstract> leftStack) {
		this.leftStack = leftStack;
		this.leftStackHeight = leftStackHeight + noOfRemovedObjects*objectHeight;

	}
	public StackObserver () {
		
	}
	
	
	public StackObserver(int startX, int endX, int Y) {
		this.xStart = startX;
		this.setxEnd(endX);
		this.leftStackHeight = Y;
		this.rightStackHeight = Y;
		guiReader = new GuiPropertiesReader();
		defaultSettingsReader = new DefaultSettingsPropertiesReader();
		detectLeftStack = false;
		detectRightStack = false;
		leftStack = new Stack<>();
		rightStack = new Stack<>();
		try {
			acceptRange = defaultSettingsReader.getAcceptancePercentage();
			adjustStart = guiReader.adjustStart();
			objectHeight = guiReader.getObjectHeight();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(ObjectAbstract object, Object o) {
		this.object = object;
		
		if (!(object.isCaught())) { //if the object wasn't caught
			if (detect()) {
				object.setState(new CaughtState(object));
				object.setCaught(true);
				getDetectedStack();
				object.defineStackObserver(this);
			}
		}
	}
	

	@Override
	public boolean detect() {
			double objectStartX = object.getPosition().getX() + adjustStart;
			double objectY = object.getPosition().getY();
	//		check if it is on the same left level
			if (Math.abs(leftStackHeight - (objectHeight + objectY )) <= 1) {
				if (Math.abs(xStart - objectStartX) <= acceptRange) {
//					on the stack's left
					this.leftStackHeight = leftStackHeight - objectHeight;
					detectLeftStack = true;
					detectRightStack = false;
					leftStack.add(object);
					return true;
				} 
				//		check if it is on the same right level
			}
			if (Math.abs(rightStackHeight - (objectHeight + objectY )) <= 1) {
				if (xEnd - objectStartX <= 3 * acceptRange && (xEnd - objectStartX >= 0.5 * acceptRange)) {
//					on stack's right hand
					this.rightStackHeight = rightStackHeight - objectHeight;
					detectRightStack = true;
					detectLeftStack = false;
					rightStack.add(object);
					return true;
				}
			}
		return false;
	}
	
	
	

	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}




	public int getxEnd() {
		return xEnd;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}


	@Override
	public void getDetectedStack() {
		if (detectLeftStack) {
			object.setDetectedStack("Left");
		}
		if (detectRightStack) {
			object.setDetectedStack("Right");
		}
	}


	public int getNoOfRemovedObjects() {
		return noOfRemovedObjects;
	}


	public void setNoOfRemovedObjects(int noOfRemovedObjects) {
		this.noOfRemovedObjects = noOfRemovedObjects;
	}




}
