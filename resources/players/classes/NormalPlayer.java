
package players.classes;

import java.applet.Applet;
import java.awt.Point;
import java.nio.file.Path;

import controller.GameController;
import gameConfigurationsReader.IGuiReader;
import gameConfigurationsReader.configurationsReaders.GuiPropertiesReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import observer.ObserverIF;
import observer.StackObserver;
import player.PlayerAbstract;
import view.MainGui;

public class NormalPlayer extends PlayerAbstract {
	
	private ImageView imageView;
	private Image image;
	
		
	private IGuiReader guiProperties;

	private ObserverIF stackObserver;
	

	@Override
	public void setStackObserver() throws Exception {
		stackObserver = new StackObserver(this.getxPosition(),
		this.getxPosition() + guiProperties.getObjectWidth() , this.getyPosition());

	}
	
	@Override
	public ObserverIF getStackObserver() {
		return this.stackObserver;
	}

	public ImageView getImageView() {
		return imageView;
	}


	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public NormalPlayer(Path img, String name) {
		super(img, name);
		guiProperties = new GuiPropertiesReader();
	}


	@Override
	public Point moveBy() throws Exception {
		Point position = new Point();
		int dx = 0;
		
//		double initialX = controller.getImageView().getLayoutX();
//		double y = controller.getImageView().getLayoutY();
//		double initialX = imageView.getLayoutX();
//		double y = imageView.getLayoutY();
		double initialX = getxPosition();
		double y = getyPosition();
		
/*		if (way == null) {
			position.setLocation(initialX, y);
			return position;
		}*/


		 if (isMoveright()) {
			 dx += this.getPlayerSpeed();
			 
		 } else if (isMoveLeft()) {
			 dx -= this.getPlayerSpeed();
		 }
		 
//		 double cx = controller.getImageView().getBoundsInLocal().getWidth() / 2;
		 double cx = imageView.getBoundsInLocal().getWidth() / 2;
		 double x = cx + initialX + dx;
	       
		 position = moveImageTo(x, y);
		 
		 if (position == null) {
			 position = new Point();
			 position.setLocation(initialX, y);
		 }
		 
		 updateObserversXPosition(position.x);
		 return position;
	}
	
	private void updateObserversXPosition(int x) throws Exception {
		stackObserver.setxStart(x);
		stackObserver.setxEnd(x+ guiProperties.getPlayerWidth());
	}
	
	private Point moveImageTo(double x, double y) throws Exception {
		Point position = new Point();
//		double cx = controller.getImageView().getBoundsInLocal().getWidth()  / 2;
		double cx = imageView.getBoundsInLocal().getWidth()  / 2;
	       
       /* if ((x - cx >= 0) && (x + cx <= controller.getProperties().getGuiLength())) {
//            controller.changePositionTo(x - cx, y);
        	position.setLocation(x - cx, y);
        	return position;
        }*/
		if ((x - cx >= 0) && (x + cx <= MainGui.getScene().getWidth())) {
//          controller.changePositionTo(x - cx, y);
      	position.setLocation(x - cx, y);
      	
      	return position;
      }
		
//        position.setLocation(x, y);
        return null;
	}


}