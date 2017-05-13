package object;

import gameConfigurationsReader.IPathsReader;
import gameConfigurationsReader.configurationsReaders.PathsPropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 *Class to load objects from objects folder
 *using configuration readers
 *
 */
public class ObjectsDynamicLinkage {
	
	/**
	 * config reader file
	 */
	private IPathsReader pathReader;
	
	private Path classesPath;
	
	private File classesDirectory;

	static ObjectsDynamicLinkage objDynamicLinkage = new ObjectsDynamicLinkage();
//	private ObjectsDynamicLinkage objDynamicLinkage;
	
	static {
		try {
			objDynamicLinkage.pathReader = new PathsPropertiesReader();
			objDynamicLinkage.classesPath = objDynamicLinkage.pathReader.getObjectsClassesPath();
			objDynamicLinkage.classesDirectory = new File(objDynamicLinkage.classesPath.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	static public ObjectsDynamicLinkage getObjDynamicLinkage() throws Exception {
		return objDynamicLinkage;
	}
	
	private ObjectsDynamicLinkage() {
		
	}

	public Class loadClass(String className) throws Exception {
		if (!classesDirectory.toString().contains("resources\\")) {
			classesDirectory = new File("resources\\" + classesDirectory);
			classesPath = classesDirectory.toPath();
		}
		setPaths(classesDirectory.listFiles(), className, classesPath);
		URLClassLoader loader = new URLClassLoader(new URL[] {classesDirectory.toURL()});
		Class desiredClass = loader.loadClass(classesPath.toString().
				replace('\\', '.')+'.'+className);
		return desiredClass;
	}
	
	private void setPaths(File[] folder, String className, Path classPath) throws Exception {
		for (File file : folder) {
			if (file.isDirectory()) {
				setPaths(file.listFiles(), className, classPath);
			} else {
				classPath = file.toPath();
				if (classPath.getFileName().toString().equals(className + ".java")) {
					Path tempClassPath = Paths.get(classPath.toString().substring(10));
					classesDirectory = new File(tempClassPath.toString().substring(0, tempClassPath.toString().lastIndexOf('\\')));
					classesPath = classesDirectory.toPath();
				}
							
			}
		}
	}
	
}
