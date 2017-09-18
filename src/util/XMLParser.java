package util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

/**
 * Class that implements parsing XML
 */
public class XMLParser {

    /**
     * Parses an xml file
     *
     * @param xml: path to an xml file
     * @return a dom object, generated from XML
     */
    public static Document parse(String xml) {
        return null;
    }

    /**
     * Verifies and parses an xml file
     *
     * @param xml: path to an xml file
     * @param xsd: path to an xsd file
     * @return a dom object, generated from XML
     */
    public static Document parse(String xml, String xsd) {
        return null;
    }

    /**
     * Validates that all of the necessary values are present
     *
     * @param xml: a dom-object
     * @param xsd: path to an xsd file
     */
    private static void validate(Document xml, Document xsd) {

    }
}