package saveANDload.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import gameMemento.Memento;
import object.ObjectAbstract;
import player.PlayerAbstract;
import saveANDload.LoadIF;
import view.MainGui;

public class LoadXML implements LoadIF {
	
	private Memento memento;
	
	private String folderPath;
	
	public LoadXML() {
		memento = memento.getMemento();
//		folderPath = "resources\\files\\";
		try {
			folderPath = MainGui.getPathsReader().getSavePath();
		} catch (Exception e) {
		}
	}
	
	@Override
	public Memento loadFile(String fileName) {
		
		String playersListPath = folderPath + (fileName + "/Players.XML"); //fileName.Players.XML
		String objectsListPath = folderPath + (fileName + "/Objects.XML"); //fileName.Objects.XML
		ArrayList<LinkedHashMap<String, Object>> playersList = new ArrayList<>();
		ArrayList<LinkedHashMap<String, Object>> objectsList = new ArrayList<>();
		
		try {
			playersList = readPlayers(playersListPath);
			objectsList = readObjects(objectsListPath);
			memento.setMementoInverter(playersList, objectsList);
			
			return memento;
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

	private ArrayList<LinkedHashMap<String, Object>> readPlayers(String filePath) {
		ArrayList<LinkedHashMap<String,Object>> playersList;
		Object read = new ArrayList<LinkedHashMap<String,Object>>();
		try {
			
			XStream xstream = new XStream(new DomDriver());
			xstream.processAnnotations(ArrayList.class);
			read = xstream.fromXML(new java.io.File(filePath));
			playersList = (ArrayList<LinkedHashMap<String, Object>>) read;
			return playersList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private ArrayList<LinkedHashMap<String, Object>> readObjects(String filePath) {
		ArrayList<LinkedHashMap<String, Object>> objects;
		Object read = new ArrayList<>();
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.processAnnotations(ArrayList.class);
			read = xstream.fromXML(new java.io.File(filePath));
			objects = (ArrayList<LinkedHashMap<String, Object>>) read;
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
