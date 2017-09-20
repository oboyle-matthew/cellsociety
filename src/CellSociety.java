import actions.IAction;
import actions.Nothing;
import javafx.application.Application;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import util.Grid;
import util.Grid;
import util.XMLParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main game class runner
 */
class CellSociety {
    private static String xsd = "Society.xsd";

    public static void main(String[] args) {
    }

    /**
     * Creates an instance of the game
     *
     * @param xml: path to an xml configuration file
     */
    CellSociety(String xml) {
        Document dom = XMLParser.parse(xml, xsd);
    }

    /**
     * Initializes all instance variables from the XML file
     *
     * @param config: configuration object
     */
    private void init(Document config) {

    }

    /**
     * Calculates the grid  at given generation
     *
     * @param generation: generation number
     * @return grid of the new generation
     */
    private Grid getGeneration(int generation) {
        return null;
    }

    /**
     * Perform all the necessary computations since the last step
     *
     * @param dt: time interval
     */
    private void step(double dt) {

    }

    /**
     * Calculate all of the cell's actions
     */
    private void calculate() {

    }
}