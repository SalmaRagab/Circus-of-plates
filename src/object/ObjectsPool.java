package object;

import java.util.ArrayList;
import java.util.List;

import gameConfigurationsReader.IDefaultSettingsReader;
import gameConfigurationsReader.configurationsReaders.DefaultSettingsPropertiesReader;
import iterator.Iterator;

public class ObjectsPool implements Iterator{
	
	private ObjectsPool(){}
	private int containerSize;
	
	static ObjectsPool objectsPool = new ObjectsPool();
	static ArrayList<ObjectAbstract> objectsContainer = new ArrayList<ObjectAbstract>();
	
	public static ObjectsPool getObjectPool() {
		return objectsPool;
	}
	
	static  {
		IDefaultSettingsReader defaultSettingsReader = new DefaultSettingsPropertiesReader();
		try {
			objectsPool.setContainerSize(defaultSettingsReader.getContainerSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObjectsPool getObjectsPool () {
		return objectsPool;
	}
	
	
	private void setContainerSize(int size) {
		this.containerSize = size;
	}
	
	
	
	
	
	
	
	
	

	@Override
	public boolean isEmpty() {
		return objectsContainer.isEmpty();
	}

	@Override
	public int getSize() {
		return objectsContainer.size();
	}

	@Override
	public java.lang.Object remove(int index) {
		return objectsContainer.remove(index);
	}

	@Override
	public java.lang.Object removeFirst() {
		return objectsContainer.remove(0);
	}

	@Override
	public java.lang.Object removeLast() {
		return objectsContainer.remove(objectsContainer.size()-1);

	}

	@Override
	public void addFirst(java.lang.Object object) {
		if (objectsContainer.size() < containerSize) {
			objectsContainer.add(0, (ObjectAbstract) object);
		}
		
	}

	@Override
	public void addLast(java.lang.Object object) {
		if (objectsContainer.size() < containerSize) {
			objectsContainer.add((ObjectAbstract) object);
		}
		
	}

	@Override
	public void add(java.lang.Object object, int index) {
		if (objectsContainer.size() < containerSize) {
			objectsContainer.add(index, (ObjectAbstract) object);
		}
		
	}

}
