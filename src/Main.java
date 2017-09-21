import javafx.application.Application;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import util.Config;
import util.Grid;
import util.XMLParser;

public class Main extends Application {
    private static String xsd = "Society.xsd";

    private Config config;
    private Grid grid;
    private CellSociety cellSociety;

    private double dt = .1;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void init(String xml) {
        config = new Config(XMLParser.parse(xml, xsd));
        cellSociety = new CellSociety(new Grid(config.grid));
    }

    public void update(int generation) {
        grid = cellSociety.getGeneration(generation);
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

    }
}
