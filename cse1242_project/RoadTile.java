
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

class RoadTile {
	int roadType;
	int rotation;
	int GridX; // x ve y yi GridPane düzenine göre almış, 0.0 bi kareyi temsil ediyor mesela
	int GridY;

	public RoadTile(int roadType, int rotation, int GridX, int GridY) {

		this.roadType = roadType;
		this.rotation = rotation;
		this.GridX = GridX;
		this.GridY = GridY;

	}

	public int getRoadType() {
		return roadType;
	}

	public void setRoadType(int roadType) {
		this.roadType = roadType;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getGridX() {
		return GridX;
	}

	public void setGridX(int gridX) {
		GridX = gridX;
	}

	public int getGridY() {
		return GridY;
	}

	public void setGridY(int gridY) {
		GridY = gridY;
	}

	public Pane getRoadTile() {

		Rectangle rectangle = new Rectangle(800 / 15, 800 / 15);

		rectangle.setX(GridX * (800 / 15));
		rectangle.setY(GridY * (800 / 15));
		rectangle.setFill(Color.rgb(239,249,254));
		Pane pane = new StackPane();
		Pane pane2 = new GridPane();

		if (roadType == 0) {
			rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);

			if (rotation == 0 || rotation ==180) {
				pane.getChildren().add(rectangle);
				return pane;
			} else {
				rectangle.setRotate(90.0);
				pane.getChildren().add(rectangle);
				return pane;
			}
		}
		if (roadType == 1) {
			if (rotation == 0) {
//				cember sag ust

				Arc arc1 = new Arc(0, 0, 45.5, 45.5, 0, 90);
				Arc arc2 = new Arc(0, 0, 7.5, 7.5, 0, 90);

				arc2.setFill(Color.LIGHTBLUE);
				arc2.setType(ArcType.ROUND);
				arc2.setTranslateY(26.5);

				arc1.setFill(Color.rgb(239,249,254));
				arc1.setType(ArcType.ROUND);
				arc1.setTranslateY(7.7);
				((GridPane) pane2).add(arc1, GridX, GridY);
				((GridPane) pane2).add(arc2, GridX, GridY);

				return pane2;
			}
			if (rotation == 90) {
//				cemberin sol ust
				Arc arc5 = new Arc(0, 0, 45.5, 45.5, 0, 90);
				Arc arc6 = new Arc(0, 0, 7.5, 7.5, 0, 90);

				arc6.setFill(Color.LIGHTBLUE);
				arc6.setType(ArcType.ROUND);
				arc6.setTranslateX(46);
				arc6.setTranslateY(26.5);
				arc6.setRotate(270);

				arc5.setFill(Color.rgb(239,249,254));
				arc5.setType(ArcType.ROUND);
				arc5.setTranslateX(7.6);
				arc5.setTranslateY(7.7);
				arc5.setRotate(270);
				((GridPane) pane2).add(arc5, GridX, GridY);
				((GridPane) pane2).add(arc6, GridX, GridY);
				return pane2;

			}
			if (rotation == 180) {
//				cemberın sol alt
				Arc arc7 = new Arc(0, 0, 45.5, 45.5, 0, 90);
				Arc arc8 = new Arc(0, 0, 7.5, 7.5, 0, 90);

				arc8.setFill(Color.LIGHTBLUE);
				arc8.setType(ArcType.ROUND);
				arc8.setTranslateX(46);
				arc8.setTranslateY(-20);
				arc8.setRotate(180);

				arc7.setFill(Color.rgb(239,249,254));
				arc7.setType(ArcType.ROUND);
				arc7.setTranslateX(7.6);
				arc7.setRotate(180);
				((GridPane) pane2).add(arc7, GridX, GridY);
				((GridPane) pane2).add(arc8, GridX, GridY);
				return pane2;

			}
			if (rotation == 270) {
				Arc arc3 = new Arc(0, 0, 45.5, 45.5, 0, 90);
				Arc arc4 = new Arc(0, 0, 7.5, 7.5, 0, 90);

				arc4.setFill(Color.LIGHTBLUE);
				arc4.setType(ArcType.ROUND);
				arc4.setTranslateY(-19.3);
				arc4.setRotate(90);

				arc3.setFill(Color.rgb(239,249,254)); // Set fill color
				arc3.setType(ArcType.ROUND);
				arc3.setRotate(90);
				((GridPane) pane2).add(arc3, 3, 1);
				((GridPane) pane2).add(arc4, 3, 1);
				return pane2;
			}

		}

		if (roadType == 2) {
			rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);
			pane.getChildren().add(rectangle);

			Rectangle rectangle1 = new Rectangle(800 / 15, 800 / 15);
			rectangle1.setHeight(rectangle.getWidth() - 2 * 7.5);
			rectangle1.setX(GridX * (800 / 15));
			rectangle1.setY(GridY * (800 / 15));
			rectangle1.setFill(Color.rgb(239,249,254));

			rectangle1.setRotate(90);
			pane.getChildren().add(rectangle1);

			return pane;

		}
		if (roadType == 3) {
			if (rotation == 0) {
				rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);
				pane.getChildren().add(rectangle);

				Rectangle rectangle1 = new Rectangle(700 / 15, 800 / 15);

				rectangle1.setHeight(rectangle.getWidth() - 2 * 7.5);
				rectangle1.setX(GridX * (800 / 15));
				rectangle1.setY(GridY * (800 / 15));
				rectangle1.setFill(Color.rgb(239,249,254));

				rectangle1.setRotate(90);
				rectangle1.setTranslateY(4);

				pane.getChildren().add(rectangle1);

				return pane;

			}
			if (rotation == 90) {
				rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);
				rectangle.setRotate(90);
				pane.getChildren().add(rectangle);

				Rectangle rectangle1 = new Rectangle(590 / 15, 800 / 15);

				rectangle1.setHeight(rectangle.getWidth() - 2 * 7.5);
				rectangle1.setX(GridX * (800 / 15));
				rectangle1.setY(GridY * (800 / 15));
				rectangle1.setFill(Color.rgb(239,249,254));
				rectangle1.setRotate(270);
				rectangle1.setTranslateX(-8);

				pane.getChildren().add(rectangle1);

				return pane;

			}
			if (rotation == 180) {
				rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);
				rectangle.setRotate(180);
				pane.getChildren().add(rectangle);

				Rectangle rectangle1 = new Rectangle(700 / 15, 800 / 15);

				rectangle1.setHeight(rectangle.getWidth() - 2 * 7.5);
				rectangle1.setX(GridX * (800 / 15));
				rectangle1.setY(GridY * (800 / 15));
				rectangle1.setFill(Color.rgb(239,249,254));
				rectangle1.setRotate(-90);
				rectangle1.setTranslateY(-5);

				pane.getChildren().add(rectangle1);

				return pane;

			}
			if (rotation == 270) { 
				rectangle.setHeight(rectangle.getHeight() - 2 * 7.5);
				rectangle.setRotate(-90);
				pane.getChildren().add(rectangle);

				Rectangle rectangle1 = new Rectangle(700 / 15, 800 / 15);

				rectangle1.setHeight(rectangle.getWidth() - 2 * 7.5);
				rectangle1.setX(GridX * (800 / 15));
				rectangle1.setY(GridY * (800 / 15));
				rectangle1.setFill(Color.rgb(239,249,254));
				rectangle1.setRotate(-180);
				rectangle1.setTranslateX(4);

				pane.getChildren().add(rectangle1);

				return pane;

			}
		}
		return null;
	}

}