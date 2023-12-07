package org.example.factory;

import org.example.writer.FileWriter;
import org.example.writer.XMLWriter;

public class XMLWriterFactory implements FileWriterFactory {
    @Override
    public FileWriter createFileWriter() {
        return new XMLWriter();
    }
}