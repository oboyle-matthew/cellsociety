package util;

import actions.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
        COLOR, SYMBOL, IMAGE;
    }

    private enum Type {
        GRID, RANDOM;
    }

    class CellType {

    }

    private static String DEFAULT_TIME_UNIT = "seconds";
    private static double DEFAULT_STEP = 1.0;

    private static boolean DEFAULT_FINITE = false;
    private static double DEFAULT_END_TIME = 1.0;

    // Ability to add several fills in sequence
    private static Fill DEFAULT_FILL[] = {Fill.COLOR};

    private HashMap<Character, Class<? extends IAction>> cls;
    public String time_unit = DEFAULT_TIME_UNIT;
    public double step = DEFAULT_STEP;

    public boolean finite = DEFAULT_FINITE;
    public double end_time = DEFAULT_END_TIME;

    public Config(Document xml) {
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

    private void parseCells(Document xml) {
        NodeList cells_dom = xml.getElementsByTagName("cell");
        for (int i = 0; i < cells_dom.getLength(); ++i) {
            Element cell_type = (Element) cells_dom.item(i);

        }
    }
}
