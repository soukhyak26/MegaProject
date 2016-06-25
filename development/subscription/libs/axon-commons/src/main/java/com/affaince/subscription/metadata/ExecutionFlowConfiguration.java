package com.affaince.subscription.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mongodb.util.JSON;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 26/3/16.
 */
public final class ExecutionFlowConfiguration {
    public static final String XSD_NAME = "ExecutionFlow.xsd";
    public static final String XSD_PATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "resources"
            + File.separator
            + XSD_NAME;
    public static final String XML_NAME = "ExecutionFlow.xml";
    public static final String XML_PATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "resources"
            + File.separator
            + XML_NAME;
    public static final String JSON_NAME = "ExecutionFlow.json";
    public static final String JSON_PATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "resources"
            + File.separator
            + JSON_NAME;
    private static final String EXECUTIONFLOWS_TAG = "ExecutionFlows";
    private static final String EXECUTIONFLOW_TAG = "ExecutionFlow";
    private static final String EXECUTIONSTEP_TAG = "ExecutionStep";
    private static final String NAME_ATTRIBUTE = "name";
    public Map<String, ExecutionFlow> getFlowConfiguration() {
        return flowConfiguration;
    }
    private Map<String, ExecutionFlow> flowConfiguration = new HashMap<>();
    private boolean isInit = false;
    private ExecutionFlowConfiguration() {
        if(isInit) return;
        try {
            initConfiguration();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
    private static class ExecutionFlowConfigurationHolder {
        private static final ExecutionFlowConfiguration INSTANCE = new ExecutionFlowConfiguration();
    }
    public static ExecutionFlowConfiguration getInstance() {
        return ExecutionFlowConfigurationHolder.INSTANCE;
    }
    //TODO: Can we use JAXB and generated classes for more sophisticated parsing?
    public void initConfiguration() throws SAXException, ParserConfigurationException, IOException {
        if(isInit) return;
        /*SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        factory.setSchema(schemaFactory.newSchema(new Source[]{new StreamSource(XSD_PATH)}));
        SAXParser parser = factory.newSAXParser();
        parser.parse(XML_PATH, new DefaultHandler() {
            private String currentFlow;
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                switch (qName) {
                    case EXECUTIONFLOWS_TAG:
                        return;
                    case EXECUTIONFLOW_TAG:
                        currentFlow = attributes.getValue(NAME_ATTRIBUTE);
                        flowConfiguration.put(currentFlow, new ExecutionFlow(currentFlow));
                        return;
                    case EXECUTIONSTEP_TAG:
                        flowConfiguration.get(currentFlow)
                                .addExecutionFlowNode(
                                        new ExecutionFlowNode(attributes.getValue(NAME_ATTRIBUTE)*//*, currentFlow*//*)
                                );
                        return;
                    default:
                        throw new RuntimeException("Invalid Tag : " + qName);
                }
            }
        });*/
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //flowConfiguration = new HashMap<>();
        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, ExecutionFlow.class);
        //flowConfiguration = mapper.readValue(new File(JSON_NAME), mapType);
        flowConfiguration = mapper.readValue((new ClassPathResource(JSON_NAME/*, this.getClass()*/)).getFile(), mapType);
        isInit = true;
    }
}
