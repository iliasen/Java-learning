package org.example.factory;

import org.example.writer.FileWriter;
import org.example.writer.PropertiesWriter;

public class PropertiesWriterFactory implements FileWriterFactory{
    @Override
    public FileWriter createFileWriter() {
        return new PropertiesWriter();
    }
}
