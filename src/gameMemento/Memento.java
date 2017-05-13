package gameMemento;

import java.awt.Point;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import additionalObjects.Bar;
import controller.GameController;
import object.ObjectAbstract;
import object.ObjectFactory;
import object.state.StateAbstract;
import observer.ObserverIF;
import player.PlayerAbstract;
import player.PlayerFactory;

public class Memento {
	
	private static Memento memento = new Memento();
	final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Memento.class);

	private ArrayList<LinkedHashMap<String, Object>> playerArrayListHash; // for saving
	private ArrayList<LinkedHashMap<String, Object>> objectsArrayListHash; // for saving
	
	private ArrayList<PlayerAbstract> playerArrayList; // from loading
	private ArrayList<ObjectAbstract> objectArrayList; // from loading
	
	private Memento() { }
	
	public static Memento getMemento() {
		return memento;
	}
	
	public void setMementoConverter(ArrayList<PlayerAbstract> playersList, ArrayList<ObjectAbstract> objectsList)  {
		playerArrayListHash = new ArrayList<>();
		objectsArrayListHash = new ArrayList<>();
		
		for (int i = 0; i < playersList.size(); i++) {
			playerArrayListHash.add(ConvertPlayer(playersList.get(i)));
		}
		for (int i = 0; i < objectsList.size(); i++) {
			objectsArrayListHash.add(ConvertObject(objectsList.get(i)));
		}	
		
		logger.info("converted arraylists to be saved");
	}
	
	
	private LinkedHashMap<String, Object> ConvertObject(ObjectAbstract objectAbstract) {
		LinkedHashMap<String, Object> objectHash = new LinkedHashMap<>();
		objectHash.put("imagepath", objectAbstract.getImagePath());
		objectHash.put("remove", objectAbstract.isRemove());
//		objectHash.put("state", objectAbstract.getState());
		objectHash.put("position", objectAbstract.getPosition());
		objectHash.put("barx", objectAbstract.getBar().getInitialX());
		objectHash.put("bary", objectAbstract.getBar().getInitialY());
		objectHash.put("barl", objectAbstract.getBar().getLength());
		objectHash.put("bari", objectAbstract.getBar().getImagePath());
		String ob = "";
		ArrayList<ObserverIF> obs = objectAbstract.getObservers();
		for (ObserverIF o : obs) {
			ob += o.toString() + ",";
		}
		
		objectHash.put("observers", ob);
//		objectHash.put("currentlocation", objectAbstract.getCurrentLocation());

//		objectHash.put("currentobserver", objectAbstract.getCurrentObserver());
//		objectHash.put("distance", objectAbstract.getDistance());
//		objectHash.put("detectedstack", objectAbstract.getDetectedStack());
		
		return objectHash;
	}

	
	public void setMementoInverter(ArrayList<LinkedHashMap<String, Object>> playersList,
			ArrayList<LinkedHashMap<String, Object>> objectsList) {
		playerArrayList = new ArrayList<>();
		objectArrayList = new ArrayList<>();
		for (int i = 0; i < playersList.size(); i++) {
			playerArrayList.add(invertPlayer(playersList.get(i)));
		}
		for (int i = 0; i < objectsList.size(); i++) {
			objectArrayList.add(invertObject(objectsList.get(i)));
		}
		
		
	}

	private ObjectAbstract invertObject(LinkedHashMap<String, Object> linkedHashMap) {
		ObjectFactory of;
		ObjectAbstract object = null;
		try {
			of =  ObjectFactory.getObjectFactory();
			object = of.createObject((Path) linkedHashMap.get("imagepath"));
			if (linkedHashMap.get("remove").toString().equals("true")) {
				object.setRemove(true);
			} else {
				object.setRemove(false);
			}
			String p = (linkedHashMap.get("position").toString());
			p = p.substring(p.indexOf('[') +4, p.lastIndexOf(']'));
			String[] pos = p.split(",");
			object.setPosition(new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1].substring(2,pos[1].length()))));

			Bar bar = new Bar((double)linkedHashMap.get("barx"),  (double)linkedHashMap.get("bary"),  (double)linkedHashMap.get("barl"),  (Path)linkedHashMap.get("bari"));
			object.setBar(bar);
			String observersList = linkedHashMap.get("observers").toString();
			String[] obs = observersList.split(",");
			ArrayList<ObserverIF> obl = new ArrayList<>();
			for (String o : obs) {
				obl.add((ObserverIF) Class.forName(o.substring(0, o.indexOf('@'))).newInstance());
			}
			
			object.setObservers(obl);
			
			logger.info("objects arraylist reversed and loaded successfully");

			
////			object.setCurrentLocation(linkedHashMap.get("remove"));
//			object.setObservers(linkedHashMap.get("observers"));
//			object.setcurrentObserver(linkedHashMap.get("currentobserver") );
//			object.setDistance(linkedHashMap.get("distance"));
//			object.setDetectedStack(linkedHashMap.get("detectedstack"));
			
		} catch (Exception e) {
			logger.error("couldnot revert objects from saved file");
		}
		return object;
	}

	private PlayerAbstract invertPlayer(LinkedHashMap<String, Object> linkedHashMap) {
		PlayerFactory pf;
		PlayerAbstract player = null;
		try {
			pf = new PlayerFactory();
			player = pf.createPlayer((Path) linkedHashMap.get("imagepath"),(String) linkedHashMap.get("name"));
			player.setScore((int) linkedHashMap.get("score"));
			player.setxPosition((int) linkedHashMap.get("xposition"));
			player.setyPosition((int) linkedHashMap.get("yposition"));
		} catch (Exception e) {
			
		}
		return player;
	}

	private LinkedHashMap<String, Object> ConvertPlayer(PlayerAbstract player) {
		LinkedHashMap<String, Object> playerHash = new LinkedHashMap<>();
		playerHash.put("name", player.getName());
		playerHash.put("score", player.getScore());
		playerHash.put("xposition", player.getxPosition());
		playerHash.put("yposition", player.getyPosition());
		playerHash.put("imagepath", player.getImgPath());
		
		
		return playerHash;
	}

	public ArrayList<LinkedHashMap<String, Object>> getPlayerArrayListHash() {
		return playerArrayListHash;
	}

	public ArrayList<LinkedHashMap<String, Object>> getObjectsArrayListHash() {
		return objectsArrayListHash;
	}

	public ArrayList<PlayerAbstract> getPlayerArrayList() {
		return playerArrayList;
	}

	public ArrayList<ObjectAbstract> getObjectArrayList() {
		return objectArrayList;
	}
	
	
}
