package org.example.factory;

import org.example.parsers.PropertiesParser;
import org.example.parsers.XMLParser;
import org.example.parsers.properties.PropertiesBusParser;
import org.example.parsers.xml.XMLBusParser;


public class BusParserFactory implements ParserFactory {

    @Override
    public XMLParser createXMLParser() {
        return new XMLBusParser();
    }

    @Override
    public PropertiesParser createPropertiesParser() {
        return new PropertiesBusParser();
    }
}
