import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import util.Config;
import util.Grid;
import util.XMLParser;

public class Main extends Application {
    private static String xsd = "Society.xsd";

    private Config config;
    private Grid grid;
    private CellSociety cellSociety;

    private double dt = .1;
    
    private double gridSize = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void init(String xml) {
        config = new Config(XMLParser.parse(xml, xsd));
        cellSociety = new CellSociety(new Grid(config.grid));
    }

    public void update(int generation) {
        grid = cellSociety.calculate(generation);
        draw(grid);
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
        
        scene.setFill(pattern);
    }
}
