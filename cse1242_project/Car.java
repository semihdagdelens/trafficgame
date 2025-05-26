
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class Car {

	Path path;
	Pane pane;
	PathTransition pathTransition;
	Rectangle car;
	double time;
	double X,Y;
	double pathLength;
	
	
	public Car(Path path, Pane pane) {
		this.path = path;
		
		car = new Rectangle(17, 8, Color.rgb(86,65,153));
		pathTransition = new PathTransition(Duration.seconds(getPathLength()/100), path, car);
		pathTransition.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
		pane.getChildren().add(car);
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.cycleCountProperty().set(1);
		pathTransition.setInterpolator(Interpolator.LINEAR);
	
	}


	public Path getPath() {
		return path;
	}


	public void setPath(Path path) {
		this.path = path;
	}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}


	public PathTransition getPathTransition() {
		return pathTransition;
	}


	public void setPathTransition(PathTransition pathTransition) {
		this.pathTransition = pathTransition;
	}


	public Rectangle getCar() {
		return car;
	}


	public void setCar(Rectangle car) {
		this.car = car;
	}


	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}
	
	
	
	
	public double calculatePathLength(Path []paths) {
		int length =0;
		
		return length;
	}
	public double getPathLength() {
		for (int i = 1; i < path.getElements().size(); i++) {
			
	        PathElement currentElement = path.getElements().get(i);
	        PathElement previousElement = path.getElements().get(i - 1);
	        
	        if (currentElement instanceof LineTo && previousElement instanceof LineTo) {
	            LineTo currentLineTo = (LineTo) currentElement;
	            LineTo previousLineTo = (LineTo) previousElement;
	            double x1 = previousLineTo.getX();
	            double x2 = currentLineTo.getX();
	            double y1 = previousLineTo.getY();
	            double y2 = currentLineTo.getY();
	            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	            pathLength += distance;
	        }   
	            
	        else if (currentElement instanceof LineTo && previousElement instanceof MoveTo) {
	        	LineTo currentLineTo = (LineTo) currentElement;
	        	MoveTo previousLineTo = (MoveTo) previousElement;
	        	double x1 = previousLineTo.getX();
	            double x2 = currentLineTo.getX();
	            double y1 = previousLineTo.getY();
	            double y2 = currentLineTo.getY();
	            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	            pathLength += distance;
	        }
	    }
		return pathLength;
	}


}