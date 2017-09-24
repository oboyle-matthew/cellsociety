package util;

import actions.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.management.modelmbean.XMLParseException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class holding all the parameters for
 */
public class Config {
    /**
     * Enum of types of fill
     * Essential on render -> has to be public
     */
    public enum Fill {
        COLOR, IMAGE
    }

    private enum Type {
        GRID, RANDOM
    }

    class CellType {
        ArrayList<FillType> fills;
        Class<? extends Action> ActionClazz;
        Character symbol;
        double ratio;

        Action getAction() {
            try {
                return ActionClazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.print(e.toString());
                return null;
            }
        }

        public void setRatio(double ratio) {
            this.ratio = ratio;
        }

        public double getRatio() {
            return ratio;
        }
    }

    class FillType {
        Fill type;
        String content;
        Paint paint;

        FillType(String typeString, String content) throws XMLParseException {
            try {
                type = Fill.valueOf(typeString);
            } catch (NullPointerException | IllegalArgumentException e) {
                throw new XMLParseException("Invalid fill type " + typeString);
            }
            switch (type) {
                case COLOR:
                    try {
                        paint = Color.valueOf(content);
                    } catch (NullPointerException | IllegalArgumentException ignored) {
                        try {
                            paint = Color.web(content);
                        } catch (NullPointerException | IllegalArgumentException e) {
                            throw new XMLParseException("Illegal color value");
                        }
                    }
                    break;

                case IMAGE:
                    File imageFile = new File("resources/data/" + content);
                    if (!imageFile.exists())
                        throw new XMLParseException("Image file " + content + " not found." +
                                "All images must be located in resources/images");
                    Image image = new Image("resources/data/" + content);
                    paint = new ImagePattern(image);
                    break;
            }
        }
    }

    private static String DEFAULT_TIME_UNIT = "seconds";
    private static double DEFAULT_STEP = 1.0;

    private static boolean DEFAULT_FINITE = false;
    private static double DEFAULT_END_TIME = 1.0;

    // Ability to add several fills in sequence
    private static Fill DEFAULT_FILL[] = {Fill.COLOR};

    private HashMap<Character, CellType> cellTypes;
    private CellType[][] grid = null;
    public String time_unit = DEFAULT_TIME_UNIT;
    public double step = DEFAULT_STEP;

    public boolean finite = DEFAULT_FINITE;
    public double end_time = DEFAULT_END_TIME;

    public Config(Document xml) throws XMLParseException {
        parseTimeLine(xml);
        parseCells(xml);
        parseState(xml);
    }

    public CellType[][] getGrid() {
        return grid;
    }

    /**
     * Gets first child with tag provided
     *
     * @param xml: document to search
     * @param tag: tag
     * @return content of the tag
     */
    private static String getTextFromTag(Document xml, String tag) {
        Element firstChild = getFirstChild(xml, tag);
        return firstChild == null ? null : firstChild.getTextContent();
    }

    private static Element getFirstChild(Document xml, String tag) {
        NodeList list = xml.getElementsByTagName(tag);
        return list.getLength() == 0 ? null : (Element) list.item(0);
    }

    private static Element getFirstChild(Element root, String tag) {
        NodeList list = root.getElementsByTagName(tag);
        return list.getLength() == 0 ? null : (Element) list.item(0);
    }

    private void parseTimeLine(Document xml) {
        String buffer;
        time_unit = (buffer = getTextFromTag(xml, "time-unit")) == null ? time_unit : buffer;
        step = (buffer = getTextFromTag(xml, "step")) == null ? step : Double.parseDouble(buffer);
        finite = (buffer = getTextFromTag(xml, "finite")) == null ? finite : Boolean.parseBoolean(buffer);
        end_time = (buffer = getTextFromTag(xml, "end-time")) == null ? end_time : Double.parseDouble(buffer);
    }

    private void parseCells(Document xml) throws XMLParseException {
        NodeList cells_dom = xml.getElementsByTagName("cell");
        boolean autoFill = false;
        for (int i = 0; i < cells_dom.getLength(); ++i) {
            Element cellTypeDef = (Element) cells_dom.item(i);
            CellType cellType = new CellType();
            cellType.fills = new ArrayList<>();

            boolean fillDefined = parseFill(cellType, cellTypeDef);
            parseSymbol(cellType, cellTypeDef);
            parseAction(cellType, cellTypeDef);

            if (autoFill && !fillDefined)
                throw new XMLParseException("If fill must be defined or undefined for all cells");
            autoFill = !fillDefined;

            cellTypes.put(cellType.symbol, cellType);
        }
        if (autoFill)
            createAutoFill();
    }

    private void parseSymbol(CellType cellType, Element cellTypeDef) throws XMLParseException {
        Node node = getFirstChild(cellTypeDef, "symbol");
        if (node == null)
            throw new XMLParseException("Cell type must have a symbol");

        String symbol = node.getTextContent();
        if (symbol.length() != 1)
            throw new XMLParseException("Symbol must be length 1");

        cellType.symbol = symbol.charAt(0);
        if (cellType.symbol == '0')
            throw new XMLParseException("Symbol 0 is reserved for empty space");

        if (cellTypes.containsKey(cellType.symbol))
            throw new XMLParseException("Symbol must be unique");
    }

    private boolean parseFill(CellType cellType, Element cellTypeDef) throws XMLParseException {
        NodeList list = cellTypeDef.getElementsByTagName("fill");
        if (list.getLength() == 0)
            return false;
        for (int i = 0; i < list.getLength(); ++i) {
            Element fillDef = (Element) list.item(i);
            Node type_node = getFirstChild(fillDef, "type");
            Node content_node = getFirstChild(fillDef, "content");
            if (type_node == null || content_node == null)
                throw new XMLParseException("Fill must have both type and content");
            cellType.fills.add(new FillType(type_node.getTextContent(), content_node.getTextContent()));
        }
        return true;
    }

    private void parseAction(CellType cellType, Element cellTypeDef) throws XMLParseException {
        Node action_node = getFirstChild(cellTypeDef, "action");
        if (action_node == null)
            throw new XMLParseException("Cell type must define an action");
        try {
            cellType.ActionClazz = Class.forName("actions."
                    + action_node.getTextContent()).asSubclass(Action.class);
        } catch (ClassNotFoundException | LinkageError e) {
            throw new XMLParseException("Action " + action_node.getTextContent() + " not found." +
                    "Actions must be located in actions/");
        } catch (ClassCastException e) {
            throw new XMLParseException("Action must extend Action class");
        }
    }

    private void parseState(Document xml) throws XMLParseException {
        Element state_dom = getFirstChild(xml, "state");
        if (state_dom == null) {
            generateEvenDistribution();
            return;
        }
        Node type_dom = getFirstChild(state_dom, "type");
        Type type;
        if (type_dom == null)
            throw new XMLParseException("Type must be defined");
        try {
            type = Type.valueOf(type_dom.getTextContent());
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new XMLParseException("Invalid state type " + type_dom.getTextContent());
        }

        switch (type) {
            case GRID:
                parseGrid(state_dom);
                break;
            case RANDOM:
                parseDistribution(state_dom);
                break;
        }
    }

    private void generateEvenDistribution() {
        int n = cellTypes.size();
        for (CellType cell : cellTypes.values())
            cell.setRatio(1 / n);
    }

    private void parseGrid(Element state_dom) throws XMLParseException {
        Node grid_dom = getFirstChild(state_dom, "grid");
        if (grid_dom == null)
            throw new XMLParseException("Grid must be defined, when grid type is selected");
        String grid_str = grid_dom.getTextContent();
        String[] lines = grid_str.split("\n");
        if (lines.length < 1 || lines[0].length() < 1)
            throw new XMLParseException("Grid cannot be empty");

        int rows = lines.length;
        int cols = lines[0].length();
        grid = new CellType[cols][rows];
        for (int j = 0; j < rows; ++j) {
            if (lines[j].length() != cols)
                throw  new XMLParseException("All rows must be same size");
            for (int i = 0; i < cols; ++i) {
                char c = lines[j].charAt(i);
                grid[i][j] = cellTypes.get(c);
            }
        }
    }

    private void parseDistribution(Element state_dom) {

    }

    private void createAutoFill() {

    }
}
