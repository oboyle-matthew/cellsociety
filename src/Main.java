import javafx.application.Application;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import util.Config;
import util.XMLParser;

public class Main extends Application {
    private static String xsd = "Society.xsd";

    private Config config;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void init(String xml) {
        config = new Config(XMLParser.parse(xml, xsd));
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
}
