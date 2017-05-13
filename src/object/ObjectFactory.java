package object;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import player.PlayerAbstract;

/**
 *The object factory is used to create different falling objects.
 *The choice of the created object is NOT based on the user's choice
 *It is based on random creation (ObjectsPool is the class responsible for creating).
 *
 */
public class ObjectFactory {
		
	private String className;
	private String color;
	private String imageName;
	
	private Class loadedClass;
	
	static  ObjectFactory objectFactory = new ObjectFactory();
	
	private HashMap<String, Class<ObjectAbstract>> classesLoaded;
	
	private ObjectsDynamicLinkage dynamicLinkage;

	static public ObjectFactory getObjectFactory() {
		return objectFactory;
	}
	
	private void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}
	
	public HashMap<String, Class<ObjectAbstract>> getClassesLoaded() {
		return classesLoaded;
	}
	
	private ObjectFactory() {
		
	}
	
	/**
	 * The method is used to create an object,
	 * An index of the desired object is sent(from object pool 
	 * based on random choice).
	 * @param index The index of the desired object to create
	 * @return An object (of the desired index)
	 * @throws Exception
	 */
	public ObjectAbstract createObject(Path imagePath) throws Exception {
		
		dynamicLinkage = dynamicLinkage.getObjDynamicLinkage();
		classesLoaded = new HashMap<>();
		
		setImageName(imagePath.toString());
		
		setClassName(imagePath.toString());

		setObjectColor(imagePath.toString());
		
		ObjectAbstract object;
		
		
		if (!classesLoaded.containsKey(className)) { //if it doesn't contain the class wanted
			loadedClass = dynamicLinkage.loadClass(className);
			classesLoaded.put(className, loadedClass);
			object = (ObjectAbstract) loadedClass.getDeclaredConstructor(
									Path.class).newInstance(imagePath);
		} else { //it contains the class
			object = (ObjectAbstract) classesLoaded.get(className).
					getDeclaredConstructor(Path.class).newInstance(imagePath);
		}
		object.setColor(color);

		return object;
	}
	
	private void setImageName(String path) {
		imageName = path.substring(path.lastIndexOf('\\') + 1);
	}
	
	private void setClassName(String path) {
		String newPath = path.substring(path.indexOf("images") + 7, path.indexOf(imageName) - 1);
		className = newPath.substring(0, newPath.lastIndexOf('\\'));
	}
	
	private void setObjectColor(String path) {
		String newPath = path.substring(path.indexOf("images") + 7, path.indexOf(imageName) - 1);
		color = newPath.substring(newPath.lastIndexOf('\\') + 1);
	}

}
