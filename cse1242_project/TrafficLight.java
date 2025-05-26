
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TrafficLight {

	double startX;
	double endX;
	double startY;
	double endY;
	Line line;
	StackPane pane;

	Circle circle;

	public TrafficLight(double startX, double startY, double endX, double endY) {
		pane = new StackPane();
		
		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
		line = new Line(startX, startY, endX, endY);
		double centerX = (startX + endX) / 2.0;
		double centerY = (startY + endY) / 2.0;
		circle = new Circle(centerX, centerY, 4);
		circle.setFill(Color.GREEN);
		pane.setPrefSize(30, 30);
		
		pane.getChildren().addAll(line,circle);
		
		
		
		
		
		
		
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(StackPane pane) {
		this.pane = pane;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

}
