package Score;

import java.util.Stack;

import javafx.scene.paint.Color;
import object.ObjectAbstract;
import object.state.CaughtState;
import player.PlayerAbstract;

public class EasyScoreCalculator extends ScoreCalculator {
	
	static EasyScoreCalculator easyScoreCalculator = new EasyScoreCalculator();
	private EasyScoreCalculator() {}

	public static EasyScoreCalculator getEasyScoreCalculator() {
		return easyScoreCalculator;
	}

	private final static int removed = 3;
	
	@Override
	protected boolean increaseScore(Stack<ObjectAbstract> objectsStack) {
		Stack<ObjectAbstract> tempStack = new Stack<>();
		try {
			ObjectAbstract object = objectsStack.pop(); // first object
			tempStack.add(object);
			String lastColor = object.getColor(); 
			object = objectsStack.pop(); // second object
			tempStack.add(object);
			if (lastColor.equals(object.getColor())) {
				object = objectsStack.pop(); // last object
				tempStack.add(object);
				if (lastColor.equals(object.getColor())) {
					ObjectAbstract tempObject;
					while (!tempStack.isEmpty()) {
						tempObject = tempStack.pop();
						tempObject.setRemove(true);
						tempObject.setState(new CaughtState(object));
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
