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
        COLOR, IMAGE;
    }

    private enum Type {
        GRID, RANDOM;
    }

    class CellType {
        ArrayList<FillType> fills;
        Class<Action> Action;
        Character symbol;
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

    private HashMap<Character, Class<Action>> cellTypes;
    public String grid;
    public String time_unit = DEFAULT_TIME_UNIT;
    public double step = DEFAULT_STEP;

    public boolean finite = DEFAULT_FINITE;
    public double end_time = DEFAULT_END_TIME;

    public Config(Document xml) throws XMLParseException {
        parseTimeLine(xml);
        parseCells(xml);
    }

    public Cell fromSymbol(char s) {
        return null;
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

            parseSymbol(cellType, cellTypeDef);
            if (!parseFill(cellType, cellTypeDef))
                autoFill = false;

            if (cellTypes.containsKey(cellType.symbol))
                throw new XMLParseException("Symbol must be unique");


            cellTypes.put(cellType.symbol, null);
        }
    }

    private void parseSymbol(CellType cellType, Element cellTypeDef) throws XMLParseException {
        Node node = cellTypeDef.getElementsByTagName("symbol").item(0);
        if (node == null)
            throw new XMLParseException("Cell type must have a symbol");
        String symbol = node.getTextContent();
        if (symbol.length() != 1)
            throw new XMLParseException("Symbol must be length 1");
        cellType.symbol = symbol.charAt(0);
    }

    private boolean parseFill(CellType cellType, Element cellTypeDef) throws XMLParseException{
        NodeList list = cellTypeDef.getElementsByTagName("fill");
        if (list.getLength() == 0)
            return false;
        for (int i = 0; i < list.getLength(); ++i) {
            Element fillDef = (Element) list.item(i);
            Node type_node = fillDef.getElementsByTagName("type").item(0);
            Node content_node = fillDef.getElementsByTagName("content").item(0);
            if (type_node ==null || content_node == null)
                throw new XMLParseException("Fill must have both type and content");
            cellType.fills.add(new FillType(type_node.getTextContent(), content_node.getTextContent()));
        }
        return true;
    }
}
