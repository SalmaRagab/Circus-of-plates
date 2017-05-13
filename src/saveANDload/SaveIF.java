package saveANDload;

import java.nio.file.Path;

import gameMemento.Memento;

public interface SaveIF {
	
	public void saveToFile(String fileName, Memento mementoObject);
	
	
}
