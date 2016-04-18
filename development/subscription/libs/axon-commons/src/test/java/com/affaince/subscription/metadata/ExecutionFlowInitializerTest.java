package com.affaince.subscription.metadata;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by anayonkar on 3/4/16.
 */
    //http://www.journaldev.com/895/how-to-validate-xml-against-xsd-in-java
    //http://www.rgagnon.com/javadetails/java-0669.html
    //http://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
public class ExecutionFlowInitializerTest {
    @Test
    public void initTest() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("src/test/resources/ExecutionFlow.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("src/test/resources/ExecutionFlow.xml")));
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void anotherInitTest() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            SAXParser parser = null;
            try {
                factory.setSchema(schemaFactory.newSchema(new Source[]{new StreamSource("src/test/resources/ExecutionFlow.xsd")}));
                parser = factory.newSAXParser();
            } catch (SAXException se) {
                System.out.println("SCHEMA : " + se.getMessage());  // problem in the XSD itself
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            /*XMLReader reader = parser.getXMLReader();
            reader.parse(new InputSource("src/test/resources/ExecutionFlow.xml"));*/
            parser.parse("src/test/resources/ExecutionFlow.xml", new DefaultHandler() {
                private String currentFlow;
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    switch(qName) {
                        case "ExecutionFlows":
                            return;
                        case "ExecutionFlow":
                            currentFlow = attributes.getValue("name");
                            System.out.println("current flow is " + currentFlow);
                            ExecutionFlowConfiguration.getInstance().getFlowConfiguration().put(currentFlow, new ExecutionFlow(currentFlow));
                            return;
                        case "ExecutionStep":
                            System.out.println(attributes.getValue("name") + " belongs to " + currentFlow);
                            ExecutionFlowConfiguration.getInstance().getFlowConfiguration().get(currentFlow).getFlowNodes().add(new ExecutionFlowNode(attributes.getValue("name"), currentFlow));
                            return;
                    }
                    //System.out.println("startElement : " + qName);
                    //System.out.println("attributes : " + attributes);
                    /*int length = attributes.getLength();
                    for(int i = 0 ; i < length ; i++) {
                        System.out.println(attributes.getQName(i));
                        System.out.println(attributes.getValue(i));
                    }*/
                    //super.startElement(uri, localName, qName, attributes);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //System.out.println("endElement : " + qName);
                    //System.out.println("attributes : " + attributes);
                    super.endElement(uri, localName, qName);
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    //System.out.println("Data : " + new String(ch, start, length));
                    super.characters(ch, start, length);
                }
            });
            System.out.println("Done");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void initConfigTest() {
        try {
            String errorMessage = "message";
            Map<String, ExecutionFlow> flowConfiguration = ExecutionFlowConfiguration.getInstance().getFlowConfiguration();
            Assert.assertNotNull(errorMessage, flowConfiguration);
            Assert.assertTrue(errorMessage, flowConfiguration.size() == 2);
            ExecutionFlow sampleFlow_1 = flowConfiguration.get("SampleFlow_1");
            Assert.assertNotNull(errorMessage, sampleFlow_1);
            List<ExecutionFlowNode> flowNodeList_1 = sampleFlow_1.getFlowNodes();
            Assert.assertTrue(errorMessage, flowNodeList_1.size() == 5);
            Assert.assertTrue(errorMessage, flowNodeList_1.get(0).getFlowName().equals("SampleFlow_1") && flowNodeList_1.get(0).getFlowNodeName().equals("SampleStep_11"));
            Assert.assertTrue(errorMessage, flowNodeList_1.get(1).getFlowName().equals("SampleFlow_1") && flowNodeList_1.get(1).getFlowNodeName().equals("SampleStep_12"));
            Assert.assertTrue(errorMessage, flowNodeList_1.get(2).getFlowName().equals("SampleFlow_1") && flowNodeList_1.get(2).getFlowNodeName().equals("SampleStep_13"));
            Assert.assertTrue(errorMessage, flowNodeList_1.get(3).getFlowName().equals("SampleFlow_1") && flowNodeList_1.get(3).getFlowNodeName().equals("SampleStep_14"));
            Assert.assertTrue(errorMessage, flowNodeList_1.get(4).getFlowName().equals("SampleFlow_1") && flowNodeList_1.get(4).getFlowNodeName().equals("SampleStep_15"));
            ExecutionFlow sampleFlow_2 = flowConfiguration.get("SampleFlow_2");
            Assert.assertNotNull(errorMessage, sampleFlow_2);
            List<ExecutionFlowNode> flowNodeList_2 = sampleFlow_2.getFlowNodes();
            Assert.assertTrue(errorMessage, flowNodeList_2.size() == 5);
            Assert.assertTrue(errorMessage, flowNodeList_2.get(0).getFlowName().equals("SampleFlow_2") && flowNodeList_2.get(0).getFlowNodeName().equals("SampleStep_21"));
            Assert.assertTrue(errorMessage, flowNodeList_2.get(1).getFlowName().equals("SampleFlow_2") && flowNodeList_2.get(1).getFlowNodeName().equals("SampleStep_22"));
            Assert.assertTrue(errorMessage, flowNodeList_2.get(2).getFlowName().equals("SampleFlow_2") && flowNodeList_2.get(2).getFlowNodeName().equals("SampleStep_23"));
            Assert.assertTrue(errorMessage, flowNodeList_2.get(3).getFlowName().equals("SampleFlow_2") && flowNodeList_2.get(3).getFlowNodeName().equals("SampleStep_24"));
            Assert.assertTrue(errorMessage, flowNodeList_2.get(4).getFlowName().equals("SampleFlow_2") && flowNodeList_2.get(4).getFlowNodeName().equals("SampleStep_25"));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }
}
