package org.example.factory;

import org.example.writer.XMLWriter;
import org.example.writer.FileWriter;
import org.example.writer.PropertiesWriter;

public interface FileWriterFactory {
    public FileWriter createFileWriter();
}