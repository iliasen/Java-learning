package org.example.factory;


import org.example.parsers.XMLParser;
import org.example.parsers.XMLBusParser;


public class BusParserFactory implements ParserFactory {

    @Override
    public XMLParser createXMLParser() {
        return new XMLBusParser();
    }

}
