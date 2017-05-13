package saveANDload.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import gameMemento.Memento;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import object.ObjectAbstract;
import player.PlayerAbstract;
import saveANDload.SaveIF;
import view.MainGui;

public class SaveXML implements SaveIF {


	
	String path;
	String playersListPath;
	String objectsListPath;
	
	public SaveXML() {
//		path = "resources\\files\\";
		try {
			path = MainGui.getPathsReader().getSavePath();
		} catch (Exception e) {
		}
	}
	
	@Override
	public void saveToFile(String fileName, Memento mementoObject) {
		ArrayList<LinkedHashMap<String,Object>> playersList;
		ArrayList<LinkedHashMap<String,Object>> objectsList;
		if (new File(path + fileName).mkdir()) {
			System.out.println("yes");
		}
		
		String playersListPath = path + (fileName + "/Players.XML"); //fileName.Players.XML
		String objectsListPath = path + (fileName + "/Objects.XML"); //fileName.Objects.XML
		
		playersList = mementoObject.getPlayerArrayListHash();
		objectsList = mementoObject.getObjectsArrayListHash();
		
		
		XStream playersXstream = new XStream(new DomDriver());
		String playersXML = playersXstream.toXML(playersList);
		
		XStream objectsXstream = new XStream(new DomDriver());
		String objectsXML = objectsXstream.toXML(objectsList);
		
		
		
		try {
			writePlayers(playersListPath, playersXML);
			writeObjects(objectsListPath, objectsXML);
		} catch (Exception e) {
			
		}
	}


	private void writePlayers(String path, String xml) throws IOException {
		FileWriter file = new FileWriter(path);
		file.write("<?xml version=\"1.1\"?>\n");
		file.write(xml);
		file.flush();
		file.close();
	}

	private void writeObjects(String path, String xml) throws IOException {
		FileWriter file = new FileWriter(path);
		file.write("<?xml version=\"1.1\"?>\n");
		file.write(xml);
		file.flush();
		file.close();
	}

}
