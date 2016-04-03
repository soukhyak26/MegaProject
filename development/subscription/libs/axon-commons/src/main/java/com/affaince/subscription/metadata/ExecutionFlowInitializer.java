package com.affaince.subscription.metadata;


import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by anayonkar on 3/4/16.
 */
public class ExecutionFlowInitializer {
    public static void main(String[] args) throws SAXException, IOException {
        /*SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        SAXParser parser = null;
        try {
            factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource( "ExecutionFlow.xsd" )}));
            parser = factory.newSAXParser();
        }
        catch (SAXException se) {
            System.out.println("SCHEMA : " + se.getMessage());  // problem in the XSD itself
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        XMLReader reader = parser.getXMLReader();
        System.out.println("Done");*/
        /*SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File("ExecutionFlow.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File("ExecutionFlow.xml")));*/

    }
}
