import javafx.application.Application;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import util.Config;
import util.Grid;
import util.XMLParser;

import javax.management.modelmbean.XMLParseException;

public class Main extends Application {
    private static String xsd = "data/Society.xsd";

    private Config config;
    private Grid grid;
    private CellSociety cellSociety;

    private double dt = .1;
    
    private double gridSize = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {

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
    
    private void handleKeyInput (KeyCode code) {
    	if (code == KeyCode.RIGHT) {
    		//step forward
    	} if (code == KeyCode.P) {
    		//stop simulation
    	} if (code == KeyCode.S) {
    		//start simulation
    	}
    }

}
