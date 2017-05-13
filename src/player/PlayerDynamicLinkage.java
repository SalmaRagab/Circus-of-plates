package player;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;

public class PlayerDynamicLinkage {
	

	IPathsReader pathReader;
	/**
	 * players classes folder path
	 */
	Path classesPath;
	
	File classesDir;
	
	public PlayerDynamicLinkage() throws Exception {
		pathReader = new PathsPropertiesReader();
		classesPath = pathReader.getPlayerClassesPath();
		classesDir = new File(classesPath.toString());
	}
	
	public Class loadClass(String className) throws Exception {
		
		
		URLClassLoader loader = new URLClassLoader(new URL[] {classesDir.toURL()});
		Class desiredClass = loader.loadClass(classesPath.toString().replace('\\', '.')+'.'+className);
		return desiredClass;
		
		
		
	}
	
	

}
