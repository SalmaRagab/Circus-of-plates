package player;

import java.nio.file.Path;

public class PlayerFactory {
	
	PlayerDynamicLinkage dynamicLinkage;
	
	
	public PlayerFactory() throws Exception {
		dynamicLinkage = new PlayerDynamicLinkage();
	}
	
	public PlayerAbstract createPlayer(Path imgPath, String playerName) throws Exception {
//		extract class name from img path
		String className = ExtractClassName(imgPath.toString());
		Class playerClass = dynamicLinkage.loadClass(className);
		PlayerAbstract player = (PlayerAbstract) playerClass.getDeclaredConstructor(
								Path.class, String.class).newInstance(imgPath, playerName);
		
		return player;
	}
	
	private String ExtractClassName(String path) {
		String className = path.substring(0, path.lastIndexOf('\\'));
		className = className.substring(className.lastIndexOf('\\')+1, className.length());
		
		return className;
		
		
	}

}
