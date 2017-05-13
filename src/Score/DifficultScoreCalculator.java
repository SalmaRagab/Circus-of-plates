package Score;

import java.util.Stack;

import object.ObjectAbstract;
import object.state.CaughtState;

public class DifficultScoreCalculator extends ScoreCalculator {
	
	static DifficultScoreCalculator difficultScoreCalculator = new DifficultScoreCalculator();
	private DifficultScoreCalculator() {}

	public static DifficultScoreCalculator getDifficultScoreCalculator() {
		return difficultScoreCalculator;
	}

	private final static int removed = 4;
	@Override
	protected boolean increaseScore(Stack<ObjectAbstract> objectsStack) {
//		both color and shape shoud be the same for 4 times
		Stack<ObjectAbstract> tempStack = new Stack<>();
		try {
			ObjectAbstract object = objectsStack.pop(); // first object
			tempStack.add(object);
			String lastColor = object.getColor(); 
			String lastClass = object.getClass().toString();
			object = objectsStack.pop(); // second object
			tempStack.add(object);
			if (lastColor.equals(object.getColor()) && lastClass.equals(object.getClass().toString())) {
				object = objectsStack.pop(); // third object
				tempStack.add(object);
				if (lastColor.equals(object.getColor()) && lastClass.equals(object.getClass().toString())) {
					object = objectsStack.pop(); // last object
					tempStack.add(object);
					if (lastColor.equals(object.getColor()) && lastClass.equals(object.getClass().toString())) {
						ObjectAbstract tempObject;
						tempStack.add(object);
						while (!tempStack.isEmpty()) {
							tempObject = tempStack.pop();
							tempObject.setRemove(true);
							tempObject.setState(new CaughtState(object));
						}
					}
					return true;
				}
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
		} 
		
		while (!tempStack.isEmpty()) {
			objectsStack.add(tempStack.pop());
		}
		return false;
	
	}

	@Override
	protected int getNoOfRemovedObjects() {
		return removed;
	}
}
