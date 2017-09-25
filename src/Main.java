import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import util.Config;
import util.Grid;
import util.XMLParser;

import exceptions.XMLParseException;

public class Main extends Application {
	public static final int SEGREGATION = 0;
	public static final int PREDATOR_PREY = 1;
	public static final int FIRE = 2;
	public static final int GAME_OF_LIFE = 3;
	
	private int gametype;
	
	
	private boolean startGame;
	
    private static String xsd = "data/Society.xsd";
    private final String TITLE = "Team 15";
    private final int FRAMES_PER_SECOND = 60;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
//	private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private final Paint BACKGROUND = Color.ALICEBLUE;
	private int generation;
	Label gene = new Label("Generation:" + Integer.toString(generation));


    private Config config;
    private Grid grid;
    private CellSociety cellSociety;

    private double dt = .1;
    private Stage theStage;
    private Scene myScene;
    private Group root;
    private Timeline animation;
    private double gridSize = 500;
    
    private double secondDelay = 1.0 / FRAMES_PER_SECOND;

    @Override
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;
    	root = new Group();
    	myScene = new Scene(root, gridSize, gridSize, BACKGROUND);
    	primaryStage.setScene(myScene);
    	primaryStage.setTitle(TITLE);
    	primaryStage.show();
    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                  e -> update(generation));
                                  
        init(xsd);
        
    	animation = new Timeline();
    	animation.setCycleCount(Timeline.INDEFINITE);
    	animation.getKeyFrames().add(frame);
    	animation.play();

    }

    private void init(String xml) {
        try {
            Document dom = XMLParser.parse(xml, xsd);
            config = new Config(dom);
        } catch (XMLParseException e) {
            System.err.print(e.toString());
            System.exit(0);
        }
        cellSociety = new CellSociety(config);
    }

    public void update(int generation) {
        grid = cellSociety.calculate(generation);
    }

    /**
     * Initializes event handlers
     */
    private void initEventHandlers() {

    }

    /**
     * Handle keyboard events
     */
    private void handleKeyboardEvents() {

    }

    /**
     * Handle slider events
     */
    private void handleSliderEvents() {

    }

    private void draw(Grid grid) {
        //https://stackoverflow.com/questions/28870460/how-to-create-a-background-grid
    	grid.getGroup();
    	double w = gridSize;
        double h = gridSize;

        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTGRAY.deriveColor(1, 1, 1, 0.2));
        gc.fillRect(0, 0, w, h);
        gc.strokeRect(0, 0, w, h);

        Image image = canvas.snapshot(new SnapshotParameters(), null);
        ImagePattern pattern = new ImagePattern(image, 0, 0, w, h, false);
        
        // scene.setFill(pattern);
    }
    
    
    //Note - need to add location of buttons
    private void createButton(int type) {
    	Button button = new Button();
    	button.addEventHandler(MouseEvent.MOUSE_CLICKED,
    		    new EventHandler<MouseEvent>() {
    		        @Override public void handle(MouseEvent e) {
    		            gametype = type;
    		            //make button disappear as well
    		            startGame = true;
    		        }
    	});
    }
    
    private void speedButton(double speedmultiplier) {
    	Button speed = new Button();
    	speed.addEventHandler(MouseEvent.MOUSE_CLICKED,
    		    new EventHandler<MouseEvent>() {
    		        @Override public void handle(MouseEvent e) {
    		            secondDelay *= speedmultiplier;
    		        }
    	});
    }
    
    private void speedUpButton() {
    	speedButton(0.9);
    }
    
    private void slowDownButton() {
    	speedButton(1/0.9);
    }
    
    
    //NEED TO ADD LOCATION OF BUTTONS
    private void createStartButtons() {
    	createButton(SEGREGATION);
    	createButton(PREDATOR_PREY);
    	createButton(FIRE);
    	createButton(GAME_OF_LIFE);
    }
    
    
    
    private void handleKeyInput (KeyCode code) {
    	if (code == KeyCode.RIGHT && !startGame) {
    		//step forward (only if game is paused)
    	}
    	if (code == KeyCode.P) {
    		startGame = false;
    		//stop simulation
    	} if (code == KeyCode.S) {
    		startGame = true;
    		//start simulation
    	}
    }
}
