package org.example.factory;


import org.example.parsers.XMLParser;
import org.example.parsers.XMLTrainParser;

public class TrainParserFactory implements ParserFactory {
    @Override
    public XMLParser createXMLParser() {
        return new XMLTrainParser();
    }

}
