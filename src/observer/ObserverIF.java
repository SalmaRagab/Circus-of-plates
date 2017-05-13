package observer;

import java.util.Stack;

import object.ObjectAbstract;

public interface ObserverIF {
	
	public void update (ObjectAbstract object, Object o);
	
	public boolean detect() throws Exception;

	public void setxStart(int x);

	public void setxEnd(int i);
	
	public int getxStart();
	
	public int getxEnd();
	
	public void getDetectedStack();

	public Stack<ObjectAbstract> getRightStack();

	public Stack<ObjectAbstract> getLeftStack();
	
	public void setLeftStack(Stack<ObjectAbstract> removeLastThree);

	public void setRightStack(Stack<ObjectAbstract> removeLastThree);
	
	public void setNoOfRemovedObjects(int noOfRemovedObjects);

}
