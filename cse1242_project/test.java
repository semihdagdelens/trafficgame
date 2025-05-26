//Semih Dağdelen 150123023, Baran Çil 150123020, Ahmet Faruk Demir 150122035 
//In this class we created panes adding roads, buildings, trafficlights based on information in input files
//We created car objects and then traffic. Manipulating lights by clicking on them, we managed to have a simple traffic simulator game.
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;	
import java.io.FileNotFoundException;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class test extends Application {
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
	private GridPane pane = null;
	private Path[] pathArray = null;
	private Label textLabels;
	private Metadata metadata;
	private double time = 0;
	private int crashCount = 0;
	private int destinationsToWin;
	private int crashesToLose;
	private int scoreCount;
	private Pane paneForLabel;
	private int level = 1;
	private Label winCondition;
	private Label loseCondition;
	private Button nextLevelButton;
	private Button backToMenuButton;
	private Stage primaryStage;
	private ImageView imageView2;
	private boolean winHandled = false;
	private boolean levelCompleted = false;
	
	public void readFile(int level) {
		try (Scanner scanner = new Scanner(new File("level" + level + ".txt"))) {	//reading input file

			scanner.next();
			double x = Double.parseDouble(scanner.next());
			double y = Double.parseDouble(scanner.next());
			int gridX = Integer.parseInt(scanner.next());
			int gridY = Integer.parseInt(scanner.next());
			int numberOfPath = Integer.parseInt(scanner.next());
			destinationsToWin = Integer.parseInt(scanner.next());
			crashesToLose = Integer.parseInt(scanner.next());

			metadata = new Metadata(x, y, gridX, gridY, numberOfPath, destinationsToWin, crashesToLose);
			textLabels = new Label("Score: " + scoreCount + "/" + destinationsToWin + "\nCrashes:" + crashCount + "/" + crashesToLose);
			paneForLabel.getChildren().add(textLabels);
			
			winCondition = new Label("\tCongrats!\nYou passed Level " + level );	//label which will show up when level is passed 
			winCondition.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
			winCondition.setTranslateX(95);
			winCondition.setTranslateY(200);
			winCondition.setVisible(false);
			
			loseCondition = new Label("Game Over");		////label which will show up when level is not passed successfully
			loseCondition.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
			loseCondition.setTranslateX(180);
			loseCondition.setTranslateY(212);
			loseCondition.setVisible(false);

			pane = metadata.getPane();
			pathArray = new Path[metadata.numberOfPath];

			while (scanner.hasNext()) {
				String[] words = scanner.nextLine().split(" ");

				if (words[0].equals("Building")) {
					int buildingType = Integer.parseInt(words[1]);
					int rotation = Integer.parseInt(words[2]);
					int colorIndex = Integer.parseInt(words[3]);
					int row = Integer.parseInt(words[4]);
					int col = Integer.parseInt(words[5]);

					Building building = new Building(buildingType, rotation, colorIndex, row, col);
					pane.add(building.getPane(), row, col);

				}
				if (words[0].equals("TrafficLight")) {
					Double startX = Double.parseDouble(words[1]);
					Double startY = Double.parseDouble(words[2]);
					Double endX = Double.parseDouble(words[3]);
					Double endY = Double.parseDouble(words[4]);

					TrafficLight trafficLight = new TrafficLight(startX, startY, endX, endY);	//creates trafficlight objects
					trafficLight.getPane().setTranslateX((startX + endX) / 2 - 25);
					trafficLight.getPane().setTranslateY((startY + endY) / 2 - 25);

					trafficLight.circle.setOnMouseClicked(e -> {		//change the color of lights when clicked

						if (trafficLight.circle.getFill().equals(Color.RED)) {
							trafficLight.circle.setFill(Color.GREEN);

						} else if (trafficLight.circle.getFill().equals(Color.GREEN)) {
							trafficLight.circle.setFill(Color.RED);

						}

					});
					trafficLights.add(trafficLight);
					pane.getChildren().add(trafficLight.getPane());

				}
				if (words[0].equals("RoadTile")) {
					int roadType = Integer.parseInt(words[1]);
					int rotation = Integer.parseInt(words[2]);
					int GridX = Integer.parseInt(words[3]);
					int GridY = Integer.parseInt(words[4]);

					RoadTile roadTile = new RoadTile(roadType, rotation, GridX, GridY);
					pane.add(roadTile.getRoadTile(), GridX, GridY);

				}
				if (words[0].equals("Path")) {

					int type = Integer.parseInt(words[1]);

					double x1 = Double.parseDouble(words[3]);
					double y1 = Double.parseDouble(words[4]);

					if (words[2].equals("MoveTo")) {

						MoveTo moveTo = new MoveTo(x1, y1);		
						Path path = new Path(moveTo);		//create the path using moveTo position
						pathArray[type] = path;

					}
					if (words[2].equals("LineTo") && pathArray[type] != null) {
						LineTo lineTo = new LineTo(x1, y1);
						pathArray[type].getElements().add(lineTo);	// add the path LineTo positions and path is complete

					}
				}

			}

			createTraffic();
			paneForLabel.getChildren().addAll(winCondition,loseCondition);		
			pane.add(paneForLabel, 2, 2);		//add labels to the pane
			pane.add(nextLevelButton, 7, 8);
			pane.add(backToMenuButton, 7, 7);			
			
			
			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}//readFile method ends here
	

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		this.primaryStage = primaryStage;
		paneForLabel = new Pane();
		BorderPane paneForStartScreen = new BorderPane();	//create a pane for startScreen
		paneForStartScreen.setPadding(new Insets(0, 0, 160, 0));	//160 pixels of padding at the bottom
		
		Button startButton = new Button("Start");	//create startButton
		startButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 28));
		startButton.setPrefSize(100, 50);
		
		nextLevelButton = new Button("Next");		//create nextLevelButton
		nextLevelButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
		nextLevelButton.setPrefSize(60, 30);
		nextLevelButton.setVisible(false);
		
		backToMenuButton = new Button("Menu");		////create backToMenuButton
		backToMenuButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 13));
		backToMenuButton.setPrefSize(60, 30);
		backToMenuButton.setVisible(false);
		
		
		Scene scene1 = new Scene(paneForStartScreen, 600, 600);
		BorderPane.setAlignment(startButton, Pos.BOTTOM_CENTER);	
		
		ImageView imageView = new ImageView(new Image("image.jpeg"));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(600);
		imageView.setFitWidth(600);
		imageView2 = new ImageView(new Image("winScreen.jpeg"));
		imageView2.setPreserveRatio(true);
		imageView2.setFitHeight(600);
		imageView2.setFitWidth(600);		
		
		paneForStartScreen.getChildren().add(imageView);
		paneForStartScreen.setBottom(startButton);
		
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Car Game");

		startButton.setOnAction(e -> {		//move to first level when clicked
			moveNextLevel(level);
			level ++;
		});
		
		nextLevelButton.setOnAction(e -> {	//move to next level when clicked
			if (level<=5) {
				moveNextLevel(level);
				level ++;
				
			}
		});
		
		backToMenuButton.setOnAction(e -> {		//move to startScreen when clicked
			primaryStage.setScene(scene1);
			
		});
		
		primaryStage.show();	

	}

	public static void main(String[] args) throws FileNotFoundException {

		launch(args);
	}

	private void spawnCar() {
	    int index = (int) (Math.random() * pathArray.length);
	    Car newCar = new Car(pathArray[index], pane);
	    MoveTo moveTo = (MoveTo) pathArray[index].getElements().get(0); // MoveTo komutunu al

	    // Yeni araba ile diğer arabalar arasındaki mesafeyi kontrol et
	    boolean canSpawn = true;
	    for (Car existingCar : cars) {
	        // Eğer yeni arabanın spawn noktası çevresinde başka bir araba varsa spawn işlemini erteleyin
	        if (calculateDistance(moveTo.getX(), moveTo.getY(), existingCar.getCar().getTranslateX() + existingCar.getCar().getWidth() / 2, existingCar.getCar().getTranslateY() + existingCar.getCar().getHeight() / 2) < 42) {
	            canSpawn = false;
	            pane.getChildren().remove(newCar.getCar());
	            break;
	        }
	    }

	    if (canSpawn) {
	        cars.add(newCar);
	        newCar.getCar().xProperty().set(-3);
	        newCar.getCar().yProperty().set(20);
	        newCar.pathTransition.play();
	        newCar.getPathTransition().setOnFinished(event -> {
	            System.out.println("YOL TAMAMLANDI");
	            pane.getChildren().remove(newCar.getCar());
	            cars.remove(newCar);
	            ++scoreCount;
	        });
	    }
	}


	// İki nokta arasındaki mesafeyi hesaplayan yardımcı yöntem
	private double calculateDistance(double x1, double y1, double x2, double y2) {
	    return Math.sqrt(Math.abs(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}


	private void createTraffic() {

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}

	int i = 0;

	private void update() {

		time += 0.4; // Corrected time increment

		// Logic for checking cars and lights
		// For lights
		for (Car car : cars) {
			for (Car otherCar : cars) {
				 LineTo carDestination = (LineTo) car.getPath().getElements().get(car.getPath().getElements().size() - 1);
                 LineTo otherCarDestination = (LineTo) otherCar.getPath().getElements().get(otherCar.getPath().getElements().size() - 1);
				if (!car.equals(otherCar)
						&& car.getCar().getBoundsInParent().intersects(otherCar.getCar().getBoundsInParent())) {
					car.pathTransition.stop();
					otherCar.pathTransition.stop();
					cars.remove(car);
					cars.remove(otherCar);
					crashCount++;
					
					textLabels.setText("Score: " + scoreCount + "/" + destinationsToWin + "\nCrashes:" + crashCount
							+ "/" + crashesToLose);
					System.out.println("Cars removed due to collision.");

					PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
					pause.setOnFinished(event -> {
						pane.getChildren().removeAll(car.getCar(), otherCar.getCar());

					});

					pause.play();

				}

				for (TrafficLight trafficLight : trafficLights) {
					if (car.getCar().getBoundsInParent().intersects(trafficLight.circle.getBoundsInLocal())) {
						car.pathTransition.play();
						if (trafficLight.getCircle().getFill().equals(Color.GREEN)) {
							
							for (Car movingCar : cars) {

								if (movingCar.getPath().equals(car.getPath())
										&& car.getPathTransition().getCurrentTime()
												.compareTo(movingCar.getPathTransition().getCurrentTime()) == 1  ) {
									movingCar.pathTransition.play();
								}

							}

						} else {
							car.pathTransition.pause();

						}

					} else if (car.pathTransition.getStatus() == Status.PAUSED && !car.equals(otherCar)

							&& Math.abs(car.getCar().getBoundsInParent().getCenterX()
									- otherCar.getCar().getBoundsInParent().getCenterX()) < 33
							&& Math.abs(car.getCar().getBoundsInParent().getCenterY()
									- otherCar.getCar().getBoundsInParent().getCenterY()) < 33) {
						
						if (car.getPath().equals(otherCar.getPath()) || ( (carDestination.getX() == otherCarDestination.getX() && carDestination.getY() == otherCarDestination.getY()))) {
							
							otherCar.pathTransition.pause();

						} 
							 
			                    
						
					}

				}

			}
		}
		// Logic for spawning new cars
				if (time > 2) {
					if (Math.random()  < 0.1 && !levelCompleted) {
						spawnCar();
						System.out.println("Car spawned.");
					}
					time = 0;
				}
				textLabels.setText("Score: " + scoreCount + "/" + destinationsToWin + "\nCrashes:" + crashCount + "/" + crashesToLose);
				
			    if (scoreCount == destinationsToWin && !winHandled) {	//if user passed level invoke handleWinCondition()
			    	handleWinCondition();
			    	winHandled = true;
				}
			    
			    else if (crashCount == crashesToLose) {		//if user loses
					loseCondition.setVisible(true);			//show a label written Game Over
//					backToMenuButton.setVisible(true);		// make visible backToMenuButton button
					
					level = 1;	// To start from level 1 when user presses backToMenuButton
					for (int i = 0; i < cars.size(); i++) {		//travel inside cars array 
						cars.get(i).pathTransition.stop();		//stop all cars
						pane.getChildren().removeAll(cars.get(i).getCar()); 	//remove all cars from the pane 
						levelCompleted = true; 
					}
				}		
				

			}// update method ends here
			
	public void handleWinCondition() {

		winCondition.setVisible(true);		//show a label says user that he passed that level
		for (int i = 0; i < cars.size(); i++) {		//travel inside cars array 
			cars.get(i).pathTransition.stop();		//stop all cars
			pane.getChildren().removeAll(cars.get(i).getCar()); 	//remove all cars from the pane 
			levelCompleted = true;
		}
		 
		if(level<=5) { 
			nextLevelButton.setVisible(true); 		//make nextLevelButton visible for user to move next level
			 
			 
		}	 
		else { 		//passed five levels successfully so that level is 6 now and winScreen is being shown
			level =1;	//when user presses backToMenuButton and then startButton, he/she must start from level 1
			
			BorderPane paneForWinScreen = new BorderPane();
			paneForWinScreen.setPadding(new Insets(0, 0, 40, 0));	//40 pixels of padding at the bottom
			BorderPane.setAlignment(backToMenuButton, Pos.BOTTOM_CENTER);
			paneForWinScreen.getChildren().add(imageView2); 
			paneForWinScreen.setBottom(backToMenuButton);
			backToMenuButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
			backToMenuButton.setPrefSize(75, 40);
			//backToMenuButton.setVisible(true); 
			Scene scene = new Scene(paneForWinScreen, 580, 580);
			primaryStage.setScene(scene);
		}		
	}
	
	public void moveNextLevel(int level) {	//First reset info and clear pane then reads next level and sets the new Scene
		
		if (level>1) {
			pane.getChildren().clear();
			cars.removeAll(cars);
			paneForLabel.getChildren().clear();		
			scoreCount = 0;
			crashCount = 0;
			winHandled = false;	
			levelCompleted = false;
			nextLevelButton.setVisible(false);
			backToMenuButton.setVisible(false);	
		}
				
		readFile(level);		//reads input depends on level value
		Scene scene = new Scene(pane, 800, 800);	//create new scene
		primaryStage.setScene(scene);		//set new scene
	}
}