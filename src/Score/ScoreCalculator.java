package Score;

import java.util.Stack;

import object.ObjectAbstract;
import player.PlayerAbstract;

public abstract class ScoreCalculator {
	
	private void ScoreCalculator(){}
	
	public static ScoreCalculator getScoreCalculator(String level) {
		if (level == null) {
			return EasyScoreCalculator.getEasyScoreCalculator();

		}
		switch (level) {
		case "Easy":
			return EasyScoreCalculator.getEasyScoreCalculator();
		case "Medium":
			return MediumScoreCalculator.getMediumScoreCalculator();
		case "Difficult":
			return DifficultScoreCalculator.getDifficultScoreCalculator();
		default:
			return EasyScoreCalculator.getEasyScoreCalculator();
		}
	
	}
	

	public int calculateScore(PlayerAbstract player) {
		int score = player.getScore();
		Stack<ObjectAbstract> objectsLeftStack = player.getStackObserver().getLeftStack();
		Stack<ObjectAbstract> objectsRightStack = player.getStackObserver().getRightStack();
		if (increaseScore (objectsLeftStack)) {
			score++;
			player.setScore(score);
			player.getStackObserver().setNoOfRemovedObjects(getNoOfRemovedObjects());
			player.getStackObserver().setLeftStack(objectsLeftStack);
		}
		if (increaseScore (objectsRightStack)) {
			score++;
			player.setScore(score);
			player.getStackObserver().setNoOfRemovedObjects(getNoOfRemovedObjects());
			player.getStackObserver().setRightStack(objectsRightStack);
		}
		
		
		
		return score;
	}
	
	protected abstract boolean increaseScore(Stack<ObjectAbstract> objectsStack);
	
	protected abstract int getNoOfRemovedObjects();
	
	
}
	
	
	
	

