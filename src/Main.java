import exceptions.XMLParseException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import util.Config;
import util.XMLParser;

import javax.xml.validation.Validator;
import java.io.File;
import java.util.HashMap;

public class Main extends Application {
    private static final double FPS = 10.;
    private static String DATA_DIRECTORY = "resources/data/";
    private static String XML_SCHEMA = DATA_DIRECTORY + "Society.xsd";

    private Config config;
    private Stage stage;
    private Scene scene;
    private Group group;
    private CellSociety cellSociety;
    private HashMap<String, Document> configList;
    private Timeline animation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            config = new Config(
                XMLParser.parse(new File(DATA_DIRECTORY + "Segregation.xml"),
                        XMLParser.getValidator(XML_SCHEMA)));
            cellSociety = new CellSociety(config);
        } catch (XMLParseException e) {
            return;
        }
        scene = new Scene(cellSociety.getGroup(), config.width, config.height);
        stage.setScene(scene);

        setAnimation();
        primaryStage.show();
    }

    private void setAnimation() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1 / FPS),
                e -> step(1 / FPS));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step(double dt) {
        cellSociety.next();
    }

    private void readConfigDirectory(String path) {
        File[] files = new File(path).listFiles();
        Validator validator = XMLParser.getValidator(XML_SCHEMA);
        if (files == null || validator == null)
            return;
        for (File file : files) {
            String fileName = file.getName();
            String ext = fileName.substring(fileName.length() - 3);
            if (ext.equals("xml"))
                configList.put(fileName, XMLParser.parse(file, validator));
        }
    }
}