package object;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
/**
 *Object pool handler is used to create a stock of objects
 *(using dynamic loading).
 *This class first sets a list of available images in the
 *objects folder and send it to objects factory.
 *The pool handler then starts to fill the stock 
 *by generating random number and sending corresponding image path to factory.
 *The class keep looping till filling the stock of the object pool.
 */
public class ObjectPoolHandler {
	
	private ObjectsPool objectsPool;
	private ObjectFactory objectsFactory;
	private ArrayList<Path> imagesPathsList;
	private int noOfObjects;
	
	public int getNoOfObjects() throws Exception {
		IDefaultSettingsReader defaultSettingsReader = new DefaultSettingsPropertiesReader();
		return defaultSettingsReader.getContainerSize();
	}

	private ObjectPoolHandler(){}
	
	static ObjectPoolHandler objectPoolHandler = new ObjectPoolHandler();
	
	static {
		objectPoolHandler.objectsFactory = ObjectFactory.getObjectFactory();
		objectPoolHandler.objectsPool = ObjectsPool.getObjectPool();
	}
	
	public static ObjectPoolHandler getObjectPoolHandler () throws Exception {
		return objectPoolHandler;
	}
	
	/**
	 * fill the object pool randomly for the first time
	 * @throws Exception 
	 */
	public ObjectsPool fillObjectsPool(ArrayList<Path> imagesPathsList) throws Exception {
		objectPoolHandler.imagesPathsList = imagesPathsList;
		for (int i = 0; i < objectPoolHandler.getNoOfObjects(); i++) {
			int randomNumber = (int)(Math.random() * objectPoolHandler.imagesPathsList.size());
			objectPoolHandler.objectsPool.addLast(objectPoolHandler.objectsFactory.createObject(objectPoolHandler.imagesPathsList.get(randomNumber)));
			
		}
		return objectsPool;
	}
	
	
	public ObjectAbstract getObject() {
		try {
			int randomNumber = (int)(Math.random() * objectPoolHandler.imagesPathsList.size());
			return (ObjectAbstract) objectsPool.remove(randomNumber);
		} catch (Exception e) {
			return null;
		}

		
	}
	
	public void returnObject (ObjectAbstract returnedObject) {
		int randomNumber = (int)(Math.random() * objectPoolHandler.noOfObjects);
		objectsPool.add(returnedObject, randomNumber);
	}
	
	
	
	
	

}
