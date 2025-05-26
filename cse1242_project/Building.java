
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Building {

	int buildingType;
	Color[] colors = new Color[4];
	int rotation;
	int colorIndex;
	int gridX;
	int gridY;
	Pane pane;

	Rectangle rectangle;
	Rectangle rectangle1;
	Rectangle rectangle2;
	Circle circle1;
	Circle circle2;
	private static final double SQUARE_SIZE = 800.0 / 15;

	public Building(int buildingType, int rotation, int colorIndex, int row, int col) {

		this.buildingType = buildingType;
		this.rotation = rotation;
		this.colorIndex = colorIndex;
		this.gridX = row;
		this.gridY = col;
		pane = new Pane();
		colors[0] = Color.rgb(255, 204, 155);// orange
		colors[1] = Color.rgb(145, 228, 191);// green
		colors[2] = Color.rgb(177, 173, 229);// purple
		colors[3] = Color.rgb(239, 126, 145);// red

		if (buildingType == 0) {
			createBuildingType0();

			if (rotation == 0) {

				rectangle1.setTranslateX(10);
				rectangle2.setTranslateX(21);
				rectangle1.setTranslateY(9);
				rectangle2.setTranslateY(20);
				pane.getChildren().addAll(rectangle, rectangle1, rectangle2);
				GridPane.setColumnIndex(pane, col);
				GridPane.setRowIndex(pane, row);
			}
			if (rotation == 90) {

				rectangle1.setTranslateX(-43);
				rectangle2.setTranslateX(-32);
				rectangle1.setTranslateY(9);
				rectangle2.setTranslateY(20);
				rectangle.setTranslateX(-53);

				pane.getChildren().addAll(rectangle, rectangle1, rectangle2);
				GridPane.setColumnIndex(pane, col);
				GridPane.setRowIndex(pane, row);
				pane.setRotate(270);
			}

			if (rotation == 180) {

				rectangle1.setTranslateX(-43);
				rectangle2.setTranslateX(-32);
				rectangle.setTranslateX(-53);
				rectangle1.setTranslateY(-97);
				rectangle2.setTranslateY(-86);
				rectangle.setTranslateY(-106);
				pane.getChildren().addAll(rectangle, rectangle1, rectangle2);
				GridPane.setColumnIndex(pane, col);
				GridPane.setRowIndex(pane, row);
				pane.setRotate(180);

			}

			if (rotation == 270) {

				rectangle1.setTranslateX(10);
				rectangle2.setTranslateX(21);
				rectangle1.setTranslateY(-97);
				rectangle2.setTranslateY(-86);
				rectangle.setTranslateY(-106);
				pane.getChildren().addAll(rectangle, rectangle1, rectangle2);
				GridPane.setColumnIndex(pane, col);
				GridPane.setRowIndex(pane, row);
				pane.setRotate(90);
			}
		}
		if (buildingType == 1) {
			createBuildingType1();
			if (rotation == 0) {

				circle1.setTranslateX(53);
				circle2.setTranslateX(53);
				circle1.setTranslateY(53);
				circle2.setTranslateY(53);
				pane.getChildren().addAll(rectangle, circle1, circle2);

			}
			if (rotation == 90) {
				circle1.setTranslateY(53);
				circle2.setTranslateY(53);
				rectangle.setTranslateX(-53);
				pane.getChildren().addAll(rectangle, circle1, circle2);
				pane.setRotate(270);
			}

			if (rotation == 180) {

				rectangle.setTranslateX(-53);
				rectangle.setTranslateY(-106.33);
				circle1.setTranslateY(-53);
				circle2.setTranslateY(-53);
				pane.getChildren().addAll(rectangle, circle1, circle2);
				pane.setRotate(180);

			}

			if (rotation == 270) {
				circle1.setTranslateX(53);
				circle2.setTranslateX(53);
				circle1.setTranslateY(-53);
				circle2.setTranslateY(-53);
				rectangle.setTranslateY(-106.66);
				pane.setRotate(90);
				pane.getChildren().addAll(rectangle, circle1, circle2);

			}
		}
		if (buildingType == 2) {
			createBuildingType2();
			if (rotation == 0) {

				pane.getChildren().addAll(rectangle);

			}
			if (rotation == 90) {
				pane.getChildren().addAll(rectangle);
				pane.setRotate(270);
			}

			if (rotation == 180) {

				pane.getChildren().addAll(rectangle);
				pane.setRotate(180);

			}

			if (rotation == 270) {
				pane.getChildren().addAll(rectangle);
				pane.setRotate(90);

			}
		}

	}

	public Pane getPane() {
		return pane;
	}

	public Color getStrokeColor() {
		if (colorIndex == 0) {
			return Color.rgb(242, 185, 141);// orange

		}
		if (colorIndex == 1) {
			return Color.rgb(132, 208, 173);// green
		}
		if (colorIndex == 2) {
			return Color.rgb(159, 155, 204);// purple
		}
		if (colorIndex == 3) {
			return Color.rgb(216, 118, 135);// red
		}
		return null;
	}

	public Color getColor() {
		if (colorIndex == 0) {
			return Color.rgb(255, 204, 155);// orange
		}
		if (colorIndex == 1) {
			return Color.rgb(145, 228, 191);// green
		}
		if (colorIndex == 2) {
			return Color.rgb(177, 173, 229);// purple
		}
		if (colorIndex == 3) {
			return Color.rgb(239, 126, 145);// red
		}
		return null;
	}

	private Circle createCircle(double radius, Color color) {
		Circle circle = new Circle(radius);
		circle.setFill(color);
		circle.setStroke(getStrokeColor()); // Çember kenarlığının rengi
		circle.setStrokeWidth(5); // Çember kenarlığının kalınlığı

		return circle;
	}

	private Rectangle createRectangle(double width, double height, Color color) {
		Rectangle rectangle = new Rectangle(width * SQUARE_SIZE, height * SQUARE_SIZE);
		rectangle.setFill(color);
		return rectangle;
	}

	public void createBuildingType0() {
		rectangle = createRectangle(2, 3, Color.rgb(238, 248, 254));
		rectangle1 = createRectangle(1.6, 1.6, getColor());
		rectangle1.setStroke(getStrokeColor());
		rectangle2 = createRectangle(1.2, 1.2, getColor());
		rectangle2.setStroke(getStrokeColor());
		rectangle.setStroke(Color.GRAY);
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		rectangle1.setArcHeight(10);
		rectangle1.setArcWidth(10);
		rectangle2.setArcHeight(10);
		rectangle2.setArcWidth(10);
		rectangle1.setStrokeWidth(5);
		rectangle2.setStrokeWidth(5);

	}

	public void createBuildingType1() {
		rectangle = createRectangle(2, 3, Color.rgb(238, 248, 254));
		rectangle.setStroke(Color.GRAY);
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		circle1 = createCircle(45, getColor());
		circle2 = createCircle(35, getColor());

	}

	public void createBuildingType2() {
		rectangle = createSquare(1, 1, getColor());
		rectangle.setStroke(getStrokeColor());
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		rectangle.setStrokeWidth(2);

	}

	public Rectangle createSquare(double width, double height, Color color) {
		Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
		square.setFill(color);
		return square;
	}

	public int getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(int buildingType) {
		this.buildingType = buildingType;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getColorIndex() {
		return colorIndex;
	}

	public void setColorIndex(int colorIndex) {
		this.colorIndex = colorIndex;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

}