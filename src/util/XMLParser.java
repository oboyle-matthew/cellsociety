package util;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;;import java.io.File;
import java.io.IOException;

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
    private static Document parse(String xml) {
        DocumentBuilder builder;
        Document dom = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            dom = builder.parse(xml);
        } catch (ParserConfigurationException | SAXException e) {
            System.out.print("Error parsing " + xml);
        } catch (IOException e) {
            System.out.print("File " + xml + " not found.");
        }

        return dom;
    }

    /**
     * Validates an XML file against a schema and parses it
     *
     * @param xml: path to an xml file
     * @param xsd: path to an xsd file
     * @return a dom object, generated from XML
     */
    public static Document parse(String xml, String xsd) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Validator validator;
        try {
            validator = factory.newSchema(new File(xsd)).newValidator();
        } catch (SAXException e) {
            System.out.print("Error parsing " + xsd);
            return null;
        }

        try {
            validator.validate(new StreamSource(new File(xml)));
            return parse(xml);
        } catch (SAXException e) {
            System.out.print("Invalid xml file " + xml);
        } catch (IOException e) {
            System.out.print("File " + xml + " not found.");
        }
        return null;
    }
}