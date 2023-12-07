package org.example.factory;


import org.example.parsers.XMLParser;

public interface ParserFactory {
    XMLParser createXMLParser();

}
