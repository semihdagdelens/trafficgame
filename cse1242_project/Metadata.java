
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Metadata {
	double x;
	double y;
	int numOfGridX;
	int numOfGridY;
	int destinationsToWin;
	int crashesToLose;
	int numberOfPath;
	Rectangle[][] grids;
	GridPane gridPane;
	int currentScore;
	int currentCrashes;

	public Metadata(double x, double y, int numOfGridX, int numOfGridY, int numberOfPath, int destinationsToWin, int crashesToLose) {

		this.x = x;
		this.y = y;
		this.numOfGridX = numOfGridX;
		this.numOfGridY = numOfGridY;
		this.destinationsToWin = destinationsToWin;
		this.crashesToLose = crashesToLose;
		this.numberOfPath=numberOfPath;
		grids = new Rectangle[numOfGridX][numOfGridY]; 
		gridPane= new GridPane();
	}

	public GridPane getPane() {
		 

		for (int i = 0; i < numOfGridX; i++)
			for (int j = 0; j < numOfGridY; j++) {
				Rectangle square = new Rectangle(x / numOfGridX, y / (numOfGridY));
				square.setFill(Color.LIGHTBLUE);
				gridPane.add(square, j, i);
				
			}
		//gridPane.add(addText(), 1, 1);
		
		gridPane.toBack();
		return gridPane;

	}


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getNumOfGridX() {
		return numOfGridX;
	}

	public void setNumOfGridX(int numOfGridX) {
		this.numOfGridX = numOfGridX;
	}

	public int getNumOfGridY() {
		return numOfGridY;
	}

	public void setNumOfGridY(int numOfGridY) {
		this.numOfGridY = numOfGridY;
	}

	public int getDestinationsToWin() {
		return destinationsToWin;
	}

	public void setDestinationsToWin(int destinationsToWin) {
		this.destinationsToWin = destinationsToWin;
	}

	public int getCrashesToLose() {
		return crashesToLose;
	}

	public void setCrashesToLose(int crashesToLose) {
		this.crashesToLose = crashesToLose;
	}

	public Rectangle[][] getGrids() {
		return grids;
	}

	public void setGrids(Rectangle[][] grids) {
		this.grids = grids;

	}
	public void updateNumberOfPath() {
		this.numberOfPath++;
	}
	public Pane addText() {
		Pane pane = new Pane();
		Label label = new Label("Score: " + currentScore + "/" + destinationsToWin + "\nCrashes:"+ currentCrashes + "/"+ crashesToLose );
		pane.getChildren().add(label);
		pane.setLayoutX(5);
		return pane;	
	}

	public int getNumberOfPath() {
		return numberOfPath;
	}

	public void setNumberOfPath(int numberOfPath) {
		this.numberOfPath = numberOfPath;
	}

}